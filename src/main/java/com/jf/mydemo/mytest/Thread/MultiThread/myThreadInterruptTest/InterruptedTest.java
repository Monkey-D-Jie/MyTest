package com.jf.mydemo.mytest.Thread.MultiThread.myThreadInterruptTest;

/**
 * @Author wangjie
 * @Date 2018-06-07 00:14
 * @Description 线程中断的测试类
 */
public class InterruptedTest {
    public static void main(String[] args){
        System.out.println("main thread-----start");
        InterruptedDemo interruptedDemo = new InterruptedDemo();
        Thread t1 = new Thread(interruptedDemo);
        Thread t2 = new Thread(interruptedDemo);
        t1.start();
        t2.start();

        int count = 0;
        while (true){
            if(count == 50){//在循环中控制线程结束的条件
                //Thread.sleep(100);
                //interruptedDemo.changeFlag();//触发线程结束
                /**
                 * 使用interrupted()打破线程的冻结状态。
                 * 如果没有这句的，则会导致该线程在此被停滞
                 */
                    t1.interrupt();
                    t2.interrupt();
                break;
            }
            System.out.println("main thread----->>>"+count);
            count++;
        }
        System.out.println("main thread-----over");
    }
}

