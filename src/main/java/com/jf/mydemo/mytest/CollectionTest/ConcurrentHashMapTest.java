package com.jf.mydemo.mytest.CollectionTest;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/7 0007
 * @time: 15:09
 * To change this template use File | Settings | File and Templates.
 */

public class ConcurrentHashMapTest {

    @Test
    public void test1(){
        ConcurrentHashMap<String,Object> map = new ConcurrentHashMap<>(8);
        map.put("test1",666);
        map.put("test2",777);
        map.put("test3",null);
        map.put(null,null);
        map.put(null,888);
        map.get("test1");
        map.remove("test1");
    }

    @Test
    public void test2(){
        int c = 8;
        int n = c - 1;
        n |= n >>> 1;
        //n = n | n >>> 1 ;
        System.out.println(" n |= n >>> 1 :"+ (n = n | n >>> 1 ));
        n |= n >>> 2;
        System.out.println(" n |= n >>> 2 :"+ (n |= n >>> 2));
        n |= n >>> 4;
        System.out.println(" n |= n >>> 4 :"+ (n |= n >>> 4));
        n |= n >>> 8;
        System.out.println(" n |= n >>> 8 :"+ (n |= n >>> 8));
        n |= n >>> 16;
        System.out.println(" n |= n >>> 16 :"+ ( n |= n >>> 16));

    }
}
