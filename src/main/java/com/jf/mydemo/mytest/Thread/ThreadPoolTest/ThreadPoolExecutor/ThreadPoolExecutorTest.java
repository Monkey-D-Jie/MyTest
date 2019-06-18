package com.jf.mydemo.mytest.Thread.ThreadPoolTest.ThreadPoolExecutor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-15 13:51
 * @Description: 用来测试线程池处理多线程任务的测试类
 * To change this template use File | Settings | File and Templates.
 */

public class ThreadPoolExecutorTest {
    @Test
    public void threadPoolTest() throws InterruptedException {
        Runnable myRunnable = () -> {
            try {
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName()+" is running");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("测试线程-%d").build();
        DifferentQueueThreadTest(myRunnable,threadFactory);
    }

    public void DifferentQueueThreadTest(Runnable myRunnable, ThreadFactory threadFactory) throws InterruptedException {
        //①：SynchronousQueue队列(该队列默认没有队列大小，来一个就处理一个，)
        // 核心线程数为6,最大线程数为10，线程的超时时间设定为5s
        //注意：并没有指定阻塞队列的大小
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>(),threadFactory);
        //①-1：更改线程池的大小上限为 8
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<>(),threadFactory);
        /**
         * ②：LinkedBlockingDeque 链表型双队列
         *  它跟 LinkedBlockingQueue的区别？
         *  我感觉它是在LinkedBlockingQueue上的一个优化，看了下源码上的声明，
         *  实现方式上作了优化处理
         */
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(),threadFactory);
        //③：ArrayBlockingQueue
        ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 8, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(4),threadFactory);
        long start = System.currentTimeMillis();
        System.out.print("最大线程数：" + executor.getMaximumPoolSize()+" ");
        System.out.println();
        //先开三个
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println("---先开三个---");
        System.out.print("核心线程数：" + executor.getCorePoolSize()+" ");
        System.out.print("线程池数：" + executor.getPoolSize()+" ");
        System.out.print("队列任务数：" + executor.getQueue().size()+" ");
        System.out.println();
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println();
        System.out.println("---再开三个---");
        System.out.print("核心线程数：" + executor.getCorePoolSize()+" ");
        System.out.print("线程池数：" + executor.getPoolSize()+" ");
        System.out.println("队列任务数：" + executor.getQueue().size()+" ");
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        executor.execute(myRunnable);
        System.out.println();
        System.out.println("---继续再开三个---");
        System.out.print("核心线程数：" + executor.getCorePoolSize()+" ");
        System.out.print("线程池数：" + executor.getPoolSize()+" ");
        System.out.println("队列任务数：" + executor.getQueue().size()+" ");
        Thread.sleep(8000);
        System.out.println("----8秒之后----");
        System.out.print("核心线程数：" + executor.getCorePoolSize()+" ");
        System.out.print("线程池数：" + executor.getPoolSize()+" ");
        System.out.print("队列任务数：" + executor.getQueue().size()+" ");
        System.out.println();
        executor.shutdown();
        long end = System.currentTimeMillis();
        System.out.println("合计耗时："+(end-start)/1000.0+"s");
        /**
         *
         * ① ：SynchronousQueue 在最大线程数之下
         * 测试结果：
         *---先开三个---
         核心线程数：6 线程池数：3 队列任务数：0

         ---再开三个---
         核心线程数：6 线程池数：6 队列任务数：0

         ---继续再开三个---
         核心线程数：6 线程池数：9 队列任务数：0
         测试线程-0 is running
         测试线程-3 is running
         测试线程-2 is running
         测试线程-1 is running
         测试线程-5 is running
         测试线程-6 is running
         测试线程-7 is running
         测试线程-4 is running
         测试线程-8 is running
         ----8秒之后----
         核心线程数：6 线程池数：6 队列任务数：0
         *
         * 结果分析：
         * 当线程池为同步队列，且未声明其队列的大小时，但凡有新的线程任务申请，
         * 则该线程池就会创建一个新线程去处理。不会在队列中去排队。
         * 这些线程在执行完任务后，如果是在未超时的时间内，会继续等待；超时时间一过，
         * 就都会自动的被清除掉。
         * 最终只保留指定核心线程数条的线程，线程池关闭后，它们才会被回收。
         * 如果在超时的时间内，还没有完成任务的，会发生什么呢？
         * 在这里定义的keepAliveTime，指的是完成线程任务后，还能继续留存的时间。
         * 跟线程任务执行完所需要的时间没有关系.硬是要说关系的话，也只是后面的线程
         * 任务因为前面任务的执行而顺序的往后面排，按序执行
         * ①-1：超过了最大线程数
         * 直接报错，
         * java.util.concurrent.RejectedExecutionException: Task com.jf.mydemo.mytest.Thread.ThreadPoolTest.ThreadPoolExecutor
         * 抛出RejectExecutionException异常
         *②：LinkedBlockingDeque
         * 运行结果
         * 最大线程数：8 ---先开三个---
         核心线程数：6 线程池数：3 队列任务数：0

         ---再开三个---
         核心线程数：6 线程池数：6 队列任务数：0

         ---继续再开三个---
         核心线程数：6 线程池数：6 队列任务数：3
         测试线程-1 is running
         测试线程-0 is running
         测试线程-2 is running
         测试线程-3 is running
         测试线程-4 is running
         测试线程-5 is running
         测试线程-2 is running
         测试线程-0 is running
         测试线程-1 is running
         ----8秒之后----
         核心线程数：6 线程池数：6 队列任务数：0
         结果分析：
         链表型队列
         这种队列的特点是，但凡线程池中的线程数达到了核心线程数值后，
         后面来的线程任务就都会在队列中排队。
         队列的排队长度默认是Integer.MAX_VALUE
         如果是有自行设定，则以自行设定的值为大，超过后，就会抛出异常
         测试：将LinkedBlockDueue队长设置为2
         结果：正常运行。
         这种情况下，Executor可承载的线程任务数量为 最大线程数+队列长度 = 10
         当<线程任务数量<核心线程数量设置值 >时,来一个任务，就创建一个线程去执行它。
         线程任务数量超过核心线程数量值后，超过数量的线程任务会优先的被放到阻塞队列中，
         等到有空闲线程后再去执行。
         当【核心线程数+队列任务数】的和超过最大线程数时，
         线程池会继续在最大线程数的范围内新开线程。
         达到最大线程数后，如果还有新的线程任务请求，则报错RejectException。
         正常情况下，完成线程任务后，只保持核心线程数量的线程在池中，其他
         仍然空闲的线程将在超时时间后自动的被清除掉。
         executor关闭后，所有的线程都被关闭掉。
         总结一句话：LinkedBlockDueue的线程任务承载量为：最大线程数+队列长度。
         它相比单纯的BlockQueue效率更高。
         核心点在于Dueue底层是用双向链表实现的，减少了队列节点的遍历开销。
         而LinkedBlockQueue是用 two lock queue 双锁队列实现，即插入和删除
         都用单独的锁，入队和出队都各自进行同步。
         1.7后，又引入了TransferQueue的队列类型，其特点是：使用双队列协同配合的方式，
         完成接入的线程任务，完成的顺序是不定。Dual Queue，是tm什么鬼？？？（暂作了解，不深究）

         ③：ArrayBlockingQueue
         这个的话，就是常规队列实现的，遵循先进先出的任务处理方式。
         它跟LinkedBlockingDueue的线程任务处理策略和拒绝策略相似。
         不同的地方在于，初始化时，就需要指定其队列大小。
         综上来看，平时使用ThreadPoolExecutor来建立线程池，
         比较好的选择是SynBlockingQueue，LinkedBlockingDueue，
         然后根据实际情况对它们的队列大小做一定限制或直接使用默认的设置。
         线程执行的优先级为 核心线程执行，加入阻塞队列，最大线程执行
         */

    }
}