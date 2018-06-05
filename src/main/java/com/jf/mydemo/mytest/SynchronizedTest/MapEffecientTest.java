package com.jf.mydemo.mytest.SynchronizedTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/8 0008
 * @time: 13:14
 *  To change this template use File | Settings | File and Templates.
 *  用数据比对 Hashtable/synchronizedMap(Map)/concurrentHashMap 三者之间的效率
 *结果（效率由 低->高）
 * Hashtable < synchronizedMap(Map) < concurrentHashMap
 */

public class MapEffecientTest {

    public final static int THREAD_POOL_SIZE = 10;
    public static Map<String,Integer> hashtablemapMap = null;
    public static Map<String,Integer> synchronizedmapMap = null;
    public static Map<String,Integer> concurrentmapMap = null;

    public static void main(String[] args) throws InterruptedException {
        /*hashtablemapMap = new Hashtable<>();
        efficienPerformTest(hashtablemapMap);

        synchronizedmapMap = Collections.synchronizedMap(new HashMap<>());
        efficienPerformTest(synchronizedmapMap);*/


        concurrentmapMap = new ConcurrentHashMap<>();
        efficienPerformTest(concurrentmapMap);

    }

    public static void efficienPerformTest(final Map<String,Integer> testMap) throws InterruptedException {
        if(testMap != null){
            System.out.println("Test started for "+testMap.getClass().getName());
            /*并发运行10个线程，每个线程*/
            long averageTime = 0;
            for (int i = 0; i < 10 ; i++) {
                long startTime = System.nanoTime();
                ExecutorService efficiencyExServer = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
                for (int j=0; j< THREAD_POOL_SIZE; j++){
                    efficiencyExServer.execute(new Runnable() {
                        @Override
                        public void run() {
                            for (int k = 0; k < 500000; k++) {
                                Integer randomnumber = (int)Math.ceil(Math.random()*550000);

                                /*这里存取顺序对整个程序有什么影响吗？*/
                                /**
                                 * @author: wjie
                                 * @date: 2018/3/8 0008 13:57
                                 * 调试后发现：并没有什么影响，只是说在做 存和取的操作而已；
                                 *但是，对它们各自的操作在时间上有影响，先存后取更慢一些
                                 * 以 concurrentHashMap为例
                                 * 先存后取：1873+2025
                                 * 先取（为null直接返回）后存：1965+1940
                                 * 总体上来说，时间上的影响还是很小的
                                 */
                                testMap.put(String.valueOf(randomnumber),randomnumber);
                                Integer mapValue = testMap.get(String.valueOf(randomnumber));
                            }
                        }
                    });
                }
                efficiencyExServer.shutdown();
                efficiencyExServer.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
                long endTime = System.nanoTime();
                long totalTime = (endTime - startTime)/1000000L;
                averageTime += totalTime;
                System.out.println("500K entried add/retrieved in "+totalTime+" ms");
            }
            System.out.println("For " + testMap.getClass() + " the average time is " + averageTime / 5 + " ms\n");
        }
    }

}
