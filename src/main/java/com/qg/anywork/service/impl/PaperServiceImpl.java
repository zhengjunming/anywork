package com.qg.anywork.service.impl;

import com.qg.anywork.dao.OrganizationDao;
import com.qg.anywork.dao.PaperDao;
import com.qg.anywork.dao.QuestionDao;
import com.qg.anywork.dao.TestDao;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.organization.OrganizationException;
import com.qg.anywork.exception.testpaper.TestPaperException;
import com.qg.anywork.exception.testpaper.TestPaperTimeException;
import com.qg.anywork.model.bo.TeacherJudge;
import com.qg.anywork.model.bo.TeacherSubmit;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.*;
import com.qg.anywork.service.PaperService;
import com.qg.anywork.util.DateUtil;
import com.qg.anywork.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Create by ming on 18-10-5 下午11:07
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class PaperServiceImpl implements PaperService {

    private final OrganizationDao organizationDao;

    private final PaperDao paperDao;

    private final QuestionDao questionDao;

    private final TestDao testDao;

    @Autowired
    public PaperServiceImpl(OrganizationDao organizationDao, PaperDao paperDao, QuestionDao questionDao, TestDao testDao) {
        this.organizationDao = organizationDao;
        this.paperDao = paperDao;
        this.questionDao = questionDao;
        this.testDao = testDao;
    }

    @Override
    public RequestResult addTestPaper(InputStream inputStream, String testPaperTitle, Integer chapterId,
                                      String createTime, String endingTime, Integer testPaperType, User user) throws Exception {
        if (DateUtil.parse(createTime).after(DateUtil.parse(endingTime)) || DateUtil.parse(createTime).before(new Date())) {
            // 开始时间比结束时间晚
            throw new TestPaperTimeException(StatEnum.TEST_PAPER_TIME_ERROR);
        }
        List<Integer> organizationIds = organizationDao.getMyOrganizationIds(user.getUserId());
        int testPaperId;
        TestPaper testpaper = new TestPaper();
        testpaper.setAuthorId(user.getUserId());
        testpaper.setTestpaperTitle(testPaperTitle);
        testpaper.setChapterId(chapterId);
        testpaper.setCreateTime(DateUtil.parse(createTime));
        testpaper.setEndingTime(DateUtil.parse(endingTime));
        testpaper.setTestpaperType(testPaperType);
        testpaper.setHaveSubject(0);
        List<Question> questionList = ExcelUtil.getQuestionList(inputStream);
        if (questionList != null && questionList.size() > 0) {
            for (Question question : questionList) {
                if (question.getType() == 4) {
                    testpaper.setHaveSubject(1);
                    break;
                }
            }
        }
        // 将试卷插入数据库
        paperDao.insertTestPaper(testpaper);
        // 获得试卷ID
        testPaperId = testpaper.getTestpaperId();
        // 插入试卷与组织的关系
        paperDao.insertTestPaperOrganization(testPaperId, organizationIds);

        int score = 0;
        if (questionList != null && questionList.size() > 0) {
            for (Question question : questionList) {
                //更新试卷号
                question.setTestpaperId(testPaperId);
                //将总分加起来
                score += question.getSocre();
            }
        }
        questionDao.insertAllQuestion(questionList);
        paperDao.updateTestPaperScore(testPaperId, score);
        testpaper.setQuestions(questionList);
        testpaper.setTestpaperScore(score);
        log.info("{}发布了试卷", user.getUserName());
        return new RequestResult<>(StatEnum.TEST_RELEASE_SUCESS, testpaper);
    }

    @Override
    public RequestResult updateTestPaperInfo(Integer testPaperId, String testPaperTitle, Integer testPaperType, String createTime, String endingTime) throws ParseException {
        TestPaper testPaper = new TestPaper();
        testPaper.setTestpaperId(testPaperId);
        testPaper.setTestpaperTitle(testPaperTitle);
        testPaper.setTestpaperType(testPaperType);
        if (!(createTime == null || "".equals(createTime))) {
            testPaper.setCreateTime(DateUtil.parse(createTime));
        }
        if (!(endingTime == null || "".equals(endingTime))) {
            testPaper.setEndingTime(DateUtil.parse(endingTime));
        }
        paperDao.updateTestPaperInfo(testPaper);
        testPaper = paperDao.findByTestPaperId(testPaperId);
        return new RequestResult<>(0, "修改成功", testPaper);
    }

    @Override
    public RequestResult deleteTestPaper(Integer testPaperId) {
        paperDao.deleteTestPaperOrganizationRelation(testPaperId);
        questionDao.deleteQuestion(testPaperId);
        paperDao.deleteTestPaper(testPaperId);
        return new RequestResult<>(1, "删除成功");
    }

    @Override
    public RequestResult showTestPaper(Integer testPaperId) {
        return new RequestResult<>(StatEnum.GET_SUCCESS, paperDao.findByTestPaperId(testPaperId));
    }

    @Override
    public RequestResult analyseTestPaper(Integer testPaperId, Integer organizationId, Integer userId) {
        // 获取试卷信息
        TestPaper testPaper = paperDao.findByTestPaperId(testPaperId);
        // 计算平均分、题目错误率
        List<Integer> userIds = new ArrayList<>();
        if (organizationId == 0) {
            List<Integer> organizationIds = organizationDao.getMyOrganizationIds(userId);
            for (Integer oId : organizationIds) {
                userIds.addAll(organizationDao.getUserIdsByOrganizationId(oId));
            }
        } else {
            List<Integer> organizationIds = organizationDao.getMyOrganizationIds(userId);
            if (!organizationIds.contains(organizationId)) {
                throw new OrganizationException(StatEnum.ORGANIZATION_NOT_EXIST);
            }
            userIds = organizationDao.getUserIdsByOrganizationId(organizationId);
        }
        double averageScore = paperDao.getAverageScore(userIds, testPaper.getTestpaperId());
        DecimalFormat df = new DecimalFormat("#.00");
        testPaper.setAverageScore(Double.parseDouble(df.format(averageScore)));
        // 计算错误率
        for (Question question : testPaper.getQuestions()) {
            int correctNumber = paperDao.getQuestionCorrectNumber(userIds, question.getQuestionId());
            double errorRate = ((userIds.size() - correctNumber) * 1.0) / userIds.size();
            question.setErrorRate(Double.parseDouble(df.format(errorRate)));
        }
        return new RequestResult<>(StatEnum.GET_SUCCESS, testPaper);
    }

    @Override
    public RequestResult listTestPaper(Integer organizationId) {
        List<TestPaper> testPapers = paperDao.listTestPaperByOrganizationId(organizationId);
        if (testPapers.size() == 0) {
            throw new TestPaperException(StatEnum.TEST_LIST_IS_NULL);
        }
        return new RequestResult<>(StatEnum.GET_SUCCESS, testPapers);
    }

    @Override
    public RequestResult listStudentDoneDetail(Integer testPaperId, Integer organizationId, User user) {
        List<CheckResult> checkResults;
        if (organizationId == 0) {
            checkResults = listAllOrganizationStudentDoneDetail(organizationDao.getMyOrganizationIds(user.getUserId()), testPaperId);
        } else {
            checkResults = listOneOrganizationStudentDoneDetail(organizationId, testPaperId);
        }
        if (checkResults.size() == 0) {
            return new RequestResult(0, "没有对应的记录");
        }
        return new RequestResult<>(StatEnum.GET_SUCCESS, checkResults);
    }

    @Override
    public RequestResult showStudentTestDetail(Integer testPaperId, Integer studentId) {
        // 获取该试卷的结果
        StudentTestResult testResult = testDao.getTestResult(testPaperId, studentId);
        Map<String, Object> map = new ConcurrentHashMap<>(4);
        map.put("studentId", studentId);
        map.put("testpaperId", testPaperId);
        map.put("socre", testResult.getSocre());
        List<StudentAnswerAnalysis> studentAnswerAnalyses = testDao.getStudentAnswer(testPaperId, studentId);
        map.put("studentAnswerAnalysis", studentAnswerAnalyses);
        return new RequestResult<>(StatEnum.GET_SUCCESS, map);
    }

    @Override
    public RequestResult getStudentSubjectAnswer(Integer testPaperId, Integer studentId) {
        // 获取该试卷的结果
        StudentTestResult testResult = testDao.getTestResult(testPaperId, studentId);
        Map<String, Object> map = new ConcurrentHashMap<>(4);
        map.put("studentId", studentId);
        map.put("testpaperId", testPaperId);
        map.put("socre", testResult.getSocre());
        List<StudentAnswerAnalysis> studentAnswerAnalyses = testDao.getStudentSubjectAnswer(testPaperId, studentId);
        map.put("studentAnswerAnalysis", studentAnswerAnalyses);
        return new RequestResult<>(StatEnum.GET_SUCCESS, map);
    }

    @Override
    public RequestResult teacherJudgeSubjectAnswer(TeacherSubmit teacherSubmit) {
        List<TeacherJudge> teacherJudges = teacherSubmit.getTeacherJudge();
        double subject = 0.0;
        for (TeacherJudge t : teacherJudges) {
            subject = t.getSocre();
            // 更新studentAnswer
            testDao.updateStudentAnswerSocre(t.getSocre(), teacherSubmit.getStudentId(), t.getQuestionId());
        }
        // 更新checkResult
        testDao.updateCheckResult(subject, teacherSubmit.getTestpaperId(), teacherSubmit.getStudentId());
        StudentTestResult studentTestResult = testDao.getTestResult(teacherSubmit.getTestpaperId(), teacherSubmit.getStudentId());
        studentTestResult.setSocre(studentTestResult.getSocre() + subject);
        testDao.updateTestResult(studentTestResult);
        return new RequestResult<>(1, "评卷成功");
    }

    private List<CheckResult> listAllOrganizationStudentDoneDetail(List<Integer> organizationIds, Integer testPaperId) {
        List<CheckResult> checkResults = new ArrayList<>();
        for (Integer organizationId : organizationIds) {
            checkResults.addAll(listOneOrganizationStudentDoneDetail(organizationId, testPaperId));
        }
        return checkResults;
    }

    private List<CheckResult> listOneOrganizationStudentDoneDetail(Integer organizationId, Integer testPaperId) {
        List<CheckResult> checkResultsByTest = testDao.getCheckResultByTestpaperId(organizationId, testPaperId);
        String organizationName = organizationDao.getById(organizationId).getOrganizationName();
        // 试卷的主要信息
        TestPaper testPaper = paperDao.findTestPaperMainInfoById(testPaperId);
        List<CheckResult> checkResults = new ArrayList<>();
        List<User> users = organizationDao.getOrganizationPeople(organizationId);
        for (User u : users) {
            int flag = 0;
            for (CheckResult c : checkResultsByTest) {
                if (c.getStudentId() == u.getUserId()) {
                    c.setIfAttend(1);
                    c.setOrganizationName(organizationName);
                    checkResults.add(c);
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                CheckResult checkResult = new CheckResult();
                checkResult.setOrganizationName(organizationName);
                checkResult.setIfAttend(0);
                checkResult.setObject(0);
                checkResult.setSubject(0);
                checkResult.setStudentId(u.getUserId());
                checkResult.setStudentName(u.getUserName());
                checkResult.setIfCheck(0);
                checkResult.setTestpaper(testPaper);
                checkResult.setStudentNum(u.getStudentId());
                checkResults.add(checkResult);
            }
        }
        return checkResults;
    }
}
