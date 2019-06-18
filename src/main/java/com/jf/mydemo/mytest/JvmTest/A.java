package com.jf.mydemo.mytest.JvmTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-22 09:56
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class A {
    /**
     * 请问：
     * ①：下面的这些类属性都在JVM的什么地方呢？
     * ②：它们分别是栈了多上的字节数？
     *字节数大小回顾
     * 空对象(即引用) 占8个字节
     * 基本类型
     * byte/boolean 1
     * short/char 2
     * int/float 4
     * double/long 8
     * 【封装类，属于对象引用，故占8字节】
     * 一个对象(类)所占字节数=空对象+对象中的成员变量合计所占字节数(靠8对齐)
     * 摘自；
     * [转]Java变量占用内存大小 - BuilderQiu - 博客园
     http://www.cnblogs.com/warden/archive/2013/03/27/2984926.html
     * class A{
     int a;
     char b;
     }
     占 8(基本)+8(int 4+char 2=6,对齐到8）= 16个字节
     再比如：

     class B{
     　　Integer a;
     　　long b;
     　　byte c;
     }
     占 8(基本)+8(long8+byte1=9，对齐到8）+8(对象引用4,对齐到8)=32个字节
     */
    /**
     * 成员变量
     */
    //栈区，a引用 占4个字节
    private int a;
    //b和10都在栈区，b 4字节，10 4字节
    private int b = 10;
    //classA 栈区
    private A classA;
    //classA1 栈区,new A() 堆区
    private A classA1 = new A();

    /**
     * 静态方法
     */
    public static void show2(){
        System.out.println("Hello static World");
    }

    /**
     * 方法
     * @param b 形参
     */
    public void show(int b){
        //局部变量
        int a = 3;
        a = b;
        System.out.println("Hello World："+b);
    }



    /**
     * 内部类
     */
    class B{

    }

    /**
     * 默认构造函数
     */
    public A(){

    }
    /**
     * 自定义构造函数
     */
    public A(int a){
        String b = "test";
        this.a = a;
    }


}
