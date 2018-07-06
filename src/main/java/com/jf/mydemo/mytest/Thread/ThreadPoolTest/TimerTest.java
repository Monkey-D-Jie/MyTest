package com.jf.mydemo.mytest.Thread.ThreadPoolTest;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-06 15:05
 * @Description: 定时器的测试类
 * To change this template use File | Settings | File and Templates.
 */

public class TimerTest {
    private static int count = 0;

    @Test
    public void test() throws InterruptedException {
        /*TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                count++;
                System.out.println(count);
                if(count == 10){
                    this.cancel();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timertask, 100, 200);*/

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                count++;
                System.out.println("executorService------>>>" + count);
                if (count == 10) {
                    executorService.shutdown();
                }
            }
        }, 0, 200, TimeUnit.HOURS);
    }

    /**
     * 面试总结（6）：ScheduledExecutorService的使用 - CSDN博客  https://blog.csdn.net/u011315960/article/details/71422386
     *
     * @param args
     */
//    public class ScheduledExecutorServiceTest {
    public static void main(String[] args) throws InterruptedException {
          /*ScheduledExecutorService executorService2 = Executors.newSingleThreadScheduledExecutor();
            executorService2.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    System.out.println("run "+ System.currentTimeMillis());
                    executorService2.shutdown();
                }
            }, 0, 3000, TimeUnit.MILLISECONDS);
        }*/

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(false).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                count++;
                System.out.println("executorService------>>>" + count);
                if (count == 10) {
                    executorService.shutdown();
                }
            }
        }, 0, 2000, TimeUnit.MILLISECONDS);
//        Thread.sleep(5000);
    }
}
