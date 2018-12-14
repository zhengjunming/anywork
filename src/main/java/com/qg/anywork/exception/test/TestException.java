package com.qg.anywork.exception.test;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author ming
 */
public class TestException extends AbstractAnyWorkException {

    public TestException(StatEnum statEnum) {
        super(statEnum);
    }
}
