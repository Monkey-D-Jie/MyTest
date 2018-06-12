package com.jf.mydemo.mytest.CommonDemoTest;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-11 10:03
 * @Description: 递归的demo
 * To change this template use File | Settings | File and Templates.
 * <p>
 * ①：参考帖子
 * 1）递归算法及经典递归例子代码实现 - 君子笑而不语 - 博客园
 * https://www.cnblogs.com/xiaoliu66007/p/3927111.html
 * 2）递归整理及几个经典题目 - CSDN博客
 * https://blog.csdn.net/qq_34039315/article/details/78679029
 * <p>
 * ②：
 * ---什么叫递归？
 * 程序调用自身的编程技巧就叫递归
 * ---递归问题的核心部分
 * 基础情况和递归部分
 * 就好比是 高中求数列前n项和的算法，n=1，时 f(n)=1,n>1时，Fn=f(n-1)+1;
 * Tips:
 * 这里的demo，暂时先了解 怎么写的，具体的原理，后头再去研究
 */

public class RecursionDemo {
    /**
     * 1.简单的：阶乘
     */
    @Test
    public void factorialTest() {
        int n = 5;
        System.out.println("入参阶乘基数--->>" + n);
        int result = factorial(5);
        System.out.println(n + "的阶乘值为--->>" + result);
    }

    private int factorial(int n) {
        if (n == 0) {
            //0!=1
            return 1;
        } else {
            //当n>0后，n!=n*(n-1)*(n-2)*...1
            return n * factorial(n - 1);
        }
    }

    /**
     * 2.经典的 汉诺塔 问题
     * ----->这汉诺塔是真有毒！！！
     * 参考帖子：
     * https://www.zhihu.com/question/24385418
     * 首先，明确一点，汉诺塔的问题，解决思路就是三步：
     * 怎么把大象放进冰箱？
     * 首先是打开冰箱，然后把大象塞进冰箱，最后把冰箱门关上。。
     * 汉诺塔---
     * 假设是A，B,C三个柱子,n个盘子，怎样把所有的盘子通过从A通过B，最终挪到C
     * 首先是把A上的n-1个盘子从A---》B，
     * 然后把A上剩下的那个大盘子n，从A---》C上，
     * 最后把B上的n-1个盘子，从B---》C上
     * 这样就能把所有的盘子都挪到C上了。
     * ----注意这里面的规律,就(n-1,A,B,C)来说
     * 从A挪到B，则C是缓冲区，即(n-1，A，B，buffer)
     * 从A挪到C，则B是缓冲区，即（n-1，A，buffer，C）
     * 从B挪到C，则A是缓冲区，即（n-1,buffer,B,C）
     * ---假定对应的方法声明如下
     * hanoi(n,from,tmp,to)
     * ↓    ↓    ↓
     * 场景：	x， z， y三个柱子，n个盘子
     * 说明：以汉诺塔的规则 把x上的盘子从x，通过z，挪到y上
     * 分析：照着上面的说法，整个过程可以拆解为3步
     * 第一步：先把x上的n-1个盘子挪到z上，借助y,那么 【x，z，y为tmp】对应的调用方法即
     * hanoi(n-1,from，to，tmp)‘
     * 第二步：把x柱子上的最大盘子直接挪到y上
     * out(from---->n--->to)
     * 第三步：把z柱上的盘子，从z挪到y上，借助x，则有【在z,y,x为tmp】，对应的调用方法即
     * hanoi(n-1,tmp,from,to);
     * 至此，盘子就能按照为给说明的规律去执行了，
     */
    @Test
    public void hanoiTest() {
        int hannoiCount = 3;
        char from = 'x';
        char tmp = 'z';
        char to = 'y';
        hannoi(hannoiCount, from, tmp, to);
        hanoi(hannoiCount, from, tmp, to);

    }

    private void hannoi(int n, char from, char tmp, char to) {
        int count = 1;
        if (n > 0) {
            hannoi(n - 1, from, to, tmp);
            System.out.println("take--" + n + "--从" + from + "--->>>" + "到" + to);
            hannoi(n - 1, tmp, from, to);
        }
    }

