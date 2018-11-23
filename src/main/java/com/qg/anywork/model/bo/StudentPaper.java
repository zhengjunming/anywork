package com.qg.anywork.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author logan
 * @date 2017/7/10
 */
@Data
@NoArgsConstructor
public class StudentPaper implements Serializable {

    /**
     * 学生答卷id
     */
    private int studentPaperId;

    /**
     * 学生做的答案
     */
    private List<StudentAnswer> studentAnswer;

    /**
     * 答题者名字
     */
    private String userName;

    /**
     * 答题者Id
     */
    private int studentId;

    /**
     * 试卷id
     */
    private int testpaperId;

    /**
     * 是否临时保存 1为临时保存，0为正常提交
     */
    private int temporarySave;

    /**
     * 开始答题的时间
     */
    private String startTime;

    /**
     * 答题结束的时间
     */
    private String endTime;
}
