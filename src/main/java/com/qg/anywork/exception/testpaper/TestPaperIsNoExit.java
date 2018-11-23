package com.qg.anywork.exception.testpaper;

import com.qg.anywork.enums.StatEnum;

/**
 * @author FunriLy
 * @date 2017/8/18
 * From small beginnings comes great things.
 */
public class TestPaperIsNoExit extends TestPaperException {

    public TestPaperIsNoExit(StatEnum statEnum) {
        super(statEnum);
    }
}
