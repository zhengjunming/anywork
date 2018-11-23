package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 试卷实体
 *
 * @author FunriLy
 * @date 2017/7/10
 * From small beginnings comes great things.
 */
@Data
@NoArgsConstructor
public class TestPaper implements Serializable {

    /**
     * 试卷ID
     */
    private int testpaperId;

    /**
     * 试卷标题
     */
    private String testpaperTitle;

    /**
     * 教师ID
     */
    private int authorId;

    /**
     * 组织ID
     */
    private int organizationId;

    /**
     * 开始时间
     */
    private Date createTime;

    /**
     * 结束时间
     */
    private Date endingTime;

    /**
     * 章节id
     */
    private int chapterId;

    /**
     * 章节名称，为练习卷添加章节
     */
    private String chapterName;

    /**
     * 试卷分数
     */
    private int testpaperScore;

    /**
     * 试卷类型，0为练习、1为考试，2为预习题，3为课后复习题，若将来扩展可在这个字段上实现
     */
    private int testpaperType;

    /**
     * 平均分
     */
    private double averageScore;

    /**
     * 是否有主觀題，１爲有，０沒有
     */
    private int haveSubject;

    /**
     * 试卷下的所有题目
     */
    private List<Question> questions;
}
