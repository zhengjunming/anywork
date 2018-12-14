package com.qg.anywork.exception.chapter;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author ming
 */
public class ChapterException extends AbstractAnyWorkException {

    public ChapterException(StatEnum statEnum) {
        super(statEnum);
    }
}