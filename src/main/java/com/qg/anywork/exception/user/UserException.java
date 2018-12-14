package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author ming
 */
public class UserException extends AbstractAnyWorkException {

    public UserException(StatEnum statEnum) {
        super(statEnum);
    }
}
