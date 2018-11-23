package com.qg.anywork.util;

import org.springframework.util.DigestUtils;

/**
 * Created by FunriLy on 2017/7/10.
 * From small beginnings comes great things.
 */
public class Encryption {

    /**
     * md5盐值字符串
     */
    private static final String SLAT = "livid";

    public static String getMD5(String string) {
        String base = string + "/" + SLAT;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }
}
