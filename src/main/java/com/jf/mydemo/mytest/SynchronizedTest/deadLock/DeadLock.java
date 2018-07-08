package com.jf.mydemo.mytest.SynchronizedTest.deadLock;

/**
 * Created by wjie on 2018/5/20.
 * 死锁的示例程序
 * 死锁的要点：A需要B才能继续做后面的事情，B又需要A才能继续往后执行操作。
 * 大家都不放，所以就出现了死锁的情况
 */
public class DeadLock {
    public static void main(String[] args) {
        LockTest l1 = new LockTest(true);
        LockTest l2 = new LockTest(false);

        Thread t1 = new Thread(l1);
        Thread t2 = new Thread(l2);

        t1.start();
        t2.start();
    }
}
