package com.jf.mydemo.mytest.SingletonTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-06 16:34
 * @Description: 用静态内部类实现单例
 * To change this template use File | Settings | File and Templates.
 */

public class NestSingletonTest {

    private static class NestClass{
        private static NestSingletonTest nestSingletonTest;
        static{
            System.out.println("NestClass nestSingletonTest = new NestSingletonTest()");
            nestSingletonTest = new NestSingletonTest();
        }
    }
    private NestSingletonTest(){

    }
    //由jvm来保证线程安全性
    public static NestSingletonTest getInstance(){
        return NestClass.nestSingletonTest;
    }

    public static void main(String[] args) {
        NestSingletonTest instance = NestSingletonTest.getInstance();
        System.out.println("========================================");
        NestSingletonTest instance01 = NestSingletonTest.getInstance();
        System.out.println("========================================");
        NestSingletonTest instance02 = NestSingletonTest.getInstance();
        System.out.println("instance == instance01 == instance02:"+(instance == instance01)+","+(instance01 == instance02));
    }

}
