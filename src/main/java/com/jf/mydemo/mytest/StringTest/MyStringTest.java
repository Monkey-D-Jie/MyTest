package com.jf.mydemo.mytest.StringTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-04 10:21
 * @Description: String类型数据相关知识点的测试类
 * To change this template use File | Settings | File and Templates.
 */

public class MyStringTest {
    public static void main(String[] args) {
        //线程不安全的
        StringBuilder stringBuilder = new StringBuilder();
        //线程安全
        StringBuffer stringBuffer = new StringBuffer();
        /**
         * 如何来体现它俩的线程安全和不安全呢？？
         * 线程安全与否，在其对象的源码中又是怎样表现的哇？
         * StringBuilder在源码中有说明。
         * StringBuffer中最突出的表现就是
         * 加了锁关键字 synchronized！
         */

        MultiThreadString multiThreadString = new MultiThreadString(stringBuilder,stringBuffer);
        for (int i = 0; i < 10; i++) {
            /**
             * 测试方法开启了10个线程，每一个线程都能执行到append方法的话，对象的总长度应该为10*1000.
             * 多次测试后发现：
             * StringBuffer始终都能保证最终长度和理论上的一致。
             * StringBuilder则是会出现长度不正确的情况，有时甚至会报出IndexOutOfBounds的异常。
             * 这就是StringBuilder线程不安全的例证
             */
            Thread thread = new Thread(multiThreadString);
            thread.start();
        }
    }
}
