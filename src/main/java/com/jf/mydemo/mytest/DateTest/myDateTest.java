package com.jf.mydemo.mytest.DateTest;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/27 0027
 * @time: 13:25
 * To change this template use File | Settings | File and Templates.
 */

public class myDateTest {

    @Test
    public void currentMillsTest(){
        long mills = System.currentTimeMillis();
        System.out.println("mills value:"+mills);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        String formateDate = sdf.format(date);
        System.out.println("转化后的时间为:"+formateDate);
    }
}
