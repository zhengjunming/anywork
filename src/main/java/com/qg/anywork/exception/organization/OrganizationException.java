package com.qg.anywork.exception.organization;

import com.qg.anywork.enums.StatEnum;
import com.qg.anywork.exception.AbstractAnyWorkException;

/**
 * @author logan
 * @date 2017/7/11
 */
public class OrganizationException extends AbstractAnyWorkException {

    public OrganizationException(StatEnum statEnum) {
        super(statEnum);
    }
}
