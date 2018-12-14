package com.qg.anywork.exception.testpaper;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class NotPowerException extends TestPaperException {

    public NotPowerException(StatEnum statEnum) {
        super(statEnum);
    }
}
