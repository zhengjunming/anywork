package com.qg.anywork.exception.question;

import com.qg.anywork.enums.StatEnum;

/**
 * @author ming
 */
public class ExcelReadException extends QuestionException {

    public ExcelReadException(StatEnum statEnum) {
        super(statEnum);
    }
}
