package com.jf.mydemo.mytest.Thread.CountLatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-20 16:45
 * @Description: 参加跑步比赛的运动员
 * To change this template use File | Settings | File and Templates.
 */

public class Player implements Runnable{
    /**
     * 相当于参赛选手的起跑位置（每个选手只能有一个位置）
     */
    private CountDownLatch begin;
    /**
     * 终点位置数（每个选手都是在一个赛道上跑，相当于他们各自都拥有一个终点）
     */
    private CountDownLatch end;
    /**
     * 选手编号
     */
    private int number;

    public Player(int number,CountDownLatch begin,CountDownLatch end){
        this.number = number;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            /**
             * 开始执行线程任务
             * 在这里await()就好比 每个参赛队员在起跑位上做好准备。
             * 什么时候比赛才能开始？
             * 当然是所有选手都准备好咯，才能开始比赛嘛。
             *
             */
            System.out.println("Player-"+number+" is ready.....");
            begin.await();
            /**
             * 模拟不同的人跑步：不同的人跑步的快慢不一样
             */
            Thread.sleep((long)(Math.random()*100)*100);
            /**
             * 跑完后，选手最后到达目的地，完成比赛
             */
            System.out.println("Player-"+number+" arrived");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            /**
             * 一个选手完成比赛，其对应终点数就被占用；
             * 即 总的终点数会 -1，
             * 当终点数为0时，说明选手均到达终点，然后再由裁判（主线程）宣布
             * 比赛结束。
             * ---
             * 第一个选手到达终点后，比赛就结束了吗？
             * 当然不是，得是他们都到了才算嘛。
             * 所以，到了终点后，选手们还得等待（等待命令由主线程中的 end.await()操作触发）
             *
             */
            end.countDown();
            System.out.println("run方法中---还有"+end.getCount()+"个选手未到达终点");
        }
    }
}
