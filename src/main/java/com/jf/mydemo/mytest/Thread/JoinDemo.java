package com.jf.mydemo.mytest.Thread;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-07 17:48
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

class JoinDemo implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("join线程第" + i + "次执行！------------");
        }
    }
}
