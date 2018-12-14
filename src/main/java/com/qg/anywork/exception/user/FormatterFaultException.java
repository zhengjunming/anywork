package com.qg.anywork.exception.user;

import com.qg.anywork.enums.StatEnum;
/**
 * @author ming
 */
public class FormatterFaultException extends UserException {

    public FormatterFaultException(StatEnum statEnum) {
        super(statEnum);
    }
}
