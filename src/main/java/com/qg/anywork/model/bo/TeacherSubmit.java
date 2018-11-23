package com.qg.anywork.model.bo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author logan
 * @date 2017/8/1
 */
@Data
@NoArgsConstructor
public class TeacherSubmit implements Serializable {

    /**
     * 答题者Id
     */
    private int studentId;

    /**
     * 试卷id
     */
    private int testpaperId;

    /**
     * 老师评分
     */
    private List<TeacherJudge> teacherJudge;
}
