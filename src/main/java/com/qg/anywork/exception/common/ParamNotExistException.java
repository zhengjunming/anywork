package com.qg.anywork.exception.common;

import com.qg.anywork.enums.StatEnum;

/**
 * Create by ming on 18-8-14 上午9:01
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public class ParamNotExistException extends ParamException {
    public ParamNotExistException(StatEnum statEnum) {
        super(statEnum);
    }
}
