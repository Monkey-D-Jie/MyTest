package com.jf.mydemo.mytest.Thread.ThreadLocalTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-06 15:07
 * @Description: ThreadLocal的代码demo测试
 * 来自：
 * To change this template use File | Settings | File and Templates.
 */

public class MyThreadLocalTest {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringThreaLocal = new ThreadLocal<String>();
    private void init(){
        //    Thread.currentThread().getId()
        this.longThreadLocal.set((long)2);
        //Thread.currentThread().getName()
        this.stringThreaLocal.set("test");
    }

    private Long getLong(){
        return this.longThreadLocal.get();
    }

    private String getString(){
        return stringThreaLocal.get();
    }

    public static void main(String[] args) {
        final MyThreadLocalTest test = new MyThreadLocalTest();
        //主线程中
        test.init();
        System.out.println("*****主线程中打印*****");
        System.out.println(test.getLong());
        System.out.println(test.getString());

        //在其他线程中作操作
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 只要是在线程中调用了set方法，则对应的init方法中设置的值就是当前线程thread的
                 * 不光是set方法，get方法也是如此。
                 * 这里调用get方法也是属于当前线程而定，和主线线程没有关系。
                 * 注释后的打印结果----
                 *
                 *****主线程中打印*****
                 1
                 main
                 *****主线程中打印-第二次*****
                 1
                 main
                 *****thread1线程中打印*****
                 null
                 null

                 */
                test.init();
                /**
                 * 未注释时的打印结果
                 ******主线程中打印*****
                 1
                 main
                 *****主线程中打印-第二次*****
                 1
                 main
                 *****thread1线程中打印*****
                 11
                 Thread-0
                 */
                System.out.println("*****thread1线程中打印*****");
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        });
        thread.start();
        System.out.println("*****主线程中打印-第二次*****");
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
