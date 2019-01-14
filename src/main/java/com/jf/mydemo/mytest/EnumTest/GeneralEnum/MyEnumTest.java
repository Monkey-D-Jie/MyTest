package com.jf.mydemo.mytest.EnumTest.GeneralEnum;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-01-14 16:40
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class MyEnumTest {
    private enum TYPE{
        IRON_MAN("stark"),
        HALK("banna"),
        CAPITAIN("steven");
        String  enumName;
        TYPE(String name){
            this.enumName = name;
        }
        public String getName() {
            return enumName;
        }
        public void setName(String name) {
            this.enumName = name;
        }
    }
    public static void main(String[] args) {
        System.out.println(TYPE.CAPITAIN == TYPE.CAPITAIN);
        System.out.println(fromTypeName("stark"));
    }
    public static  TYPE fromTypeName(String typeName){
        for (TYPE type : TYPE.values()
             ) {
            System.out.println(type.name());
            if (type.getName().equals(typeName)){
                return type;
            }
        }
        return null;
    }
}
