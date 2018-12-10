package com.jf.mydemo.mytest.Thread.FutureAndCallable;

/**
 * @Author wangjie
 * @Date 2018-07-08 19:41
 * @Description 这里是描述
 */

import java.util.concurrent.Callable;

/**
 * @Author wangjie
 * @Date 2018-07-08 18:11
 * @Description 实现了callable接口的类
 */
public class  Task<T> implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("task执行中-callable 计算中");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}

