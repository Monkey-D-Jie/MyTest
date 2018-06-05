package com.jf.mydemo.mytest.CollectionTest;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/1/10 0010
 * @time: 10:26
 * To change this template use File | Settings | File and Templates.
 */

public class ListAndMapTest {

    @Test
    public void listAndMap() {
         /** 比较顺序查找和散列查找的性能*/
        //创建线性表集合
        List<String> list = new ArrayList<String>(6000);
        //创建散列表集合
        Map<String, Integer> map = new HashMap<String, Integer>(6000);
        //向集合中同时添加数据
        long listT1 = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            String key = "Tom" + i;
            list.add(key);
        }
        long listT2 = System.nanoTime();
        System.out.println("list中添加数据用时："+(listT2-listT1));
        /*向map中添加数据*/
        long mapT1 = System.nanoTime();
        for (int i = 0; i < 5000; i++) {
            String key = "Tom" + i;
            //将key,value成对的加入到map
            map.put(key, i);//Tom0, 0
        }
        long mapT2 = System.nanoTime();
        System.out.println("map中添加数据用时："+(mapT2-mapT1));
        //被查找的 key
        String key = "Tom4962";
        //线性表中顺序查找:
        long t1 = System.nanoTime();
        int i = list.indexOf(key);
        long t2 = System.nanoTime();
        System.out.println("在list中查找到key"+i + ",用时" + (t2 - t1)); //499999,11245379

        //散列表中的散列查找:
        t1 = System.nanoTime();
        int n = map.get(key);
        t2 = System.nanoTime();
        System.out.println("在map中查询到key"+n + ",用时" + (t2 - t1)); //499999,15317
        System.out.println("list平均用用时:"+(6570976+2892423+277695+5227721+2346525)/5);
        System.out.println("map平均用用时:"+(1861568+25221615+3751040+18898891+2017768)/5);
    }
    /**
     * @author: wjie
     * @date: 2018/2/27 0027 10:27
     * 调试集合中ArrayList内的方法源码用
     */
    @Test
    public void collectionTest(){
        List testList = new ArrayList(6);
        Map testMap = new HashMap(6);
        testMap.put("123",123);
        /**
         * add 方法添加数据源码过程
         * 1.ensureCapacityInternal，用该方法查看容量是否充裕，
         * 2.把要添加的 object 赋值给源码中的数组 transient（瞬时） Object[] elementData;
         * transient 关键字，被它修饰的属性不会被序列化。
         * （暂时先不管这个，有任务来了）
         *transient是Java语言的关键字，用来表示一个域不是该对象串行化的一部分。当一个对象被串行化的时候，
         * transient型变量的值不包括在串行化的表示中，然而非transient型的变量是被包括进去的。
         【
         transient：
         transient关键字详解 - CSDN博客  http://blog.csdn.net/u013207877/article/details/52572975
            transient的用途在于：阻止实例中那些用此关键字声明的变量持久化；
            当对象被反序列化时（从源文件读取字节序列进行重构），这样的实例变量值不会被持久化和恢复。
            比如你输入密码，只是希望它在登录时有用，你退出后，别人不能通过反序列化的方式把它的值给找到。
            序列化：数据按照统一的规则 rule-one 变成数据流；
            反序列化：获得数据时，也通过 rule-one 来重现数据；
            使用transient值的一些注意事项：
            1，一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
         　　2，transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
         　　3，被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
         反序列化后类中static型变量值为当前JVM中对应static变量的值，这个值是JVM中的不是反序列化得出的
         】
         * 啥子意思喃？
         *源码中会用到它的原因--》为了支持 用户自定义的序列化方式。
         *利用自定义的writeObject方法和readObject方法，用户可以自己控制序列化和反序列化的过程。
         *如果没有，则会调用默认的defaultWriteObject方法和默认的defaultReadObject
         * 默认的writeObject中只序列化了elementData中有数据的部分（只把有用的数据加到list中了），
         * 其实这个还是很容易理解的，ArrayList里面的elementData未必是满的，
         * 那么是否有必要序列化整个elementData呢？显然没有这个必要，因此ArrayList中重写了writeObject方法。
         *
         */
        testList.add(testMap);
        testList.add(123);
        testList.add(null);
        System.out.println("测试list中的数据如下:");
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }
        testList.remove(testMap);
        testList.remove(null);
        testList.remove(0);
        System.out.println("测试list中的的数据删除后如下:");
        for (int i = 0; i < testList.size(); i++) {
            System.out.println(testList.get(i));
        }
    }
}
