package com.jf.mydemo.mytest.YeildThreadTest;



/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-07 18:05
 * @Description: yeild()的测试类
 * To change this template use File | Settings | File and Templates.
 */

public class YieldExample {
    public static void main(String[] args)
    {
        Thread producer = new Producer();
        Thread consumer = new Consumer();

        producer.setPriority(Thread.MIN_PRIORITY); //Min Priority
        consumer.setPriority(Thread.MAX_PRIORITY); //Max Priority

        producer.start();
        consumer.start();
    }
    /**
     * 测试结果
     *1.在未调用 yield()时，pruducer和consumer执行结果的情况不唯一。
     * 这个时候并不是单纯的依靠优先级来决定到底谁先执行。
     *
     * I am Producer : Consumed Item 0
     I am Consumer : Consumed Item 0
     I am Consumer : Consumed Item 1
     I am Consumer : Consumed Item 2
     I am Consumer : Consumed Item 3
     I am Consumer : Consumed Item 4
     ...
     所以，你能看见，低优先级的Producer也能被先执行；
     2.调用yield()后

     */

}

