package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 学生成绩实体
 *
 * @author FunriLy
 * @date 2017/7/10
 * From small beginnings comes great things.
 */
@Data
@NoArgsConstructor
public class StudentTestResult implements Serializable {

    /**
     * 考试结果ID
     */
    private int studentTestResultId;

    /**
     * 学生ID
     */
    private int studentId;

    /**
     * 考试ID
     */
    private int testpaperId;

    /**
     * 组织ID
     */
    private int organizationId;

    /**
     * 分数
     */
    private double socre;

    /**
     * 答题结束时间
     */
    private String ending;

    /**
     * 具体题目分析
     */
    private List<StudentAnswerAnalysis> studentAnswerAnalysis;
}