    void hanoi(int n, char from, char tmp, char to) {
        if (n > 0) {
            hanoi(n - 1, from, to, tmp);
            System.out.println("take " + n + " from " + from + " to " + to);
            hanoi(n - 1, tmp, from, to);
        }
    }

    /**
     * 3.经典的 斐波拉契 数列
     * 兔子算数
     * 第一个月小兔子没有繁殖能力，所以还是一对；
     * <p>
     * 两个月后，生下一对小兔子，总数共有两对；
     * <p>
     * 三个月以后，老兔子又生下一对，因为小兔子还没有繁殖能力，总数共是三对；
     * <p>
     * ……
     * 如果所有兔子都不死，那么一年以后可以繁殖多少对兔子？
     * n=2 f(2) = f(1)[fn_1]+f(0)[fn_2];
     * n=3 f(3) = f(2)[fn-1]+f(1)[fn_2];
     * ...f(2)---f(2)[fn-1],f(1)[fn_1]---f(1)[fn_2]
     * 斜着来看。。。
     * 数学真的太jier重要了！！！！
     */
    @Test
    public void fibTest() {
        /**
         * 一年的话有多少对兔子？？
         * 一年，按12个月算..
         * 从0开始算
         */
        int year = 12;
        int result = fib(year);
        int recusionResult = fibRecursion(year);
        System.out.println("递归方法：一年后，一共有" + result + "对兔子");
        System.out.println("递推方法：一年后，一共有" + recusionResult + "对兔子");

    }

    /**
     * 递归
     *
     * @param n
     * @return
     */
    private int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }

    /**
     * 递推
     */
    private int fibRecursion(int n) {
        if (n <= 1) {
            return n;
        }
        int fn = 0;
        int fn_1 = 1;
        int fn_2 = 0;
        for (int i = 2; i <= n; i++) {
            fn = fn_1 + fn_2;
            fn_2 = fn_1;
            fn_1 = fn;
        }
        return fn;
    }

    /**
     * 4.应用上的-->全排列
     */
    @Test
    public void arrangeTest() {
        int[] arrange = {1, 2, 3};
        pai(arrange, 0, arrange.length);
        System.out.println("<<<--------------->>>");
        totalArrange(arrange,0,arrange.length-1);
    }

    /**
     * 自己就着思路写的
     *
     *
     * ----懵逼中。。。。排列组合这块儿，
     * 先放一放
     */
    private void totalArrange(int[] n,int cursor,int end) {
        if(cursor == end){
            System.out.println(ArrayUtils.toString(n));
        }else{
            for (int i = cursor; i <= end ; i++) {
                //把数组中的第一个都挪到第一位来（第一个也这样处理）
                swap(n,i,cursor);
                //得到cursor后的全排列序列结果，并和第一个组合
                totalArrange(n,cursor+1,end);
                //上面的代码将完成cursor+1后的排序工作，进入到下一次的挪位前，要把序列置为原样
                swap(n,i,cursor);
            }
        }
    }

    /**
     * 产生排列组合的递归写法
     *
     * 全排列算法的全面解析 - CSDN博客
     https://blog.csdn.net/lemon_tree12138/article/details/50986990
     *
     * @param t 数组
     * @param k 起始排列值
     * @param n 数组长度
     */
    void pai(int[] t, int k, int n) {
        if (k == n - 1) {//输出这个排列
            for (int i = 0; i < n; i++) {
                System.out.print(t[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = k; i < n; i++) {
                //一次挑选n个字母中的一个,和前位置替换
                swap(t, i, k);
                //再对其余的n-1个字母一次挑选
                pai(t, k + 1, n);
                //再换回来---->保证按现行规则进入到下一循环的序列是一致的
                swap(t, i, k);
            }
        }
    }

    private void swap(int[] t, int i, int j) {
        int temp = t[i];
        t[i] = t[j];
        t[j] = temp;
    }

    /**
     * 5.应用上的-->倒序输出一个正整数
     */


}
