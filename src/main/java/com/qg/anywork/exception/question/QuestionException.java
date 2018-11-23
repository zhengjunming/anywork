package com.qg.anywork.exception.question;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * Create by ming on 18-8-12 上午10:00
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public class QuestionException extends AbstractAnyWorkException {
    public QuestionException(StatEnum statEnum) {
        super(statEnum);
    }
}
