package com.jf.mydemo.mytest.MyDateFormatTest.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-11 16:05
 * @Description: DateFormat的工具类合集
 * To change this template use File | Settings | File and Templates.
 * 这种工具类会有问题：
 * 在多线程的场景下，会因为DateFomat中的setTime(date)生成的值和传入的值不同。
 * 原因就是因为出现了 多个线程公用一个DateFormat对象的情况。
 * 源码说明： DateFormat对象中的Calendar对象，用来设置传入的时期数据。
 * 最终在setTime(date中调用parse(date,pos),最后格式化输出的给定模板类型格式日期的工作是落到了subFormatParse()方法中，
 * calendar.setTime(date)这条语句改变了calendar，稍后，calendar还会用到（在subFormat方法里）。
 *线程A到了setTime()处阻塞，线程B到了setTime()，把date值换成了自己的dateB。线程A醒来后，继续执行setTime()之后的方法。
 * 相当于 是拿着线程B的dateB当自己的dateA使，自然就报错咯。
 * 改进方案：
 * ①：把SimpleDateFormat设为局部变量，这样就能保证Calendar对象的唯一性；(性能一般)
 * ②：在调用解析方法parse或者format方法的时候，加上同步。(性能一般)
 * 叁：用ThreadLocal来创建，每一个线程都能拿到属于自己的该变量对象，自己玩儿，不会影响到别的线程工作。(性能高)
 */

public class MyDateFormatUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static  String formatDate(Date date)throws ParseException {
        return sdf.format(date);
    }

    public static Date parse(String strDate) throws ParseException{
        return sdf.parse(strDate);
    }
    //改进方法①：需要的时候创建实例
    public static  String formatDate2(Date date)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date parse2(String strDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(strDate);
    }
    //改进方法②：对解析和格式化方法添加同步锁---因为锁会导致block状态，所以性能会受一定的影响
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String formatDate3(Date date)throws ParseException{
        synchronized(sdf2){
            return sdf2.format(date);
        }
    }

    public static Date parse3(String strDate) throws ParseException{
        synchronized(sdf2){
            return sdf2.parse(strDate);
        }
    }
    //改进方法③：用threadLocal对象
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Date parse4(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String formatDate4(Date date) {
        return threadLocal.get().format(date);
    }
    /***
     * 调用改进方法后的测试结果。。。。未再出现异常
     * Thread-1:Fri May 24 06:02:20 CST 2013
     Thread-2:Fri May 24 06:02:20 CST 2013
     Thread-0:Fri May 24 06:02:20 CST 2013
     Thread-0:Fri May 24 06:02:20 CST 2013
     Thread-1:Fri May 24 06:02:20 CST 2013
     Thread-2:Fri May 24 06:02:20 CST 2013
     Thread-1:Fri May 24 06:02:20 CST 2013
     Thread-0:Fri May 24 06:02:20 CST 2013
     Thread-2:Fri May 24 06:02:20 CST 2013
     */
}
