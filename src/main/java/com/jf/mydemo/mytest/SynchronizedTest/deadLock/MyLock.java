package com.jf.mydemo.mytest.SynchronizedTest.deadLock;

/**
 * Created by wjie on 2018/5/20.
 */
public class MyLock {
    //锁A
    public static final Object LockA = new Object();
    //锁B
    public static final Object LockB = new Object();
}
