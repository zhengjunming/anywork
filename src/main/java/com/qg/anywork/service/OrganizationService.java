package com.qg.anywork.service;

import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Organization;
import com.qg.anywork.model.po.User;

import java.io.IOException;
import java.util.List;

/**
 * @author ming
 */
public interface OrganizationService {

    /***
     * 根据组织关键字模糊查找
     *
     * @param organizationName 组织名
     * @param userId 用户ID
     * @return Organization List
     */
    RequestResult<List<Organization>> search(String organizationName, int userId);


    /**
     * 根据组织ID获得组织
     *
     * @param organizationId 组织ID
     * @return 组织
     */
    Organization getById(int organizationId);

    /**
     * 根据组织名称获得组织
     *
     * @param organizationName 组织名称
     * @return 组织
     */
    Organization getByName(String organizationName);

    /***
     * 加入组织
     *
     * @param organizationId 组织ID
     * @param token 口令
     * @param userId 用户ID
     * @return request result
     */
    RequestResult<Organization> join(int organizationId, String token, int userId);

    /***
     * 获取我的组织列表
     *
     * @param userId 用户ID
     * @return Organization List
     */
    RequestResult<List<Organization>> searchByUserId(int userId);

    /***
     * 退出组织
     * @param organizationId 组织ID
     * @param userId 用户ID
     * @return request result
     */
    RequestResult exitOrganization(int organizationId, int userId);

    /***
     * 创建组织
     *
     * @param userId userId
     * @param organization 组织
     * @return request result
     */
    RequestResult addOrganization(Integer userId, Organization organization);

    /***
     * 修改组织
     * @param organization 组织
     * @return request result
     */
    RequestResult alterOrganization(Organization organization);

    /***
     * 删除组织
     * @param organizationId 组织ID
     * @param userId 用户ID
     * @return request result
     * @throws IOException ioException
     */
    RequestResult deleteOrganization(int organizationId, int userId) throws IOException;

    /***
     * 获取我创建过的组织列表
     *
     * @param userId 用户ID
     * @return request result
     */
    RequestResult<List<Organization>> getMyOrganization(int userId);

    /**
     * 获取组织下的成员列表
     *
     * @param organizationId 组织ID
     * @return 学生列表
     */
    RequestResult<List<User>> getOrganizationPeople(int organizationId);
}
