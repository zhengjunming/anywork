package com.qg.anywork.exception.question;


import com.qg.anywork.enums.StatEnum;

/**
 * @author FunriLy
 * @date 2017/7/13
 * From small beginnings comes great things.
 */
public class RedisNotExitException extends QuestionException {

    public RedisNotExitException(StatEnum statEnum) {
        super(statEnum);
    }
}
