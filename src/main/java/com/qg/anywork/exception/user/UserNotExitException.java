package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * Created by FunriLy on 2017/7/10.
 * From small beginnings comes great things.
 */
public class UserNotExitException extends UserException {

    public UserNotExitException(StatEnum statEnum) {
        super(statEnum);
    }
}
