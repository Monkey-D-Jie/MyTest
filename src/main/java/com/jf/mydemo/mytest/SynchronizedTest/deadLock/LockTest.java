package com.jf.mydemo.mytest.SynchronizedTest.deadLock;

/**
 * Created by wjie on 2018/5/20.
 */
public class LockTest implements Runnable {

    private boolean init;
    public LockTest(boolean init){
        this.init = init;
    }

    public void run() {
        if (init){
            while(true){
                synchronized (MyLock.LockA){
                    System.out.println(Thread.currentThread().getName()+"---if----lockA");
                    synchronized (MyLock.LockB){
                        System.out.println(Thread.currentThread().getName()+"---if----lockB");
                    }
                }
            }
        }else{
            while(true){
                synchronized (MyLock.LockB){
                    System.out.println(Thread.currentThread().getName()+"---else----lockB");
                    synchronized (MyLock.LockA){
                        System.out.println(Thread.currentThread().getName()+"---else----lockA");
                    }
                }
            }
        }
    }
}
