package com.qg.anywork.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Create by ming on 18-9-13 下午1:08
 * 排行榜返回实体类
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Data
public class LeaderBoard implements Serializable {

    /**
     * 姓名
     */
    private String username;

    /**
     * 组织名
     */
    private String organizationName;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 分数
     */
    private double score;

    /**
     * 头像路径
     */
    private String imagePath;

}
