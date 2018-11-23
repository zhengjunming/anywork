package com.qg.anywork.exception.common;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * Create by ming on 18-8-14 上午9:00
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public class ParamException extends AbstractAnyWorkException {
    public ParamException(StatEnum statEnum) {
        super(statEnum);
    }
}
