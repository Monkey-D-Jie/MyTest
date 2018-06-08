package com.jf.mydemo.mytest.Thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-07 11:07
 * @Description: 新的线程测试
 * To change this template use File | Settings | File and Templates.
 */

public class ThreadDemo2 implements Runnable{
    @Override
    public void run() {
        int x = 0;
        while(true){
            if(x == 20){
                break;
            }
            System.out.println("别的线程阻塞没关系，我只管输出就好------"+x);
            x++;
        }
    }
}
