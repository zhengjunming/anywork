package com.qg.anywork.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Create by ming on 18-9-14 下午1:28
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Repository
@SuppressWarnings("unchecked")
public class LeaderBoardRedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    public void setLeaderBoard(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, 1, TimeUnit.MINUTES);
    }

    public void removeLeaderBoard(String key) {
        redisTemplate.delete(key);
    }

    public Object getLeaderBoard(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
