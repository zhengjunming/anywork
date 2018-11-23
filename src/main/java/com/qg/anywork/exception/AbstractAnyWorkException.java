package com.qg.anywork.exception;

import com.qg.anywork.enums.StatEnum;

/**
 * Create by ming on 18-8-5 上午9:58
 * <p>
 * AnyWork主异常
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public abstract class AbstractAnyWorkException extends RuntimeException {

    private StatEnum statEnum;

    public AbstractAnyWorkException(StatEnum statEnum) {
        super(statEnum.getStateInfo());
        this.statEnum = statEnum;
    }

    public StatEnum getStatEnum() {
        return statEnum;
    }
}
