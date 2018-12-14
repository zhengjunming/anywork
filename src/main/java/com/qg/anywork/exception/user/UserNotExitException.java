package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class UserNotExitException extends UserException {

    public UserNotExitException(StatEnum statEnum) {
        super(statEnum);
    }
}
