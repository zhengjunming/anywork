package com.qg.anywork.service;

import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Message;

import java.io.IOException;

/**
 * 消息实体 Service 层
 *
 * @author FunriLy
 * @date 2017/9/25
 * From small beginnings comes great things.
 */
public interface MessageService {

    /**
     * 发布公告
     *
     * @param message   公告
     * @param publisher 发布人
     * @return request result
     * @throws IOException ioException
     */
    RequestResult publishMessage(Message message, String publisher) throws IOException;


    /**
     * 获取老师发布过的公告
     *
     * @param userId   userId
     * @param pageNum  页数
     * @param pageSize 一页显示的大小
     * @return 公告列表
     */
    RequestResult listMessage(int userId, int pageNum, int pageSize);

    /**
     * 删除公告
     *
     * @param messageId 公告ID
     * @param userId    用户ID
     * @return request result
     */
    RequestResult deleteMessage(int messageId, int userId);

    /**
     * 学生查看公告
     *
     * @param userId   用户ID
     * @param pageNum  页数
     * @param pageSize 一页的数目
     * @param status   公告状态
     * @return 公告
     */
    RequestResult studentShowMessage(int userId, int pageNum, int pageSize, int status);

    /**
     * 改变公告状态
     *
     * @param userId    用户ID
     * @param messageId 公告ID
     * @return request result
     */
    RequestResult changeMessageStatus(int userId, int messageId);
}
