package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * Create by ming on 18-8-12 上午10:36
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public class PasswordException extends UserException {
    public PasswordException(StatEnum statEnum) {
        super(statEnum);
    }
}
