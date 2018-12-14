package com.qg.anywork.exception.mail;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author ming
 */
public class MailSendException extends AbstractAnyWorkException {

    public MailSendException(StatEnum statEnum) {
        super(statEnum);
    }
}
