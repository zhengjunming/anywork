package com.qg.anywork.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ming
 */
@Data
@NoArgsConstructor
public class TeacherJudge implements Serializable {

    /**
     * 问题id
     */
    private int questionId;

    /**
     * 老师所给分数
     */
    private double socre;
}
