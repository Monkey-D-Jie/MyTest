package com.jf.mydemo.mytest.Thread.SomeExampleInLife.BankTest;

/**
 * Created by wjie on 2018/5/20.
 */
public class ConsumerTest {
    public static void main(String[] args) {
        Consumer consumer = new Consumer();

        Thread t1 = new Thread(consumer,"consumer01");
        Thread t2 = new Thread(consumer,"consumer02");
        t1.start();
        t2.start();
    }
}
