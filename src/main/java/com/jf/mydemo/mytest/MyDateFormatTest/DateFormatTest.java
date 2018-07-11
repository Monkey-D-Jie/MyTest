package com.jf.mydemo.mytest.MyDateFormatTest;

import com.jf.mydemo.mytest.MyDateFormatTest.DateFormatUtils.MyDateFormatUtil;

import java.text.ParseException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-11 15:15
 * @Description: 日期转换类的测试方法
 * To change this template use File | Settings | File and Templates.
 */

public class DateFormatTest {
    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName()+":"+ MyDateFormatUtil.parse4("2013-05-24 06:02:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            new TestSimpleDateFormatThreadSafe().start();
        }

    }
}

