package com.jf.mydemo.mytest.Thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/4/17 0017
 * @time: 15:02
 * To change this template use File | Settings | File and Templates.
 */

public class ThreadPool implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }

        System.out.println("----------------------------------------");
    }
}
