package com.jf.mydemo.mytest.ExceptionTest;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/1/10 0010
 * @time: 10:48
 * To change this template use File | Settings | File and Templates.
 */

public class ThrowExcetionTest {

    @Test
    public void throwTest(){
        try{
            try{
                System.out.println("第二层的try-catch");
                String  string = null;
                System.out.println(string.length());
            }catch (Exception e){
                throw e;
            }
            try{
                System.out.println("第一层的try-catch");
                int a = 2/0;
            }catch(Exception e){
                System.out.println("捕获了异常"+e.getMessage());
            }
            System.out.println("第一层try-cathc捕获了异常，我得以输出-----");

        }catch (Exception e){
            System.out.println("最外层try-cathc捕获了异常，我得以输出-----"+e.getMessage());
        }
    }
}
