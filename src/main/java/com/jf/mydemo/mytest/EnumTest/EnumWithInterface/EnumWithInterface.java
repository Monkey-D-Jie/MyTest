package com.jf.mydemo.mytest.EnumTest.EnumWithInterface;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-14 16:14
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class EnumWithInterface {

    public static void main(String[] args) {
        for(Food.Dessert dessert : Food.Dessert.values()){
            System.out.print(dessert+" ");
            System.out.print("|dessertOrdinal："+dessert.ordinal()+"_");

        }
        System.out.println();
        for (Food.Coffee coffee : Food.Coffee.values()) {
            System.out.print(coffee + "  ");
        }
        System.out.println();
        Food.Dessert fruit1 = Food.Dessert.FRUIT;
        Food.Dessert fruit2 = Food.Dessert.FRUIT;
        System.out.println("枚举内部对象间的比对："+(fruit1 == fruit2));
        Food food = Food.Dessert.CAKE;
        System.out.println(food);
        food = Food.Coffee.BLACK_COFFEE;
        System.out.println(food);
    }
}
