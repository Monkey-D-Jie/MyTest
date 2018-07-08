package com.jf.mydemo.mytest.Thread.MultiThread.multiThreadConnection;

/**
 * @Author wangjie
 * @Date 2018-06-03 12:31
 * @Description 待操作的资源实体类
 */
public class Resource {

    private String name;
    public int count = 1;
    //the flag of produce
    private boolean flag = false;

    public synchronized void set(String name) {
        /**
         * 默认为false，
         * 表示装面包的盘子里是没有东西的，所以需要生产面包
         */
        /**
         * 多生产者，多消费者的实验
         */
        while (flag) {
//        if (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.name = name + count;
        count++;
        System.out.println(Thread.currentThread().getName() + "--生产者------" + this.name);
        /**
         * 生产完面包后，对于生产者来说，他就不应该再生产面包了。
         * 即置生产标记flag = true，说明有东西了。
         * 然后，通知消费者，你可以消费了
         */
        flag = true;
//        notify();
        /**
         * 多生产，多消费的情况下直接唤醒全部
         */
        notifyAll();
    }

    public synchronized void out() {
        /**
         * 当flag为false的时候，说明是没有东西的。
         * 所以，它需要等待。反之亦然
         */
        /**
         * 多生产者，多消费者的实验
         */
        while (!flag) {
//        if(!flag){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + "--消费者--" + this.name);
        /**
         * 如果是有东西的，则不用等待，直接消费。
         * 消费完了后，将flag =false，表明盘子里的东西是被消费完了的
         * 然后通知生产者，可以生产东西了。
         */
        flag = false;
//        notify();
        /**
         * 多生产，多消费的情况下直接唤醒全部
         */
        notifyAll();
    }
    /**
     * 经过处理后，最终的执行结果变成了这样
     * Thread-1--消费者--面包11597
     Thread-0--生产者--面包11598
     Thread-1--消费者--面包11598
     Thread-0--生产者--面包11599
     Thread-1--消费者--面包11599
     Thread-0--生产者--面包11600
     Thread-1--消费者--面包11600
     Thread-0--生产者--面包11601
     Thread-1--消费者--面包11601
     Thread-0--生产者--面包11602
     Thread-1--消费者--面包11602
     即满足了：即：生产者在盘子里没有面包后才生产，消费者在盘子里有东西时才消费
     */
}