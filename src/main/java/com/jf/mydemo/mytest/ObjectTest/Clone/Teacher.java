package com.jf.mydemo.mytest.ObjectTest.Clone;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-05 13:01
 * @Description: 测试用的老师实体类
 * To change this template use File | Settings | File and Templates.
 */

/**
 * 增减：implements Cloneable
 * 说明:在克隆的时候，该对像也要一并被clone
 */
public class Teacher implements Cloneable{
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * 单纯的在这里加上复写的clone方法并没有作用。需要在当前场景的主类中去改才行，即student的clone方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
