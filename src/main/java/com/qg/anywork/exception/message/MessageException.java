package com.qg.anywork.exception.message;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * Create by ming on 18-8-12 上午11:13
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public class MessageException extends AbstractAnyWorkException {
    public MessageException(StatEnum statEnum) {
        super(statEnum);
    }
}
