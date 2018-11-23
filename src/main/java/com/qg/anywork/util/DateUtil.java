package com.qg.anywork.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ming
 */
public class DateUtil {

    private static ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static Date longToDate(long longTime) {
        return new Date(longTime);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }

    public static Date parse(String string) throws ParseException {
        return threadLocal.get().parse(string);
    }

    /**
     * 获取当前时间的时间戳
     *
     * @return 时间戳
     */
    public static Long getStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取当前时间的Date对象
     *
     * @return 当前时间的Date对象
     */
    public static Date getNowDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前时间的SimpleDateFormat对象
     *
     * @return 当前时间的SimpleDateFormat对象
     */
    public static String getNowFormat() {
        return threadLocal.get().format(new Date(System.currentTimeMillis()));
    }

    /**
     * 将毫秒数转化为 Date
     *
     * @param longtime
     * @return
     */
    public static Date changeLongtimeToDate(long longtime) {
        return new Date(longtime);
    }
}
