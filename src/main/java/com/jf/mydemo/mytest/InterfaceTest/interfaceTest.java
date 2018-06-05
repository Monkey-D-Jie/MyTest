package com.jf.mydemo.mytest.InterfaceTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/2 0002
 * @time: 13:47
 * To change this template use File | Settings | File and Templates.
 */

public interface interfaceTest {
    /*默认方法 修饰符为 public abstrac*/
    int getCount();
    /*default 方法*/
    default String getInterface(){
        return "I am default method";
    }
    /*private 方法---->private方法 老样子，默认下仍然不能有方法体*/
//    int getCount2(){
//
//    }
    /*静态方法可以有方法体*/
    static String getStaticInfo(){
        return "static method";
    }
    void main();
    static void main(String[] args){
        System.out.println(interfaceTest.getStaticInfo());
    }

}
