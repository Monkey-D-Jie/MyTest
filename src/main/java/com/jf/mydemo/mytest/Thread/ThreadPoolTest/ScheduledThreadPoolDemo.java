package com.jf.mydemo.mytest.Thread.ThreadPoolTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-19 14:52
 * @Description: 定时执行线程池的创建
 * To change this template use File | Settings | File and Templates.
 */

public class ScheduledThreadPoolDemo {

    public static class ScheduledThreadDemo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程" + Thread.currentThread().getName() + "---->>" + i);
            }
        }
    }

    public static void main(String[] args) {
        //老方法是这样的---》创建一个大小为2的线程池
       /*ScheduledExecutorService es= Executors.newScheduledThreadPool(2);
       for(int i=0;i<10;i++){
           ScheduledThreadDemo st=new ScheduledThreadDemo();
           //参数1：目标对象
           //参数2：隔多长时间开始执行线程，
           //参数3：执行周期
           //参数4：时间单位
           es.scheduleAtFixedRate(st, 30, 10, TimeUnit.MILLISECONDS);
       }
       es.shutdown();*/
        //阿里规约创建方法----对于定时线程这块儿并没有太多的建议，至少下面的代码并未达到定时执行的效果
        //是一下子就执行完了的
        //Thread创建工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Orders-%d").build();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5), threadFactory, handler);
        for (int i = 0; i < 10; i++) {
            ScheduledThreadDemo st = new ScheduledThreadDemo();
            threadPoolExecutor.execute(st);
        }
        threadPoolExecutor.shutdown();
    }
}
