package com.jf.mydemo.mytest;

import org.junit.Test;

import java.util.TimerTask;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/16 0016
 * @time: 17:16
 * To change this template use File | Settings | File and Templates.
 */

public class MyCostumeTest {
    /**
     * @author: wjie
     * @date: 2018/3/16 0016 17:39
     * <p>
     * >> 右移,高位补符号位” 这里右移一位表示除2
     * “>>> 无符号右移,高位补0”； 与>>类似
     * “<< 左移” 左移一位表示乘2，二位就表示4，就是2的n次方
     * <p>
     * 设a=8，则表达式a＞＞＞2的值是( )。
     * A) 1
     * B) 2
     * C) 3
     * D) 4
     * 正确答案
     * B
     * 答案解析
     * [解析] 本题具体考查对位运算符中无符号右移运算符的掌握。
     * 无符号右移运算符“＞＞＞”用于将一个数的各二进制位全部无符号右移若干位，与运算符“＞＞”不同的是左补0。
     * 在本题中，8的二进制表示是1000，右移两位后变成了0010，对应的十进制数是2。
     */
    @Test
    public void costumeTest() {
        int n = 2;
        System.out.println("(n << 1)的值:" + (n << 1));
        System.out.println("(n >>> 16)的值:" + (n >>> 16));
        int result = (n << 1) - (n >>> 1);
        System.out.println("(n << 1) - (n >>> 1)的值为:" + result);
        String OS_NAME = System.getProperty("os.name").toLowerCase();
        System.out.println(OS_NAME);
    }

    public class MyTask extends TimerTask {
        int count = 0;

        @Override
        public void run() {
            count++;
            System.out.println("------count:" + count);
            if (count == 10) {
                super.cancel();
            }
        }
    }
}

