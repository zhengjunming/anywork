package com.qg.anywork.exception.testpaper;

import com.qg.anywork.enums.StatEnum;

/**
 * @author FunriLy
 * @date 2017/8/18
 * From small beginnings comes great things.
 */
public class NotPowerException extends TestPaperException {

    public NotPowerException(StatEnum statEnum) {
        super(statEnum);
    }
}
