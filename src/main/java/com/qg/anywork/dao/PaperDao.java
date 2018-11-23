package com.qg.anywork.dao;

import com.qg.anywork.model.po.TestPaper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Create by ming on 18-10-5 下午11:24
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Mapper
@Repository
public interface PaperDao {

    /**
     * 添加试卷记录
     *
     * @param testPaper 试卷实体
     * @return int
     */
    int insertTestPaper(@Param("testPaper") TestPaper testPaper);

    /**
     * 删除试卷记录
     *
     * @param testPaperId 试卷ID
     * @return int
     */
    int deleteTestPaper(@Param("testPaperId") Integer testPaperId);


    /**
     * 插入组织与试卷的关系记录
     *
     * @param testPaperId     试卷ID
     * @param organizationIds 组织ID集合
     * @return int
     */
    int insertTestPaperOrganization(@Param("testPaperId") Integer testPaperId, @Param("organizationIds") List<Integer> organizationIds);

    /**
     * 更新试卷的分数
     *
     * @param testPaperId 试卷ID
     * @param score       试卷分数
     * @return int
     */
    int updateTestPaperScore(@Param("testPaperId") Integer testPaperId, @Param("score") Integer score);

    /**
     * 修改试卷信息
     *
     * @param testPaper 试卷实体
     * @return int
     */
    int updateTestPaperInfo(@Param("testPaper") TestPaper testPaper);

    /**
     * 根据ID查找试卷
     *
     * @param testPaperId 试卷ID
     * @return 试卷
     */
    TestPaper findByTestPaperId(@Param("testPaperId") Integer testPaperId);

    /**
     * 获取主要信息
     *
     * @param testPaperId 试卷ID
     * @return 试卷
     */
    TestPaper findTestPaperMainInfoById(@Param("testPaperId") Integer testPaperId);

    /**
     * 删除试卷与组织的关系记录
     *
     * @param testPaperId 试卷ID
     * @return int
     */
    int deleteTestPaperOrganizationRelation(@Param("testPaperId") Integer testPaperId);

    /**
     * 列出组织中的试卷
     *
     * @param organizationId 组织ID
     * @return 试卷列表
     */
    List<TestPaper> listTestPaperByOrganizationId(@Param("organizationId") Integer organizationId);

    /**
     * 根据组织查找试卷
     *
     * @param organizationId 组织ID
     * @param createTime     创建时间
     * @return 试卷
     */
    List<TestPaper> findTestPaperByOrganizationIdAndTime(@Param("organizationId") int organizationId, @Param("createTime") Timestamp createTime);

    /**
     * 获取试卷平均分
     *
     * @param userIds     学生ID集合
     * @param testPaperId 试卷ID
     * @return 平均分
     */
    double getAverageScore(@Param("userIds") List<Integer> userIds, @Param("testPaperId") Integer testPaperId);

    /**
     * 获取一道题的正确人数
     *
     * @param userIds    用户ID集合
     * @param questionId 问题ID
     * @return 正确人数
     */
    int getQuestionCorrectNumber(@Param("userIds") List<Integer> userIds, @Param("questionId") Integer questionId);

    /**
     * 根据作者查找试卷
     *
     * @param userId 作者
     * @return 试卷ID集合
     */
    List<Integer> findTestPaperIdsByUserId(@Param("userId") Integer userId);
}
