package com.qg.anywork.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qg.anywork.dao.MessageDao;
import com.qg.anywork.dao.OrganizationDao;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.common.ParamException;
import com.qg.anywork.exception.message.MessageException;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Message;
import com.qg.anywork.model.po.Organization;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.MessageService;
import com.qg.anywork.util.DateUtil;
import com.qg.anywork.web.socket.OnlineWebSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Create by ming on 18-8-5 下午10:17
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;
    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public RequestResult publishMessage(Message message, String publisher) throws IOException {
        List<Organization> organizations = organizationDao.getMyOrganization(message.getUserId());
        if (organizations.isEmpty()) {
            return new RequestResult(0, "您还未创建组织，发的公告没人看哦，请先创建组织");
        }
        List<Integer> organizationIds = new ArrayList<>();
        for (Organization organization : organizations) {
            organizationIds.add(organization.getOrganizationId());
        }
        List<Integer> userIds = new ArrayList<>();
        List<User> users = new ArrayList<>();
        for (Organization organization : organizations) {
            users.addAll(organizationDao.getOrganizationPeople(organization.getOrganizationId()));
        }
        if (users.isEmpty()) {
            return new RequestResult(0, "您创建的组织还没有人加入，没必要发公告");
        }
        for (User user : users) {
            userIds.add(user.getUserId());
        }
        message.setCreateTime(DateUtil.format(new Date()));
        messageDao.insertMessage(message);
        messageDao.insertMessageAndOrganization(message.getMessageId(), organizationIds);
        messageDao.insertUserMessage(message.getMessageId(), userIds);
        OnlineWebSocket.publishMessage(message, userIds, publisher);
        log.info(publisher + "发布了一条公告");
        return new RequestResult(StatEnum.MESSAGE_PUBLISH_SUCCESS);
    }

    @Override
    public RequestResult listMessage(int userId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        PageInfo<Message> messages = new PageInfo<>(messageDao.findMessageByUserId(userId));
        return new RequestResult<>(StatEnum.LIST_MESSAGE_SUCCESS, messages);
    }

    @Override
    public RequestResult deleteMessage(int messageId, int userId) {
        if (messageDao.findByUserIdAndMessageId(userId, messageId) == null) {
            throw new MessageException(StatEnum.NOT_SUCH_MESSAGE);
        }
        int flag = messageDao.deleteMessageById(messageId);
        if (flag == 1) {
            messageDao.deleteMessageUserByMessageId(messageId);
            messageDao.deleteMessageOrganizationByMessageId(messageId);
        }
        return new RequestResult(1, "删除公告成功");
    }

    @Override
    public RequestResult studentShowMessage(int userId, int pageNum, int pageSize, int status) {
        PageInfo<Message> messages;
        // 获取组织
        int organizationId = organizationDao.findOrganizationByUserId(userId);
        PageHelper.startPage(pageNum, pageSize);
        // 此查询必须是第一个，不然会失效

        if (status == 1) {
            // 获取已读公告
            List<Message> haveReadMessages = messageDao.findHaveReadMessageExceptMessageIds(userId, organizationId);
            if (haveReadMessages.size() == 0) {
                // 公告列表为空
                throw new MessageException(StatEnum.MESSAGE_LIST_IS_NULL);
            }
            for (Message message : haveReadMessages) {
                message.setStatus(1);
            }
            messages = new PageInfo<>(haveReadMessages);
        } else if (status == 0) {
            // 获取未读公告
            List<Message> unreadMessages = messageDao.findUnreadMessage(userId);
            if (unreadMessages.size() == 0) {
                // 公告列表为空
                throw new MessageException(StatEnum.MESSAGE_LIST_IS_NULL);
            }
            for (Message message : unreadMessages) {
                message.setStatus(0);
            }
            messages = new PageInfo<>(unreadMessages);
        } else if (status == 2) {
            // 获取全部公告
            List<Message> allMessages = messageDao.findAllMessagesByOrganizationId(organizationId);
            if (allMessages.size() == 0) {
                // 公告列表为空
                throw new MessageException(StatEnum.MESSAGE_LIST_IS_NULL);
            }
            // 获取未读公告ID
            List<Integer> messageIds = messageDao.findMessageIdsByStudentId(userId);
            for (Message message : allMessages) {
                if (messageIds.contains(message.getMessageId())) {
                    message.setStatus(0);
                } else {
                    message.setStatus(1);
                }
            }
            return new RequestResult<>(StatEnum.LIST_MESSAGE_SUCCESS, new PageInfo<>(allMessages));
        } else {
            throw new ParamException(StatEnum.ERROR_PARAM);
        }
        return new RequestResult<>(StatEnum.LIST_MESSAGE_SUCCESS, messages);
    }

    @Override
    public RequestResult changeMessageStatus(int userId, int messageId) {
        Message message = messageDao.findByMessageId(messageId);
        if (message == null) {
            throw new MessageException(StatEnum.NOT_SUCH_MESSAGE);
        }
        messageDao.deleteMessageByUserIdAndMessageId(userId, messageId);
        return new RequestResult(1, "标记成功");
    }
}
