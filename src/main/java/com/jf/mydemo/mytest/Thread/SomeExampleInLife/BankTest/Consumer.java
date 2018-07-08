package com.jf.mydemo.mytest.Thread.SomeExampleInLife.BankTest;

/**
 * Created by wjie on 2018/5/20.
 * 对比存钱的顾客
 */
public class Consumer implements Runnable{
    Bank bank = new Bank();
    public void run() {
        for (int i = 0; i < 3; i++) {
            //初始金额设定为100
            bank.add(100);
        }
    }
}
