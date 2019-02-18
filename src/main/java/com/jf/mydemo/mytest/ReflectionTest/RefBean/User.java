package com.jf.mydemo.mytest.ReflectionTest.RefBean;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-18 13:05
 * @Description: 测试用用户对象
 * To change this template use File | Settings | File and Templates.
 */

public class User {
    private String name;
    private Integer age;
    public String phone;
    private String msg = "Hello Reflection World";

    public User(){

    }

    public User(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public void show(String name, Integer age){
        System.out.println("My name is "+name+",I am "+age+" years old");
    }

    private void changeMsg(String newMsg){
        this.msg = newMsg;
        System.out.println("msg changed.Now the msg is "+newMsg);
    }
}
