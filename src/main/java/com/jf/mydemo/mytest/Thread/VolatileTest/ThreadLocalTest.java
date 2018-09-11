package com.jf.mydemo.mytest.Thread.VolatileTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-03 15:53
 * @Description: ThreadLocal也是为了保证数据的一致性，所以把它列在这里
 * To change this template use File | Settings | File and Templates.
 * 感觉这个对象 就是 用在多线程场景下的 定制map，且只能存一个对象进去，
 */

public class ThreadLocalTest {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println(threadLocal.hashCode());
        threadLocal.set(111);
        threadLocal = null;
        FutureTask<Integer> result1 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("result1----threadLocl.get-->"+threadLocal.get());
                Double d = Math.random()*100;
                threadLocal.set(d.intValue());
                int n = threadLocal.get();
                System.out.println("result1--改变值后--threadLocl.get-->"+threadLocal.get());
                return --n;
            }
        });

        FutureTask<Integer> result2 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("result2----threadLocl.get-->"+threadLocal.get());
                Double d = Math.random()*100;
                threadLocal.set(d.intValue());
                int n = threadLocal.get();
                System.out.println("result1--改变值后--threadLocl.get-->"+threadLocal.get());
                return --n;
            }
        });
        result1.run();
        result2.run();
        result2.get();
    }
}
