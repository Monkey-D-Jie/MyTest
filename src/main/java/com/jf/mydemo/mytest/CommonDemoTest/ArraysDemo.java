package com.jf.mydemo.mytest.CommonDemoTest;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-14 13:48
 * @Description: Arrays工具类的常用方法测试demo
 * To change this template use File | Settings | File and Templates.
 */

public class ArraysDemo {

    public static void main(String[] args) {
        int[] array = {1,8,4,2,8,3,5,2,3,3,3};
        System.out.println("Original array:"+ ArrayUtils.toString(array));
        System.out.println("Arrays.sort(array)-----result");
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println("Arrays.copyOf(array,array.length)-----result");
        int[] array2 = Arrays.copyOf(array,array.length);
        array2[0] = 6;
        System.out.println(Arrays.toString(array));
        System.out.println("Original is equals with The copy?");
//        System.out.println(array.equals(array2));

    }
}
