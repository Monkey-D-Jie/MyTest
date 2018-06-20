package com.jf.mydemo.mytest.Thread.CountLatchDemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-20 16:49
 * @Description: 运动员跑步比赛测试
 * To change this template use File | Settings | File and Templates.
 */

public class CountLatchDemo {
    private final static int PLAYER_COUNT = 5;

    public static void main(String[] args) {
        /**
         * 每一个选手都有一个 起跑位
         */
        CountDownLatch begin = new CountDownLatch(1);
        /**
         * 一共有PLAYER_COUNT个选手参赛，则对应有PLAYER_COUNT个终点（每个选手跑一条独立赛道）
         */
        CountDownLatch end = new CountDownLatch(PLAYER_COUNT);
        Player[] players = new Player[PLAYER_COUNT];
        /**
         * 给到每个选手 上起跑位的资格
         */
        for (int i = 0; i < PLAYER_COUNT; i++) {
            players[i] = new Player(i+1,begin,end);
        }
        /**
         * 创建执行任务的线程池，
         * 以执行 ‘跑步’的线程任务
         */
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(Thread.currentThread().getName()+"-%d").build();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(PLAYER_COUNT, PLAYER_COUNT, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(5), threadFactory, handler);

//        ExecutorService threadPoolExecutor=Executors.newFixedThreadPool(5);
        /**
         * 放在这里，是为了让打印出的‘开始比赛命令’排在最开头
         */
        System.out.println("Race begins");
        for(Player player : players){
            /**
             * 让起跑位上的每个选手准备就绪
             */
            threadPoolExecutor.execute(player);
        }
        /**
         * 比赛正式开始
         */
        begin.countDown();
        try {
            System.out.println("----------main方法中---还有"+end.getCount()+"个选手未到达终点");
            /**
             * 提前告知 参赛选手到了终点后，要先等待下
             */
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("Race ends");
        }
        threadPoolExecutor.shutdown();
    }

}
