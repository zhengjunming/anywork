package com.qg.anywork.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ming
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
