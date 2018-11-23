package com.qg.anywork.model.po;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Create by ming on 18-8-22 下午10:56
 * 收藏题实体类
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Data
@NoArgsConstructor
public class CollectionQuestion {
    /**
     * ID
     */
    private Integer collectionQuestionId;

    /**
     * 问题
     */
    private Integer questionId;

    /**
     * 学生ID
     */
    private Integer studentId;

    /**
     * 收藏的时间
     */
    private String collectionTime;
}
