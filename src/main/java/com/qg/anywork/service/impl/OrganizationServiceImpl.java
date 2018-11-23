package com.qg.anywork.service.impl;

import com.qg.anywork.dao.MessageDao;
import com.qg.anywork.dao.OrganizationDao;
import com.qg.anywork.dao.PaperDao;
import com.qg.anywork.dao.TestDao;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.organization.OrganizationException;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Message;
import com.qg.anywork.model.po.Organization;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.OrganizationService;
import com.qg.anywork.util.DateUtil;
import com.qg.anywork.web.socket.OnlineWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Create by ming on 18-8-5 下午1:47
 * 组织逻辑实现类
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private PaperDao paperDao;

    @Autowired
    private MessageDao messageDao;

    @Override
    public RequestResult<List<Organization>> search(String organizationName, int userId) {
        if (organizationName == null) {
            throw new OrganizationException(StatEnum.THE_NAME_IS_NULL);
        }
        List<Organization> organizations = organizationDao.getByKeyWords("%" + organizationName + "%");
        List<Organization> myOrganizations = organizationDao.getByUserId(userId);
        List<Integer> myOrganizationsId = new ArrayList<>();
        for (Organization organization : myOrganizations) {
            myOrganizationsId.add(organization.getOrganizationId());
        }
        for (Organization o : organizations) {
            o.setCount(organizationDao.getOrganizationCount(o.getOrganizationId()));
            if (myOrganizationsId.contains(o.getOrganizationId())) {
                o.setIsJoin(1);
            }
        }
        return new RequestResult<>(StatEnum.ORGAN_SEARCH_SUCCESS, organizations);
    }


    @Override
    public Organization getById(int organizationId) {
        return organizationDao.getById(organizationId);
    }

    @Override
    public Organization getByName(String organizationName) {
        return organizationDao.findByOrganizationName(organizationName);
    }

    @Override
    public synchronized RequestResult<Organization> join(int organizationId, String token, int userId) {
        if (organizationDao.isJoin(organizationId, userId) > 0) {
            throw new OrganizationException(StatEnum.USER_HAS_JOINED_THE_ORGANIZATION);
        }
        // 只能加入一个组织
        if (organizationDao.findRelationByUserId(userId) == 1) {
            throw new OrganizationException(StatEnum.CAN_ONLY_JOIN_ONE_ORGANIZATION);
        }
        Organization organization = organizationDao.getById(organizationId);
        if (organization == null) {
            throw new OrganizationException(StatEnum.ORGANIZATION_NOT_EXIST);
        }
        if (!organization.getToken().equals(token)) {
            throw new OrganizationException(StatEnum.THE_TOKEN_IS_ERROR);
        }
        organizationDao.joinOrganization(organizationId, userId);
        return new RequestResult<>(StatEnum.ORGAN_JOIN_SUCCESS);
    }

    @Override
    public RequestResult<List<Organization>> searchByUserId(int userId) {
        List<Organization> organizations = organizationDao.getByUserId(userId);
        for (Organization o : organizations) {
            o.setIsJoin(1);
            o.setCount(organizationDao.getOrganizationCount(o.getOrganizationId()));
        }
        return new RequestResult<>(StatEnum.ORGAN_SEARCH_SUCCESS, organizations);
    }

    @Override
    public RequestResult exitOrganization(int organizationId, int userId) {
        if (organizationDao.isJoin(organizationId, userId) == 0) {
            throw new OrganizationException(StatEnum.USER_HAS_NOT_JOINED_THE_ORGANIZATION);
        }
        int flag = organizationDao.exitOrganization(organizationId, userId);
        if (flag == 0) {
            return new RequestResult(0, "退出失败");
        }
        return new RequestResult(1, "退出成功");
    }

    @Override
    public RequestResult addOrganization(Integer userId, Organization organization) {
        organization.setToken(organization.getToken());
        int flag = organizationDao.addOrganization(organization);
        if (flag == 1) {
            List<Integer> testPaperIds = paperDao.findTestPaperIdsByUserId(userId);
            for (Integer integer : testPaperIds) {
                paperDao.insertTestPaperOrganization(integer, Collections.singletonList(organization.getOrganizationId()));
            }
            organizationDao.joinOrganization(organization.getOrganizationId(), organization.getTeacherId());
            return new RequestResult<>(1, "创建组织成功", organization);
        } else {
            return new RequestResult(0, "创建组织失败");
        }
    }


    @Override
    public RequestResult alterOrganization(Organization organization) {
        organizationDao.updateOrganization(organization);
        return new RequestResult(1, "修改组织成功");
    }

    @Override
    public RequestResult deleteOrganization(int organizationId, int userId) throws IOException {
        Organization o = organizationDao.getById(organizationId);
        if (o == null) {
            throw new OrganizationException(StatEnum.ORGANIZATION_NOT_EXIST);
        }
        if (o.getTeacherId() != userId) {
            throw new OrganizationException(StatEnum.NOT_HAVE_POWER);
        }
        int flag = organizationDao.deleteOrganization(organizationId);

        if (flag == 1) {
            // 删除关联
            List<User> users = organizationDao.getOrganizationPeople(organizationId);
            organizationDao.deleteOrganizationUserRelation(organizationId);
            if (!users.isEmpty()) {
                Message message = Message.builder()
                        .userId(userId)
                        .title("组织公告")
                        .content("您所在的组织\"" + o.getOrganizationName() + "\"" + "已经被创建人解散")
                        .publisher(o.getTeacherName())
                        .createTime(DateUtil.format(new Date()))
                        .build();
                List<Integer> userIds = new ArrayList<>();
                for (User user : users) {
                    userIds.add(user.getUserId());
                }
                messageDao.insertMessage(message);
                messageDao.insertUserMessage(message.getMessageId(), userIds);
                OnlineWebSocket.publishMessage(message, userIds, message.getPublisher());
            }
            return new RequestResult(1, "删除组织成功");
        } else {
            return new RequestResult(0, "删除组织失败");
        }
    }

    @Override
    public RequestResult<List<Organization>> getMyOrganization(int userId) {
        List<Organization> organizations = organizationDao.getMyOrganization(userId);
        if (organizations.isEmpty()) {
            throw new OrganizationException(StatEnum.MY_ORGANIZATION_IS_NULL);
        }
        for (Organization o : organizations) {
            o.setCount(organizationDao.getOrganizationCount(o.getOrganizationId()));
        }
        return new RequestResult<>(1, "获取成功", organizations);
    }

    @Override
    public RequestResult<List<User>> getOrganizationPeople(int organizationId) {
        if (organizationDao.getById(organizationId) == null) {
            throw new OrganizationException(StatEnum.ORGANIZATION_NOT_EXIST);
        }
        List<User> users = organizationDao.getOrganizationPeople(organizationId);
        if (users.isEmpty()) {
            return new RequestResult<>(0, "该组织还没有学生");
        }
        return new RequestResult<>(1, "获取成功", users);
    }
}
