package com.jf.mydemo.mytest.Thread.YeildThreadTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-07 18:06
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class Consumer extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("I am Consumer : Consumed Item " + i);
            Thread.yield();
        }
        System.out.println("---------------------------------");
    }
}
