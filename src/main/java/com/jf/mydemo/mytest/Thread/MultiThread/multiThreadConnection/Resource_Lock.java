package com.jf.mydemo.mytest.Thread.MultiThread.multiThreadConnection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangjie
 * @Date 2018-06-03 12:31
 * @Description 待操作的资源实体类
 */
public class Resource_Lock {

    private String name;
    public int count = 1;
    //the flag of produce
    private boolean flag = false;

    private Lock lock = new ReentrantLock();

    //    Condition condition = lock.newCondition();
    /**
     * 新思路：创建两个不同的监视器对象。
     * 虽然它们是不同对象，但关联的锁是同一个，所以能满足：共性锁的要求。
     * 即：通知和等待的最终作用对象都是同一个。
     * 1.5的新锁提供了这样的功能。相较notifyAll()，它们的操作对象更明确，
     * 也正确的减少了要操作的对象
     */
    //生产者的监视器对象
    Condition produce = lock.newCondition();
    //消费者的监视器对象
    Condition consume = lock.newCondition();

    public void set(String name) {
        try {
            lock.lock();
            while (flag) {
                produce.await();
            }
            this.name = name + count;
            count++;
            System.out.println(Thread.currentThread().getName() + "--生产者------" + this.name);
            flag = true;
            consume.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void out() {
        try {
            lock.lock();
            while (!flag) {
                consume.await();
            }
            System.out.println(Thread.currentThread().getName() + "--消费者--" + this.name);
            flag = false;
            produce.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}