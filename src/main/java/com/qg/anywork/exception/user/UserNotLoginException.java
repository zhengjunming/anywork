package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class UserNotLoginException extends UserException {

    public UserNotLoginException(StatEnum statEnum) {
        super(statEnum);
    }
}
