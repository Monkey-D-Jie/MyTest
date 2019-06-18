package com.jf.mydemo.mytest.Thread.FutureAndCallable;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author wangjie
 * @Date 2018-07-08 17:45
 * @Description Callable+Future测试类
 */
public class CallableAndFutureTest {


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task<Integer> task = new Task();
        Future<Integer> future = executorService.submit(task);

        Future<?> future2 = executorService.submit(() -> System.out.println("future2---执行中"));


//        FutureTask<Integer> futureTask = new FutureTask(task);
//        executorService.submit(futureTask);
        try{
            //do something
            Thread.sleep(1000);
            System.out.println("before get future result---"+ DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            System.out.println(future.get());
            System.out.println("after get future result---"+ DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
            System.out.println("future2---->>"+future2.get());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
