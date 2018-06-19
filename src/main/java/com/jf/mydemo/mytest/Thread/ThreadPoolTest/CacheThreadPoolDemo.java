package com.jf.mydemo.mytest.Thread.ThreadPoolTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-19 14:52
 * @Description: 大小不固定线程池的创建
 * To change this template use File | Settings | File and Templates.
 */

public class CacheThreadPoolDemo {

    public static class CacheThreadDemo implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("当前线程" + Thread.currentThread().getName() + "---->>" + i);
            }
        }
    }

    public static void main(String[] args) {
        //Thread创建工厂
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("Orders-%d").build();
        //拒绝策略
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        //老方法是这样的---
      /*  ExecutorService es=Executors.newCachedThreadPool(threadFactory);
        for(int i=0;i<10;i++){
            CacheThreadDemo ct=new CacheThreadDemo();
            es.execute(ct);
        }
        es.shutdown();*/
        //阿里规约创建方法
        /**
         * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
         那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
         此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), threadFactory, handler);
        for (int i = 0; i < 10; i++) {
            CacheThreadDemo ct = new CacheThreadDemo();
            threadPoolExecutor.execute(ct);
        }
        threadPoolExecutor.shutdown();

    }
}
