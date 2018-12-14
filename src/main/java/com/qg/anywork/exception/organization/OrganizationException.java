package com.qg.anywork.exception.organization;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author ming
 */
public class OrganizationException extends AbstractAnyWorkException {

    public OrganizationException(StatEnum statEnum) {
        super(statEnum);
    }
}
