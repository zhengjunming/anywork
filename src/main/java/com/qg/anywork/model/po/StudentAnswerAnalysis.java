package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author logan
 * @date 2017/7/10
 */
@Data
@NoArgsConstructor
public class StudentAnswerAnalysis implements Serializable {

    /**
     * 分析id 暂无用处
     */
    private int studentAnswerId;

    /**
     * 学生id
     */
    private int studentId;

    /**
     * 试题
     */
    private Question question;

    /**
     * 学生答案
     */
    private String studentAnswer;

    /**
     * 是否正确
     */
    private int isTrue;

    /**
     * 得分情况
     */
    private double socre;

    /**
     * 是否收藏的状态，１为已收藏，０为未收藏
     */
    private Integer collectionStatus;
}
