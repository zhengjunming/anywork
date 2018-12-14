package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class UserLoginFailException extends UserException {

    public UserLoginFailException(StatEnum statEnum) {
        super(statEnum);
    }
}
