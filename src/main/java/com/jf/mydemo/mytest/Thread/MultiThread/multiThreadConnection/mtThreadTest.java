package com.jf.mydemo.mytest.Thread.MultiThread.multiThreadConnection;

/**
 * @Author wangjie
 * @Date 2018-06-03 12:45
 * @Description 多线程间的通信测试类
 */
public class mtThreadTest {

    public static void main(String[] args) {
//        Resource resource = new Resource();
        //jdk 1.5后的lock，它能起到和synchronized相同的效果，而且功能也更加的强大
        Resource_Lock resource = new Resource_Lock();
        Producer producer = new Producer(resource);
        Consumer consumer = new Consumer(resource);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(consumer);
        Thread t3 = new Thread(producer);
        Thread t4 = new Thread(consumer);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        /**
         *  多生产者，多消费者间问题探索...
         *  运行上面的程序后，出现了下面这种情况：
         ①：一个面包被消费了多次！
         Thread-0--生产者------面包28644
         Thread-1--消费者--面包28644
         Thread-3--消费者--面包28644
         ②:生产了面包却没有被消费
         Thread-2--生产者------面包22429
         Thread-0--生产者------面包22430
         Thread-1--消费者--面包22430
         ？为什么会出现这样的情况呢》？
         因为等待的线程在获取了执行权后，不会再对之前是否要再执行生产的条件作判断，从而导致了多生成了面包，而没有没消费掉
         ---》那就让，让线程在任何时候醒来后都去判断条件嘛，
         所以改标识判断的方法为 while。。。
         改了后，运行程序，生成了下方的结果
         Thread-0--生产者------面包1
         Thread-1--消费者--面包1
         Thread-0--生产者------面包2
         Thread-3--消费者--面包2
         Thread-0--生产者------面包3
         Thread-3--消费者--面包3
         Thread-2--生产者------面包4
         直接就停住了，产生了死锁！
         为什么会产生死锁？
         因为在while判断中，很可能会出现：notify()唤醒的是同一方。在本例中，notify()就是唤醒了生产方，生产方再判断的时候，
         发现盘子中仍然有东西，所以就继续等待。此时，消费方也都是等待着的，，
         这就造成一种状态：所有的线程都等待了，当然就程序就直接被挂起，没有继续执行了（wait()方法只是让线程处于一种停滞状态，并不会占用cpu资源，
         所以测试时，就辛酸是程序挂起没有运行了，电脑状态球也没有出现报红的情况）。
         由于在while这种方法下，并没有唤醒指定线程的方法，
         所以，这里可以考虑的一个解决方案就是：唤醒全部，生产的wait()阻塞也没关系。只有有被唤醒的消费者就可以了

         测试结果(其中一部分)：
         Thread-0--生产者------面包12220
         Thread-3--消费者--面包12220
         Thread-2--生产者------面包12221
         Thread-3--消费者--面包12221
         Thread-0--生产者------面包12222
         Thread-3--消费者--面包12222
         这就和谐了嘛。。

         思考：如上所述，这种方法是唤醒了全部。。。
         当然就涉及到了一些不必要的开销：比如，生产者要多判断一次是否要生产；消费者只要一个就行，这里则是唤醒了两个。。。
         就造成了一定的效率损失。
         那有什么解决办法没呢？？？？

         */


    }
}
