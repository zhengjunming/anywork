package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author FunriLy
 * @date 2017/7/14
 * From small beginnings comes great things.
 */
public class UserException extends AbstractAnyWorkException {

    public UserException(StatEnum statEnum) {
        super(statEnum);
    }
}
