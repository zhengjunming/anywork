package com.qg.anywork.dao;

import com.qg.anywork.model.po.Question;
import com.qg.anywork.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis 数据库缓存操作
 *
 * @author FunriLy
 * @date 2017/7/12
 * From small beginnings comes great things.
 */
@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @SuppressWarnings("unchecked")
    public void addQuestionList(int userId, ArrayList<Question> list) {
        String userStr = String.valueOf(userId);
        synchronized (list) {
            for (Question question : list) {
                if (redisTemplate.opsForList().size(userStr) >= 100) {
                    redisTemplate.opsForList().rightPop(userStr);
                }
                redisTemplate.opsForList().leftPush(userStr, question);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void removeQuestionList(int userId) {
        String userStr = String.valueOf(userId);
        while (redisTemplate.opsForList().size(userStr) > 0) {
            // TODO: 2017/7/13 清除不彻底
            redisTemplate.opsForList().rightPop(userStr);
        }
    }

    @SuppressWarnings("unchecked")
    public List getQuestionList(int userId) {
        return redisTemplate.opsForList().range(String.valueOf(userId), 0, -1);
    }

    @SuppressWarnings("unchecked")
    public void addUserMessage(String email, User user) {
        synchronized (user) {
            // 清除已经有的缓存信息
            removeUserMessage(email);
            redisTemplate.opsForList().leftPush(email, user);
        }
    }

    @SuppressWarnings("unchecked")
    private void removeUserMessage(String email) {
        while (redisTemplate.opsForList().size(email) > 0) {
            redisTemplate.opsForList().rightPop(email);
        }
    }

    @SuppressWarnings("unchecked")
    public User getUserMessage(String email) {
        User user = null;
        try {
            user = (User) redisTemplate.opsForList().rightPop(email);
        } catch (Exception e) {
            System.out.println("Redis 获取用户信息缓存失败，" + email);
            e.printStackTrace();
        }
        if (user != null) {
            // 清除
            removeUserMessage(email);
        }
        return user;
    }
}
