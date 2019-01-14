package com.jf.mydemo.mytest.EnumTest.EnumInterface;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-14 14:37
 * @Description: 实现接口的枚举类
 * To change this template use File | Settings | File and Templates.
 */

public enum InterfaceColorEnum implements Behaviour{

    RED("红色",1),GREEN("绿色",2),
    BLACK("黑色",3),BLUE("蓝色",4);

    /**
     * 名称
     */
    private String name;
    /**
     * 下标值
     */
    private Integer index;

    InterfaceColorEnum(String name,Integer index){
        this.name = name;
        this.index = index;
    }

    @Override
    public void show() {
        System.out.println(this.index+"_"+this.name);
    }

    @Override
    public String getInfo() {
        return this.name;
    }
}
