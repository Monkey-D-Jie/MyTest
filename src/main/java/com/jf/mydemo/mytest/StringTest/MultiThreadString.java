package com.jf.mydemo.mytest.StringTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-04 11:22
 * @Description: StringBuffer，StringBuilder多线程测试类
 * To change this template use File | Settings | File and Templates.
 */

public class MultiThreadString implements Runnable{
    private StringBuilder stringBuilder;
    private StringBuffer stringBuffer;
    public MultiThreadString(StringBuilder stringBuilder,StringBuffer stringBuffer){
        this.stringBuilder = stringBuilder;
        this.stringBuffer = stringBuffer;
    }
    @Override
    public void run() {
        try{
            for (int i = 0; i < 1000; i++) {
                this.stringBuilder.append("A");
                this.stringBuffer.append("B");
            }
            Thread.sleep(10);

        }catch (InterruptedException e) {
            System.out.println("有异常抛出，具体的信息请见下方");
            System.out.println("\\/t\\/t ↓");
            System.out.println(e.getMessage());
        }
        System.out.println("StringBuffer Size:" + stringBuffer.length()
                + " | "
                + "StringBuilder Size:" + stringBuilder.length());
    }
}
