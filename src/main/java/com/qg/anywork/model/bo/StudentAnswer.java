package com.qg.anywork.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学生答案实体
 *
 * @author FunriLy
 * @date 2017/7/10
 * From small beginnings comes great things.
 */
@Data
@NoArgsConstructor
public class StudentAnswer implements Serializable {

    /**
     * 学生答案ID
     */
    private int studentAnswerId;

    /**
     * 问题ID
     */
    private int questionId;

    /**
     * 学生答案
     */
    private String studentAnswer;
}
