package com.qg.anywork.dao;

import com.qg.anywork.model.po.CollectionQuestion;
import com.qg.anywork.model.po.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author FunriLy
 * @date 2017/7/12
 * From small beginnings comes great things.
 */
@Mapper
@Repository
public interface QuestionDao {

    /**
     * 插入题目
     *
     * @param question 题目实体
     * @return int
     */
    int insertQuestion(@Param("question") Question question);

    /**
     * 批量插入问题
     *
     * @param questionList 问题集合
     * @return int
     */
    int insertAllQuestion(@Param("questionList") List<Question> questionList);

    /**
     * 根据试卷id删除所有的题目
     *
     * @param testPaperId 试卷ID
     * @return int
     */
    int deleteQuestion(@Param("testPaperId") int testPaperId);

    /**
     * 收藏题目
     *
     * @param collectionQuestion collectionQuestion
     * @return int
     */
    int collectQuestion(@Param("collectionQuestion") CollectionQuestion collectionQuestion);

    /**
     * 根据用户ID和问题ID查找数量
     *
     * @param userId     用户ID
     * @param questionId 问题ID
     * @return 数量
     */
    int findCollectedQuestion(@Param("userId") Integer userId, @Param("questionId") Integer questionId);

    /**
     * 删除已收藏的题目
     *
     * @param studentId  学生ID
     * @param questionId 问题ID
     * @return int
     */
    int deleteCollection(@Param("studentId") Integer studentId, @Param("questionId") Integer questionId);

    /**
     * 检查该题是否已经收藏
     *
     * @param studentId  　学生ＩＤ
     * @param questionId 　问题ＩＤ
     * @return 影响的行数
     */
    int checkQuestionIfCollected(@Param("studentId") Integer studentId, @Param("questionId") Integer questionId);

    /**
     * 根据id获取题目
     *
     * @param questionId id
     * @return 题目
     */
    String findContentById(@Param("questionId") Integer questionId);

    /**
     * 根据学生ID获取收藏题目id列表
     *
     * @param studentId 学生ID
     * @return id列表
     */
    List<Integer> findQuestionListByStudentId(@Param("studentId") Integer studentId);
}
