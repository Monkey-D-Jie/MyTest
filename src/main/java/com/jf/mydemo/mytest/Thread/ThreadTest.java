package com.jf.mydemo.mytest.Thread;

import org.junit.Test;



/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/1/16 0016
 * @time: 14:28
 * To change this template use File | Settings | File and Templates.
 */

public class ThreadTest {
    /**
     * @author: wjie
     * @date: 2018/1/16 0016 14:30
     * thread执行顺序的测试类
     */
    /**
     * @author: wjie
     * @date: 2018/1/16 0016 14:52
     * 下述关键内容摘自：https://www.cnblogs.com/whyalwaysme/p/4495959.html
     * <p>
     * 直接调用了线程的run方法，就好比是调用类A中的普通方法method_A一样,
     * 已经失去了线程的特性。且它的执行路径和顺序都只有一条：即run方法执行完成后，才能继续执行
     * 后续的的代码；
     * 所以，不管你执行多少次，打印出的结果都是：
     * static show
     * thread show
     * 但是，如果是用的start，结果就不一样了噢；
     * start方法时，这里会新开除了test(主线程)之外的一个新线程，
     * 执行到的是：start新线程获取运行资源后，再执行到它内部的run方法。
     * 这个过程中，自然就存在同main主线程竞争的情况。
     * 准确的描述：
     * 用start来运行线程，是真正的执行到了多线程。在它后方的代码，可以不用等run方法执行完，就能执行到。
     * 因为调用了start()方法后，是让对应的线程处于一种就绪(可运行)的状态，
     * 得它拿到时间片后，才能去执行到run方法(又被称为线程体)【这也就能解释为什么 有时候只能打印出一句‘thread show’了，
     * 成功获得时间片，拿到执行权时，自然就能两句都给打印出来咯】
     * 所以出现的结果可能为：
     * thread show
     * static show，
     * 单独只有一句 thread show。
     * 但是，为什么会出现上述的两种结果呢？？
     * 如上解析。
     * 总结：start()才是启动线程的方法，run()是跟普通类中的类方法，跟线程启动的关系不大。
     */
    @Test
    public void threadTest() throws InterruptedException {
        System.out.println("线程测试开始------------------");
        Thread thread = new Thread() {
            @Override
            public void run() {
                show();
            }
        };
        thread.start();
        Thread.sleep(2000);
        System.out.println("线程执行它该做的事，我先做我的事情");

    }

    static void show() {
        try {
            Thread.sleep(2000);
            System.out.println("I am a method in thread");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author: wjie
     * @date: 2018/2/7 0007 16:22
     * 引自：http://www.importnew.com/18126.html
     * 结果预判：均不会大于100000
     * 测试结果：
     */
    @Test
    public void volatileTest() {
        System.out.println("内容输出测试");
    }

    /**
     * ThreadTest ----the diffrence of sleep and wait
     */
    @Test
    public void sleepAndWaiTest() throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread t1 = new Thread(threadDemo);
        t1.start();
        threadDemo.method2();
    }

    /**
     * join test
     */
    @Test
    public void joinTest(){
        Thread thread = new Thread(new JoinDemo());
        thread.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("主线程第" + i + "次执行！");
            if (i >= 2) {
                try {
                    // t1线程合并到主线程中，主线程停止执行过程，转而执行t1线程，直到t1执行完毕后继续。
                    thread.join();
                    /**
                     * join
                     * 顾名思义，就相当于 插队！
                     * 测试结果：
                     * 主线程第0次执行！
                     主线程第1次执行！
                     主线程第2次执行！
                     join线程第0次执行！------------
                     join线程第1次执行！------------
                     join线程第2次执行！------------
                     join线程第3次执行！------------
                     join线程第4次执行！------------
                     join线程第5次执行！------------
                     join线程第6次执行！------------
                     join线程第7次执行！------------
                     join线程第8次执行！------------
                     join线程第9次执行！------------
                     主线程第3次执行！
                     .....
                     一旦被join进来的线程拿到了cpu的执行权，其他的它都不会管。
                     会优先的把自己线成任务执行完。
                     然后才会继续执行之前线程的线程任务。。。
                     */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ThreadDemo threadDemo = new ThreadDemo();
            Thread t1 = new Thread(threadDemo,"我是线程");
            ThreadDemo2 threadDemo2 = new ThreadDemo2();
            Thread t2 = new Thread(threadDemo2);
            t2.start();
            t1.start();
            threadDemo.method2();
            System.out.println("主线程继续执行....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
