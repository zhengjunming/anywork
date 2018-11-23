package com.qg.anywork.dao;

import com.qg.anywork.model.po.Organization;
import com.qg.anywork.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author logan
 * @date 2017/7/11
 * 对于组织的数据库操作
 */
@Mapper
@Repository
public interface OrganizationDao {

    /***
     * 通过关键字查找相关组织
     * @param organizationName 组织名关键字
     * @return List<Organization> 组织列表
     */
    List<Organization> getByKeyWords(@Param("organizationName") String organizationName);

    /***
     * 查看用户加入的组织
     *
     * @param userId 用户
     * @return List<Organization> 用户加入的组织列表
     */
    List<Organization> getByUserId(@Param("userId") int userId);

    /***
     * 加入组织
     * @param organizationId 组织id
     * @param userId 用户id
     * @return int 返回影响的行数 1即成功 0即失败
     */
    int joinOrganization(@Param("organizationId") int organizationId, @Param("userId") int userId);

    /***
     * 查看是否加入过组织
     * @param organizationId 组织id
     * @param userId 用户id
     * @return int 返回满足条件的行数 1即加入 0即未加入
     */
    int isJoin(@Param("organizationId") int organizationId, @Param("userId") int userId);

    /***
     * 根据组织id查找组织
     * @param organizationId 组织id
     * @return Organization 组织
     */
    Organization getById(@Param("organizationId") int organizationId);

    /***
     * 退出组织接口
     * @param organizationId  组织id
     * @param userId 用户id
     * @return int
     */
    int exitOrganization(@Param("organizationId") int organizationId, @Param("userId") int userId);

    /***
     * 获取当前组织下的人数
     * @param organizationId 组织ＩＤ
     * @return 组织人数
     */
    int getOrganizationCount(@Param("organizationId") int organizationId);

    /**
     * 根据组织名称查找组织
     *
     * @param organizationName 组织名称
     * @return 组织
     */
    Organization findByOrganizationName(@Param("organizationName") String organizationName);

    /***
     * 添加组织
     * @param organization 组织
     * @return int
     */
    int addOrganization(@Param("organization") Organization organization);

    /***
     * 更新组织信息
     * @param organization 组织
     * @return int
     */
    int updateOrganization(@Param("organization") Organization organization);

    /***
     * 删除组织
     * @param organizationId 组织ID
     * @return int
     */
    int deleteOrganization(@Param("organizationId") int organizationId);

    /**
     * 删除该组织对应的所有成功关系记录
     *
     * @param organizationId 组织ID
     * @return int
     */
    int deleteOrganizationUserRelation(@Param("organizationId") int organizationId);

    /***
     * 查看我创建过的组织
     * @param userId userId
     * @return Organization List
     */
    List<Organization> getMyOrganization(@Param("userId") int userId);

    /**
     * 查找我创建的所有组织的ID
     *
     * @param userId 教师ID
     * @return 组织ID集合
     */
    List<Integer> getMyOrganizationIds(@Param("userId") Integer userId);

    /***
     * 获得该组织下的成员列表
     * @param organizationId 组织ID
     * @return User　List
     */
    List<User> getOrganizationPeople(@Param("organizationId") int organizationId);

    /**
     * 根据组织ID获取学生ID集合
     *
     * @param organizationId 组织ID
     * @return 学生ID集合
     */
    List<Integer> getUserIdsByOrganizationId(@Param("organizationId") int organizationId);

    /**
     * 获取与该组织同个创建人的全部组织
     *
     * @param organizationId 组织ID
     * @return 组织集合
     */
    List<Organization> findOrganizationById(@Param("organizationId") int organizationId);

    /**
     * 根据用户ID查找与组织的关系记录
     *
     * @param userId 用户ID
     * @return 数量，0或1
     */
    int findRelationByUserId(@Param("userId") int userId);

    /**
     * 根据用户ID找组织ID
     *
     * @param userId 用户ID
     * @return 组织ID
     */
    Integer findOrganizationByUserId(@Param("userId") int userId);
}
