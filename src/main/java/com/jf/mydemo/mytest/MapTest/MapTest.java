package com.jf.mydemo.mytest.MapTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/1/15 0015
 * @time: 15:12
 * To change this template use File | Settings | File and Templates.
 */

public class MapTest {

    /**
     * ConcurrentHashMap的一些实验
     */
    @Test
    public void concurrentHashMapTest(){
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        try {
            t.join();
            System.out.println("map.size="+map.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hashTest(){
        System.out.println(Integer.parseInt("0001111", 2) & 15);
        System.out.println(Integer.parseInt("0011111", 2) & 15);
        System.out.println(Integer.parseInt("0111111", 2) & 15);
        System.out.println(Integer.parseInt("1111111", 2) & 15);

        System.out.println("0001111".hashCode() & 15);
        System.out.println("0011111".hashCode() & 15);
        System.out.println("0111111".hashCode() & 15);
        System.out.println("1111111".hashCode() & 15);
    }
}
