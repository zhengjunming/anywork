package com.qg.anywork.service.impl;

import com.qg.anywork.dao.QuestionDao;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.question.QuestionException;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.CollectionQuestion;
import com.qg.anywork.service.QuestionService;
import com.qg.anywork.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Create by ming on 18-8-5 下午10:19
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    @Autowired
    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public RequestResult collectQuestion(int userId, int questionId) {
        synchronized (this) {
            if (questionDao.findCollectedQuestion(userId, questionId) == 1) {
                throw new QuestionException(StatEnum.DO_NOT_COLLECT_AGIAN);
            }
            CollectionQuestion collectionQuestion = new CollectionQuestion();
            collectionQuestion.setQuestionId(questionId);
            collectionQuestion.setStudentId(userId);
            collectionQuestion.setCollectionTime(DateUtil.format(new Date()));
            questionDao.collectQuestion(collectionQuestion);
        }
        return new RequestResult(StatEnum.COLLECT_SUCCESS);
    }

    @Override
    public RequestResult deleteCollection(int userId, int questionId) {
        questionDao.deleteCollection(userId, questionId);
        return new RequestResult(StatEnum.DELETE_COLLECTION_SUCCESS);
    }

    @Override
    public RequestResult listCollectionQuestion(int userId) {
        List<Integer> questionIds = questionDao.findQuestionListByStudentId(userId);
        if (questionIds.size() == 0) {
            throw new QuestionException(StatEnum.COLLECTION_LIST_IS_NULL);
        }
        List<Object> data = new ArrayList<>();
        for (int questionId : questionIds) {
            HashMap<String, Object> map = new HashMap<>(2);
            map.put("questionId", questionId);
            map.put("content", questionDao.findContentById(questionId));
            data.add(map);
        }
        return new RequestResult<>(StatEnum.GET_SUCCESS, data);
    }
}
