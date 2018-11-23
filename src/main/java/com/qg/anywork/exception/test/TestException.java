package com.qg.anywork.exception.test;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author logan
 * @date 2017/7/11
 */
public class TestException extends AbstractAnyWorkException {

    public TestException(StatEnum statEnum) {
        super(statEnum);
    }
}
