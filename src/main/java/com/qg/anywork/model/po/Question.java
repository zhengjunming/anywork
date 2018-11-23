package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 问题实体
 *
 * @author FunriLy
 * @date 2017/7/10
 * From small beginnings comes great things.
 */
@Data
@NoArgsConstructor
public class Question implements Serializable {

    /**
     * 问题id
     */
    private int questionId;

    /**
     * 题目类型  1-选择题 2-判断题 3-填空题 4-问答题
     */
    private int type;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 选择A
     */
    private String A;

    /**
     * 选择B
     */
    private String B;

    /**
     * 选择C
     */
    private String C;

    /**
     * 选择D
     */
    private String D;

    /**
     * 答案
     */
    private String key;

    /**
     * 分数
     */
    private double socre;

    /**
     * 试卷id
     */
    private int testpaperId;

    /**
     * 填空题个数
     */
    private int other;

    /**
     * 解析
     */
    private String analysis;

    /**
     * 错误率
     */
    private double errorRate;

}
