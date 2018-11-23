package com.qg.anywork.service.impl;

import com.qg.anywork.dao.RedisDao;
import com.qg.anywork.domain.UserRepository;
import com.qg.anywork.model.dto.RequestResult;
import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.mail.MailSendException;
import com.qg.anywork.exception.user.UserNotExitException;
import com.qg.anywork.model.po.User;
import com.qg.anywork.service.MailService;
import com.qg.anywork.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by ming on 18-8-5 上午10:38
 * <p>
 * 邮箱服务实现类
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RedisDao redisDao;

    @Autowired
    private UserRepository userRepository;

    @Override
    public RequestResult<?> sendPasswordMail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotExitException(StatEnum.NOT_FOUND_USER_USE_THIS_EMAIL);
        }
        mailUtil.send(email, user.getUserName(), 2);
        return new RequestResult<>(StatEnum.MAIL_SEND_SUCCESS);
    }

    @Override
    public RequestResult<Integer> sendRegisterMail(User user) throws MailSendException {
        String email = user.getEmail();
        // 发送验证邮件
        mailUtil.send(email, user.getUserName(), 1);
        // 将资料存入缓存
        redisDao.addUserMessage(email, user);
        return new RequestResult<>(StatEnum.MAIL_SEND_SUCCESS);
    }
}
