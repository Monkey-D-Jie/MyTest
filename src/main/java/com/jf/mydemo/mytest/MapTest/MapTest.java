package com.jf.mydemo.mytest.MapTest;

import com.jf.mydemo.mytest.FileTest.ServiceException;
import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Test
    public void treeAndLinkedMap(){
        Integer a = 12;
        Integer b = 13;
        Integer c = 14;
        Map<String,Object> m1 = new TreeMap<>();
        Map<String,Object> m2 = new LinkedHashMap<>();
        m1.put("a",a);
        m1.put("c",c);
        m1.put("b",b);
        m2.put("a",a);
        m2.put("c",c);
        m2.put("b",b);
        checkTokenOuttime(m1);
        System.out.println("m1:"+m1.toString());
        System.out.println("m2:"+m2.toString());
        for (Map.Entry<String, Object> entry : m1.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        throwOrgCodeExc("JFMS_123-3210哈哈",32,"通过了");
    }
    public static void throwOrgCodeExc(String str, int length, String exceptionInfo) {
        Pattern p = Pattern.compile("^[a-zA-Z0-9_-]{1,32}$");
        Matcher m = p.matcher(str);
        if (!m.find() || str.length() > length) {
            throw new ServiceException(exceptionInfo);
        }
    }
    private void checkTokenOuttime(Map<String,Object> map){
        Date curDate = new Date();
        List<String> removeKeys = new ArrayList<>(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            //遍历map中的元素，查看当前时间和存入改key的时间间隔
            if("a".equals(entry.getKey())){
                //如果已过期，则移除掉该key值
                removeKeys.add(entry.getKey());
            }
        }
        if(removeKeys.size() > 0){
            for (String key:removeKeys
                    ) {
                map.remove(key);
            }
        }
    }
}
