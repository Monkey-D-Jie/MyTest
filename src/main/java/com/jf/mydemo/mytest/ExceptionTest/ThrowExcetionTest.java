package com.jf.mydemo.mytest.ExceptionTest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/1/10 0010
 * @time: 10:48
 * To change this template use File | Settings | File and Templates.
 */

public class ThrowExcetionTest {
    /**
     * 打印日志用logger-用debug级别
     *
     */
    private Logger LOGGER = LoggerFactory.getLogger(ThrowExcetionTest.class.getName());

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
    @Test
    public void returnExceptionTest(){
        System.out.println(returnTest("Hello World",1));
    }
    public String returnTest(String string,int type){
        try {
            if(type == 1){

//                try {
//                    String str = null;
//                    System.out.println(str.toString());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    throw  e;
//                }
//                try{
//                    String str2 = "23A";
//                    System.out.println(Integer.parseInt(str2));
//                }catch (Exception e){
//                    e.printStackTrace();
//                    throw  e;
//                }
//                try{
//                    int a = 0;
//                    System.out.println(type/a);
//                }catch (Exception e){
//                    e.printStackTrace();
//                    throw  e;
//                }
                String str = null;
//                System.out.println(str.toString());
                String str2 = "23A";
//                System.out.println(Integer.parseInt(str2));
                int a = 0;
                System.out.println(type/a);
            }
            return "From try moudules";
        }catch (ArithmeticException e){
            if (LOGGER.isDebugEnabled()) {
                // ★使用{}占位符。避免字符串连接操作，减少String对象（不可变）带来的内存开销
                LOGGER.debug("XXX {}", e.getMessage());
            }
            LOGGER.info("异常占位型内容输出测试{}",e.getMessage());
            System.out.println("ArithmeticException");
            e.printStackTrace();
        }catch (NumberFormatException e){
            System.out.println("NumberFormatException");
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("NullPointerException");
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            return string;
        }
    }
}
