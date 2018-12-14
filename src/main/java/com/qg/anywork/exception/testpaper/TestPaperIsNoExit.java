package com.qg.anywork.exception.testpaper;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class TestPaperIsNoExit extends TestPaperException {

    public TestPaperIsNoExit(StatEnum statEnum) {
        super(statEnum);
    }
}
