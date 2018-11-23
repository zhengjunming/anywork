package com.qg.anywork.service;

import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.Chapter;

import java.util.List;

/**
 * @author logan
 * @date 2017/7/11
 */
public interface ChapterService {

    /***
     * 根据组织ID获取章节列表
     *
     * @param organizationId 组织ID
     * @return 章节列表
     */
    RequestResult<List<Chapter>> getByOrganizationId(int organizationId);

    /***
     * 添加章节
     * @param userId userId
     * @param chapter 章节
     * @return 章节信息
     */
    RequestResult addChapter(int userId, Chapter chapter);

    /***
     * 删除章节
     * @param chapterId 章节ID
     * @return Request result
     */
    RequestResult deleteChapter(int chapterId);

    /**
     * 修改章节信息
     *
     * @param chapterId   章节ID
     * @param chapterName 章节名称
     * @return result
     */
    RequestResult updateChapter(int chapterId, String chapterName);
}
