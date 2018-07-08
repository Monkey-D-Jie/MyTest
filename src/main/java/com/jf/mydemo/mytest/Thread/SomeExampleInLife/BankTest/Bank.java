package com.jf.mydemo.mytest.Thread.SomeExampleInLife.BankTest;

/**
 * Created by wjie on 2018/5/20.
 * 简单的模拟银行存款
 */
public class Bank {
    private int sum;
    private Object object = new Object();
    public void add(int num) {
//        synchronized (object){
            sum = sum+num;
            System.out.println(Thread.currentThread().getName()+"存款---->>"+sum);
//        }
    }
}
