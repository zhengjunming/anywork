package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * @author FunriLy
 * @date 2017/7/10
 * From small beginnings comes great things.
 */
public class ValcodeWrongException extends UserException {

    public ValcodeWrongException(StatEnum statEnum) {
        super(statEnum);
    }
}
