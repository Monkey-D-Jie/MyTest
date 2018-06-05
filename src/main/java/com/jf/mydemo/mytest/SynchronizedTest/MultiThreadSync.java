package com.jf.mydemo.mytest.SynchronizedTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/7 0007
 * @time: 18:05
 * To change this template use File | Settings | File and Templates.
 *
 * 验证
 * synchronized
 * 和
 * synchronized (this ) {}
 * 是一个效果的测试用例
 * 测试结果：
 * 输入内容为
m1 call
m1 call done
m2 call
m2 call done
即要等其中一个执行完后，另一个才能执行。
 说明加锁的对象是同一个。故猜测得证！
 */

public class MultiThreadSync {

    public synchronized void m1() throws InterruptedException{
        System. out.println("m1 call" );
        Thread. sleep(2000);
        System. out.println("m1 call done" );
    }

    public void m2() throws InterruptedException{
        synchronized (this ) {
            System. out.println("m2 call" );
            Thread. sleep(2000);
            System. out.println("m2 call done" );
        }
    }
    public static void main(String[] args) {
        final MultiThreadSync thisObj  = new MultiThreadSync();

        Thread t1 = new Thread(){
            @Override
            public void run() {
                try {
                    thisObj.m1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t2 = new Thread(){
            @Override
            public void run() {
                try {
                    thisObj.m2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        t2.start();

    }

}
