package com.jf.mydemo.mytest.Java8Action.List2Map;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-06-18 16:53
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class ListWithMapTest {

    /**
     * 存放apple对象集合
     */
    List<Apple> appleList = new ArrayList<>();
    @Before
    public void initData(){
        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);
        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }
    @Test
    public void list2Map(){
        /**
         * 注意事项：
         * 在将list转换成Map的时候，
         * 如果有重复的key时，会报错 Duplicate key
         * 在这里两个apple的id都为1，所以，如果不做处理，会报错.
         * collect(Collectors.toMap(Apple::getId,a ->a));
         * 这里如果这样写，就会出错；
         * 这里用 （k1,k2）->k1来设置，如果有重复的key，则保留key1，舍弃key2
         *
         */
        Map<Integer,Apple> appleMap = appleList.stream()
                .collect(Collectors.toMap(Apple::getId,a ->a,(k1,k2)->k1));
        System.out.println(appleMap);
    }
    @Test
    public void groupTest(){
        /**
         * 以id分组，相同id的会被划分到同一个数组里
         */
        Map<Integer,List<Apple>> groupList = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println(groupList);
    }
    @Test
    public void filterTest(){
        List<Apple> filterList = appleList.stream().filter(apple -> apple.getName().equals("香蕉")).collect(Collectors.toList());
        System.out.println(filterList);
    }
    @Test
    public void sumTest(){
        //计算 总金额
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
        //totalMoney:17.48
        System.err.println("totalMoney:"+totalMoney);
        //计算 数量
        int sum = appleList.stream().mapToInt(Apple::getNum).sum();
        //sum:100
        System.err.println("sum:"+sum);
    }
}
