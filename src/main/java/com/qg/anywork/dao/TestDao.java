package com.qg.anywork.dao;

import com.qg.anywork.model.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author logan
 * @date 2017/7/11
 * 操作学生的答卷
 */
@Mapper
@Repository
public interface TestDao {

    /***
     * 根据试卷id获取试卷信息
     * @param testpaperId 试卷id
     * @return TestPaper 返回问卷对象
     */
    TestPaper getTestPaperByTestpaperId(@Param("testpaperId") int testpaperId);


    /***
     * 根据试卷id获取试题集合
     *
     * @param testPaperId 试卷id
     * @return List<Question> 返回答题集合
     */
    List<Question> getQuestionByTestpaperId(@Param("testPaperId") int testPaperId);

    /***
     * 根据试题号获取试题
     * @param questionId 问题id
     * @return Question问题对象
     */
    Question getQuestionById(@Param("questionId") int questionId);

    /***
     * 查看用户是否提交过试卷了
     *
     * @param testPaperId 试卷id
     * @param userId 用户id
     * @return 1为提交过了 0为未提交
     */
    int isSubmit(@Param("testPaperId") int testPaperId, @Param("userId") int userId);

    /***
     * 添加考试结果
     * @param testResult 试卷结果类
     * @return 1为添加成功 0为失败
     */
    int addTestResult(@Param("testResult") StudentTestResult testResult);

    /***
     * 更新 考试结果
     * @param testResult 考试结果
     * @return int
     */
    int updateTestResult(@Param("testResult") StudentTestResult testResult);

    /***
     * 添加学生的答案
     * @param studentAnswerAnalysis 学生答案类
     * @return 1为添加成功 0为添加失败
     */
    int addStudentAnswer(@Param("studentAnswerAnalysis") StudentAnswerAnalysis studentAnswerAnalysis);

    /***
     * 获得考试结果
     * @param testPaperId 试卷ID
     * @param userId 学生ID
     * @return 试卷结果
     */
    StudentTestResult getTestResult(@Param("testPaperId") int testPaperId, @Param("userId") int userId);

    /***
     * 获得详细学生试卷详情
     *
     * @param testPaperId 试卷ID
     * @param userId 用户ID
     * @return 试卷详情集合
     */
    List<StudentAnswerAnalysis> getStudentAnswer(@Param("testPaperId") int testPaperId, @Param("userId") int userId);

    /**
     * 获取简答题的详情
     *
     * @param testPaperId 试卷ID
     * @param userId      学生ID
     * @return 简答题
     */
    List<StudentAnswerAnalysis> getStudentSubjectAnswer(@Param("testPaperId") int testPaperId, @Param("userId") int userId);

    /**
     * 获取某套题组织内成员的完成情况
     *
     * @param organizationId
     * @param testpaperId
     * @return
     */
    List<CheckResult> getCheckResultByTestpaperId(@Param("organizationId") int organizationId, @Param("testpaperId") int testpaperId);

    /***
     * 添加教师查阅及评卷信息
     * @param checkResult checkResult
     * @return 1为成功，0为失败
     */
    int addCheckResult(@Param("checkResult") CheckResult checkResult);


    /***
     * 更新学生完成的每道题的分数
     * @param socre 分数
     * @param studentId 学生ID
     * @param questionId 问题ID
     * @return int
     */
    int updateStudentAnswerSocre(@Param("socre") double socre, @Param("studentId") int studentId, @Param("questionId") int questionId);

    /***
     * 更新批改详情
     * @param subject 主观题分数
     * @param testPaperId 试卷ID
     * @param studentId 学生ID
     * @return int
     */
    int updateCheckResult(@Param("subject") double subject, @Param("testPaperId") int testPaperId, @Param("studentId") int studentId);

    /**
     * 根据组织ID、章节Id与试卷类型获取试卷
     *
     * @param organizationId 组织ID
     * @param chapterId      章节ID
     * @param testPaperType  试卷类型
     * @return 试卷列表
     */
    List<TestPaper> findByOrganizationIdAndChapterIdAndTestPaperType(@Param("organizationId") int organizationId,
                                                                     @Param("chapterId") int chapterId,
                                                                     @Param("testPaperType") int testPaperType);

    /**
     * 根据试卷ID，用户ID和组织ID获取测试结果
     *
     * @param testPaperId 试卷ID
     * @param userId      用户ID
     * @return 测试结果
     */
    StudentTestResult findTestResultByTestPaperIdAndUserIdAndOrganizationId(@Param("testPaperId") int testPaperId,
                                                                            @Param("userId") int userId);

    /**
     * 获取一张试卷的总题目数
     *
     * @param testPaperId 试卷ID
     * @return 题目数
     */
    int countQuestion(@Param("testPaperId") int testPaperId);

    /**
     * 获取某道题目的详细信息
     *
     * @param userId     用户ID
     * @param questionId 题目ID
     * @return 详细信息
     */
    StudentAnswerAnalysis getStudentAnswerAnalysis(@Param("userId") int userId, @Param("questionId") int questionId);

    /**
     * 获取学生的成绩结果
     *
     * @param userId       学生ID
     * @param testPaperIds 试卷ID集合
     * @return 学生结果
     */
    List<StudentTestResult> findStudentTestResultByUserIdAndTestPaperIds(@Param("userId") int userId, @Param("testPaperIds") List<Integer> testPaperIds);

}
