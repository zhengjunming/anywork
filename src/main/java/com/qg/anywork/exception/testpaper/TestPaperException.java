package com.qg.anywork.exception.testpaper;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * Create by ming on 18-8-12 上午10:02
 *
 * @author ming
 * I'm the one to ignite the darkened skies.
 */
public class TestPaperException extends AbstractAnyWorkException {
    public TestPaperException(StatEnum statEnum) {
        super(statEnum);
    }
}
