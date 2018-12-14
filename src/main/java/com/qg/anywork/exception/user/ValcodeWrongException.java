package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class ValcodeWrongException extends UserException {

    public ValcodeWrongException(StatEnum statEnum) {
        super(statEnum);
    }
}
