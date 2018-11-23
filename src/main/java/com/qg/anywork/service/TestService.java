package com.qg.anywork.service;

import com.qg.anywork.model.bo.StudentPaper;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.StudentTestResult;

import java.text.ParseException;

/**
 * @author logan
 * @date 2017/7/10
 */
public interface TestService {

    /**
     * 获取某个章节特定的题目
     *
     * @param userId         用户ID
     * @param organizationId 组织ID
     * @param chapterId      章节ID
     * @param testPaperType  试卷类型
     * @return 试卷列表
     */
    RequestResult listTest(int userId, int organizationId, int chapterId, int testPaperType);

    /**
     * 获取已做过试卷的详情
     *
     * @param userId      用户ID
     * @param testPaperId 试卷ID
     * @return 试卷详情
     */
    RequestResult getDoneTestDetail(int userId, int testPaperId);

    /**
     * 获取详细的试题（完成一部分和还未做）
     *
     * @param userId      用户ID
     * @param testPaperId 试卷ID
     * @param choice      对做一半的试卷的选择 1代表继续做，2代表重新做，如果是对于还未做的试卷，值为0
     * @return 详细试题
     */
    RequestResult getNoneTestDetail(int userId, int testPaperId, int choice);

    /**
     * 试卷提交
     *
     * @param studentPaper 学生提交的答案
     * @return 提交结果
     * @throws ParseException parseException
     */
    RequestResult<StudentTestResult> submitTestPaper(StudentPaper studentPaper) throws ParseException;

    /**
     * 获取学生这道题的详细信息
     *
     * @param userId     用户D
     * @param questionId 题目ID
     * @return 详细信息
     */
    RequestResult getQuestionDetail(int userId, int questionId);
}
