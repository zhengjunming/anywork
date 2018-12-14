package com.qg.anywork.exception.question;


import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class RedisNotExitException extends QuestionException {

    public RedisNotExitException(StatEnum statEnum) {
        super(statEnum);
    }
}
