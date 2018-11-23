package com.qg.anywork.exception.mail;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author FunriLy
 * @date 2017/8/20
 * From small beginnings comes great things.
 */
public class MailSendException extends AbstractAnyWorkException {

    public MailSendException(StatEnum statEnum) {
        super(statEnum);
    }
}
