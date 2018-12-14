package com.qg.anywork.service;

import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.model.po.User;

/**
 * @author ming
 */
public interface MailService {

    /**
     * 发送密码邮箱
     *
     * @param email   email
     * @return request result
     */
    RequestResult<?> sendPasswordMail(String email);

    /**
     * 发送注册邮箱验证
     *
     * @param user 用户
     * @return request result
     */
    RequestResult<Integer> sendRegisterMail(User user);
}
