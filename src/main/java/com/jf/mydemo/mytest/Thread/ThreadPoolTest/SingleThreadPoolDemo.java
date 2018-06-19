package com.jf.mydemo.mytest.Thread.ThreadPoolTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-19 14:52
 * @Description: 单一线程线程池的创建
 * To change this template use File | Settings | File and Templates.
 */

public class SingleThreadPoolDemo {

    public static class SingleThreadDemo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程" + Thread.currentThread().getName() + "---->>" + i);
            }
        }
    }

    public static void main(String[] args) {
        //老方法是这样的---》创建一个大小为2的线程池
       /* ExecutorService es=Executors.newSingleThreadExecutor();
        for(int i=0;i<10;i++){
            SingleThreadDemo st=new SingleThreadDemo();
            es.execute(ft);
        }
        es.shutdown();*/
        //阿里规约创建方法
        //Thread创建工厂
        /**
         * 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
         * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
         */
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Orders-%d").build();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory, handler);
        for (int i = 0; i < 10; i++) {
            System.out.println("-------"+i);
            SingleThreadDemo st = new SingleThreadDemo();
            threadPoolExecutor.execute(st);
        }
        threadPoolExecutor.shutdown();

    }
}
