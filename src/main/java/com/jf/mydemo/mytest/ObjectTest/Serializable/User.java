package com.jf.mydemo.mytest.ObjectTest.Serializable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-06 09:37
 * @Description: 用于测试序列化的实体类
 * To change this template use File | Settings | File and Templates.
 */

/**
 * ①：一个对象，要想让它能够序列化，就要实现Serializable接口，
 * 相当于其能够序列化的标识
 */
public class User implements Serializable{

    private String name;
    private int age;
    private Date birthday;
    /**
     * ②：
     * transient：短暂的，瞬时的
     * 在序列化过程中起到的作用即是：盛宁被该关键字修饰的成员变量不能被序列化。
     * 指明 不用在序列化时，将其序列化到文件中(本例中是这样，其他的依此类推)
     * 反序列化后得到的信息为改成员变量的原始数据：
     * string类 or 对象类 --》反序列化后得到的是null
     * 基本类型---》反序列化得到的是0或其他基本类型的初始值
     */
    private transient String gender;

    /**
     * 测试用静态成员变量
     * 说明：静态成员变量在对象创建之初就附着在对象上，
     * 即是对象的原生部件，是不支持被‘揉碎’或者‘变形’的。
     * 所以，静态成员变量是不被纳入序列化支持的范畴嘞。
     *即：序列化不保存静态变量
     */
    public static String flag = "静态成员测试";

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public static String getFlag() {
        return flag;
    }

    public static void setFlag(String flag) {
        User.flag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", gender='" + gender + '\'' +
                '}';
    }
}
