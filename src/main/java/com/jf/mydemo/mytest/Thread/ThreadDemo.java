package com.jf.mydemo.mytest.Thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-07 10:36
 * @Description: 可以执行的线程测试类
 * To change this template use File | Settings | File and Templates.
 */

public class ThreadDemo implements Runnable {

    private int number = 10;

    public void method1() {
        synchronized (this) {
        while (true) {
                number = number + 20;
                System.out.println(Thread.currentThread().getName() + "---->>>" + number);
                if(number == 3000){
                    break;
                }
            }
        }
    }

    public void method2() throws InterruptedException {
        synchronized (this) {
            number *= 200;
            /*System.out.println(Thread.currentThread().getName()+"获取到"+this.getClass().getName()+"对象的锁,并进入休眠状态");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName()+"休眠结束");*/
            /**
             * sleep
             * 测试结果：在这块儿的线程休眠期间，method1中的打印方法并不会被执行
             * 说明 sleep 不会释放锁、、、、
             * 那怎么证明它是把cpu的执行权让出去的呢？
             * -----
             * 新增了一个线程对象----在method2方法之前先开启
             * 测试结果：
             * main获取到com.jf.mydemo.mytest.Thread.ThreadDemo对象的锁,并进入休眠状态
             别的线程阻塞没关系，我只管输出就好------0
             别的线程阻塞没关系，我只管输出就好------1
             ......
             main休眠结束

             说明：在method2方法中休眠后，cpu还能继续执行其他的线程。
             结论：sleep 不会释放锁，会让出CPU执行权
             */
            System.out.println(Thread.currentThread().getName()+"获取到"+this.getClass().getName()+"对象的锁,并进入wait()状态");
            this.wait(2000);
            System.out.println(Thread.currentThread().getName()+"wait结束");
            /**
             * 测试结果
             * main获取到com.jf.mydemo.mytest.Thread.ThreadDemo对象的锁,并进入wait()状态
             别的线程阻塞没关系，我只管输出就好------0
             我是线程---->>>2020
             别的线程阻塞没关系，我只管输出就好------1
             别的线程阻塞没关系，我只管输出就好------2
             别的线程阻塞没关系，我只管输出就好------3
             别的线程阻塞没关系，我只管输出就好------4
             .....
             结论：说明 wait 会释放锁，也会让出cpu执行权
             综上所述：
                1.sleep和wait都能让线程进入停止的状态。
                2.sleep不会释放对象锁，wait会释放对象锁；
                3.两者都不影响cpu的执行权切换
             -----另外一个 问题，，，调用wait()的时候，线程中IllegalMonitorStateException
             的异常是因何产生的呢？
                monitor监视器 ---  同步锁 间的关系
             */


        }
    }

    @Override
    public void run() {
        /*try {
            System.out.println("[[[[[[[[[[[[[[[[[[[[[[[[[[[[[");
            System.out.println(this.getClass().getName()+"---run.....");
            System.out.println(Thread.currentThread().getName()+"----run.....");
            System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]");
            this.wait();
        } catch (Exception e) {
            System.out.println("在run方法中.......");
            e.printStackTrace();
        }*/
        method1();
    }
}
