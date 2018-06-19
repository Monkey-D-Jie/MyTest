package com.jf.mydemo.mytest.Thread.ThreadPoolTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-19 14:52
 * @Description: 大小固定线程池的创建
 * To change this template use File | Settings | File and Templates.
 */

public class FixedThreadPoolDemo {

    public static class FixedThreadDemo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程" + Thread.currentThread().getName() + "---->>" + i);
            }
        }
    }

    public static void main(String[] args) {
        //老方法是这样的---》创建一个大小为2的线程池
       /* ExecutorService es=Executors.newFixedThreadPool(2);
        for(int i=0;i<10;i++){
            FixedThreadDemo ft=new FixedThreadDemo();
            es.execute(ft);
        }
        es.shutdown();*/
        //阿里规约创建方法
        //Thread创建工厂
        /**
         * 创建固定大小的线程池。每次提交一个任务就创建一个线程，
         * 直到线程达到线程池的最大大小。
         线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束
         ，那么线程池会补充一个新线程。
         */
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Orders-%d").build();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5), threadFactory, handler);
        for (int i = 0; i < 10; i++) {
            FixedThreadDemo ft = new FixedThreadDemo();
            //一旦抛出了拒绝策略对应的异常后，程序就被挂起了，线程池不会被终止掉
            threadPoolExecutor.execute(ft);
        }
        System.out.println("-----------");
        threadPoolExecutor.shutdownNow();
    }
}
