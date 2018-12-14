package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class EmptyUserException extends UserException {

    public EmptyUserException(StatEnum statEnum) {
        super(statEnum);
    }
}
