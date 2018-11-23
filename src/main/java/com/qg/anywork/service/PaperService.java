package com.qg.anywork.service;

import com.qg.anywork.model.bo.TeacherSubmit;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.User;

import java.io.InputStream;
import java.text.ParseException;

/**
 * Create by ming on 18-10-5 下午11:07
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public interface PaperService {

    /**
     * 添加试卷
     *
     * @param inputStream    文件输入流
     * @param testPaperTitle 试卷标题
     * @param chapterId      章节ID
     * @param createTime     开始时间
     * @param endingTime     结束时间
     * @param testPaperType  试卷类型
     * @param user           老师
     * @return result
     * @throws Exception exception
     */
    RequestResult addTestPaper(InputStream inputStream, String testPaperTitle, Integer chapterId,
                               String createTime, String endingTime, Integer testPaperType, User user) throws Exception;

    /**
     * 修改试卷信息
     *
     * @param testPaperId    试卷ID
     * @param testPaperTitle 试卷标题
     * @param testPaperType  试卷类型
     * @param createTime     开始时间
     * @param endingTime     结束时间
     * @return result
     * @throws ParseException parseException
     */
    RequestResult updateTestPaperInfo(Integer testPaperId, String testPaperTitle, Integer testPaperType,
                                      String createTime, String endingTime) throws ParseException;

    /**
     * 删除试卷
     *
     * @param testPaperId 试卷ID
     * @return result
     */
    RequestResult deleteTestPaper(Integer testPaperId);

    /**
     * 查看试卷
     *
     * @param testPaperId 试卷ID
     * @return result
     */
    RequestResult showTestPaper(Integer testPaperId);


    /**
     * 试卷分析
     *
     * @param testPaperId    试卷ID
     * @param organizationId 组织ID
     * @param userId         教师ID
     * @return result
     */
    RequestResult analyseTestPaper(Integer testPaperId, Integer organizationId, Integer userId);

    /**
     * 列出该组织的所有试卷
     *
     * @param organizationId 组织ID
     * @return result
     */
    RequestResult listTestPaper(Integer organizationId);

    /**
     * 老师查看某套试题中的学生完成情况列表
     *
     * @param testPaperId    试卷ID
     * @param organizationId 组织ID
     * @param user           教师
     * @return result
     */
    RequestResult listStudentDoneDetail(Integer testPaperId, Integer organizationId, User user);

    /**
     * 具体查看某学生完成过的某套试题
     *
     * @param testPaperId 试卷ID
     * @param studentId   学生ID
     * @return result
     */
    RequestResult showStudentTestDetail(Integer testPaperId, Integer studentId);

    /**
     * 获取学生简答题答案进行评卷
     *
     * @param testPaperId 试卷ID
     * @param studentId   学生ID
     * @return result
     */
    RequestResult getStudentSubjectAnswer(Integer testPaperId, Integer studentId);

    /**
     * 老师评卷
     *
     * @param teacherSubmit 提交的评卷信息
     * @return result
     */
    RequestResult teacherJudgeSubjectAnswer(TeacherSubmit teacherSubmit);
}
