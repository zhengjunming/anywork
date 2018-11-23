package com.qg.anywork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * Create by ming on 18-8-21 上午10:40
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Repository
@SuppressWarnings("unchecked")
public class QuestionRedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setQuestionAnswer(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void removeQuestionAnswer(String key) {
        redisTemplate.delete(key);
    }

    public Object getQuestionAnswer(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
