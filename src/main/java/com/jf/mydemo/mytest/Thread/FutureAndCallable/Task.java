package com.jf.mydemo.mytest.Thread.FutureAndCallable;

/**
 * @Author wangjie
 * @Date 2018-07-08 19:41
 * @Description ����������
 */

import java.util.concurrent.Callable;

/**
 * @Author wangjie
 * @Date 2018-07-08 18:11
 * @Description ʵ����callable�ӿڵ���
 */
public class  Task<T> implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("taskִ����-callable ������");
        Thread.sleep(3000);
        int sum = 0;
        for(int i=0;i<100;i++)
            sum += i;
        return sum;
    }
}

