package com.jf.mydemo.mytest.CollectionTest;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/2/28 0028
 * @time: 16:48
 * To change this template use File | Settings | File and Templates.
 */

public class HashMapTest {
    @Test
    public void hashMapTest(){
        Map<String,Object> map1 = new HashMap<String,Object>(8);
        //map1.put("test",123);
        //map1.put("test2",null);
        map1.put(null,"666");
        map1.put(null,"777");
        map1.put("test3",456);
        System.out.println(map1.get("test"));
        map1.remove("test3");
    }
}
