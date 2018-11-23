package com.qg.anywork.service;

import com.qg.anywork.model.dto.RequestResult;

/**
 * @author FunriLy
 * @date 2017/7/13
 * From small beginnings comes great things.
 */
public interface QuestionService {

    /**
     * 收藏题目
     *
     * @param userId     用户ID
     * @param questionId 问题ID
     * @return Request result
     */
    RequestResult collectQuestion(int userId, int questionId);

    /**
     * 删除已收藏的题目
     *
     * @param userId     用户ID
     * @param questionId 问题ID
     * @return 结果
     */
    RequestResult deleteCollection(int userId, int questionId);

    /**
     * 列出收藏的题目
     *
     * @param userId 用户ID
     * @return list
     */
    RequestResult listCollectionQuestion(int userId);
}
