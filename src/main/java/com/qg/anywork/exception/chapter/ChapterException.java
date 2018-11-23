package com.qg.anywork.exception.chapter;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * Created by logan on 2017/7/11.
 */
public class ChapterException extends AbstractAnyWorkException {

    public ChapterException(StatEnum statEnum) {
        super(statEnum);
    }
}