package com.jf.mydemo.mytest.ObjectTest.Reflect;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-06 13:55
 * @Description: 用于演示反射方法的测试对象
 * To change this template use File | Settings | File and Templates.
 */

public class Person implements Serializable{
    private String name;
    private int age;

    public Person(){

    }

    public Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void publicHelloMethod(String name,int age){
        System.out.println("我是"+name+",今年"+age+"岁----public");
    }
    private void privateHelloMethod(String name,int age){
        System.out.println("我是"+name+",今年"+age+"岁----private");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
