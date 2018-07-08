package com.jf.mydemo.mytest.Thread.MultiThread.myThreadInterruptTest;

/**
 * @Author wangjie
 * @Date 2018-06-07 00:09
 * @Description 线程冻结的测试类
 */
public class InterruptedDemo implements Runnable{

    private boolean flag = true;

    public synchronized void run() {
        /**
         * 当改变标记时，线程任务结束，线程停止
         */
        while(flag){
            try {
                System.out.println(Thread.currentThread().getName()+this.getClass().getName());
                this.wait();
            } catch (InterruptedException e) {
                changeFlag();
                System.out.println(Thread.currentThread().getName()+"-exception--"+e.toString());
            }
        }
        System.out.println(Thread.currentThread().getName()+"------run method over");
    }

    public void changeFlag(){
        this.flag = false;
    }
}
