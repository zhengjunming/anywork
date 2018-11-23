package com.qg.anywork.dao;

import com.qg.anywork.model.po.Chapter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author logan
 * @date 2017/7/11
 * 对于章节的数据库操作
 */
@Mapper
@Repository
public interface ChapterDao {

    /***
     * 根据组织id获取章节列表
     *
     * @param organizationId 组织id
     * @return List<Chapter> 返回章节列表
     */
    List<Chapter> getByOrganizationId(@Param("organizationId") int organizationId);

    /**
     * 根据userId获取章节
     *
     * @param userId userId
     * @return 章节列表
     */
    List<Chapter> getByUserId(@Param("userId") Integer userId);

    /***
     * 增加章节
     * @param chapter 章节
     * @return 1为成功，0为失败
     */
    int addChapter(@Param("chapter") Chapter chapter);

    /***
     * 删除章节
     * @param chapterId 章节ID
     * @return 1为成功，0为失败
     */
    int deleteChapter(@Param("chapterId") int chapterId);

    /***
     * 更新章节
     * @param chapter
     * @return
     */
    int updateChapter(@Param("chapter") Chapter chapter);

    /**
     * 查找组织ID
     *
     * @param chapterId 章节ID
     * @return int
     */
    int getOrganizationIdByChapterId(@Param("chapterId") Integer chapterId);
}
