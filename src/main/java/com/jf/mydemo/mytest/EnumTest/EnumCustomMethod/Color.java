package com.jf.mydemo.mytest.EnumTest.EnumCustomMethod;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-14 14:17
 * @Description: 自定义方法枚举类
 * To change this template use File | Settings | File and Templates.
 */

public enum Color {
    //第一步：定义枚举变量-只能放在枚举类的开头
    RED("红色",1),GREEN("绿色",2),
    BLACK("黑色",3),BLUE("蓝色",4),
    REDSTR("红色","1"),GREENSTR("绿色","2"),
    BLACKSTR("黑色","3"),BLUESTR("蓝色","4");
    //2.自定义属性名
    /**
     * 名称
     */
    private String name;
    /**
     * 下标值
     */
    private Integer index;

    private String index2;
    //3.创建相应的构造函数(枚举类型的构造函数默认是私有的哈)
    Color(String name,Integer index){
        this.name = name;
        this.index = index;
    }
    Color(String name,String index2){
        this.name = name;
        this.index2 = index2;
    }
    //4.配套 get，set方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getIndex2() {
        return index2;
    }

    public void setIndex2(String index2) {
        this.index2 = index2;
    }

    public static  String  getName(Integer index){
        for (Color c : Color.values()
             ) {
            //因为是包装类，所以得用equals
            if(c.getIndex().equals(index)){
                return  c.getName();
            }
        }
        return null;
    }

    public static  String  getNameStr(String index){
        for (Color c : Color.values()){
            //String类型值之间直接比较
            if (c.getIndex2() == index){
                return  c.getName();
            }
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(getName(1));
        System.out.println(getNameStr("2"));
        Class enumClass= Color.class;
        Field[] fields = enumClass.getDeclaredFields();
        for (Field f: fields){
            System.out.println(f.getName());
        }
    }
}
