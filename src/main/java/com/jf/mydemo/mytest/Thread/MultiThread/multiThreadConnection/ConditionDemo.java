package com.jf.mydemo.mytest.Thread.MultiThread.multiThreadConnection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangjie
 * @Date 2018-06-03 23:02
 * @Description condition的一个例子
 */
public class ConditionDemo {
    //创建一个共用的锁对象
    final Lock lock = new ReentrantLock();
    //创建生产者的监视器对象，以调用await(),signal(),signalAll()等方法
    final Condition notFull = lock.newCondition();
    //创建消费者的监视器对象
    final Condition notEmpty = lock.newCondition();
    //商品集合
    final Object[] items = new Object[100];
    //生产商品的下标
    int putptr,
            //消费商品的下标
            takeptr,
            //计数器
            count;
    //生产商品的方法
    public void put(Object x) throws InterruptedException {
        //获得可让线程执行的锁
        lock.lock();
        try {
            //当生产的商品数已达最大时，说明生产的东西已经放满容器了
            while (count == items.length)
                //生产者不能再生产了，所以等待
                notFull.await();
            //如果没满，则执行正常的生产过程，将商品放入商品数组putptr对应的位置中
            items[putptr] = x;
            //当放置的位置已经达到商品数组最大值后，则把角标归0.
            //因为生产者在生产商品的时候，消费者也在消费商品。生产者生产的商品数到了
            //最大角标时，早期生产的那些商品已经被消费了。所以，要继续生产商品的，后面的
            //数组位列还不空，要放商品，自然就得从头开始来放了嘛。
            //消费者那里归0，也是这样的情况。消费商品的角标不断增大。直到最大值。此时要继续消费商品的
            //自然也得从生产列的最开始部分去拿商品。
            if (++putptr == items.length) putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length) takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
