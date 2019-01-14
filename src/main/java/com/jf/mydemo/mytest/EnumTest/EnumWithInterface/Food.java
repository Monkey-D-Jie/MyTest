package com.jf.mydemo.mytest.EnumTest.EnumWithInterface;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-14 14:49
 * @Description: 在接口中放置枚举，对具有相统属性的类作整合
 * To change this template use File | Settings | File and Templates.
 */

public interface Food {
    enum Coffee implements Food{
        BLACK_COFFEE,DECAF_COFFEE,LATTE,CAPPUCCINO
    }
    enum Dessert implements Food{
        FRUIT,CAKE,GELATO
    }
}
