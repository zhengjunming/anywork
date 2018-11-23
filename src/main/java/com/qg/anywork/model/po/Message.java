package com.qg.anywork.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统通知实体
 *
 * @author ming
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {

    /**
     * 通知id
     */
    private int messageId;

    /**
     * 发布人ID
     */
    private int userId;

    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 发布人
     */
    private String publisher;

    /**
     * 公告是否已读，1为已读，0为未读
     */
    private Integer status;
}
