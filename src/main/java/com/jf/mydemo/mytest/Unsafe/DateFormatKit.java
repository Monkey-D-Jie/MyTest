package com.jf.mydemo.mytest.Unsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Data与字符串转化工具类
 * Created by js on 2015/12/31.
 */
public class DateFormatKit {
    /**
     * 记录日志信息
     */
    /**
     * 格式化字符串，年月日时分秒
     */
    public final static String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 格式化字符串，年月日
     */
    public final static String DATE_FORMAT_ONE = "yyyy-MM-dd";
    /**
     * 格式化字符串，时分秒
     */
    public final static String DATE_FORMAT_TWO = "HH:mm:ss";
    /**
     * 格式化字符串，月日
     */
    public final static String DATE_FORMAT_THREE = "MM-dd";

    /**
     * 时间转化成字符串，并且格式化，本类中提供三种格式化字符串
     *
     * @param dateFormat 格式化字符串
     * @param date       待格式化时间
     * @return 格式化后字符串
     */
    public static String convert(final String dateFormat, final Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String time = formatter.format(date);
        return time;
    }

    /**
     * 字符串转化成时间，并且格式化，本类中提供三种格式化字符串
     * 出现异常返回NULL
     *
     * @param dateFormat 格式化字符串
     * @param time       待格式化字符串
     * @return 格式化后时间
     */
    public static Date convert(final String dateFormat, final String time) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
        }
        return date;
    }
}
