package com.jf.mydemo.mytest.Utils.numberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-09-12 09:49
 * @Description: 处理保留小数位数的工具类(四舍五入也适用)
 * To change this template use File | Settings | File and Templates.
 * 摘自：
 * java保留两位小数4种方法 - CSDN博客
https://blog.csdn.net/ming1683/article/details/3195058
 */

public class NumberFormatUtil {

    /**
     * @Author: Wangjie
     * @Date:   2018-09-12 10:03:32
     * @Description: bigDecimalFormat格式化数据
     * @param  number   需要格式化的数据
     * @Return  java.lang.Object 格式化的结果
     * tip:数据量大的情况下，这个方法的效率最高
     * 测试结果：bigDecimalFormat--->>>111231.56
     */
    public static Object bigDecimalFormat(Object number) {
        BigDecimal bg = new BigDecimal((double)number);
        double result = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("bigDecimalFormat--->>>"+result);
        return result;
    }
    /**
     * DecimalFormat转换最简便
     */
    /**
     * @Author: Wangjie
     * @Date:   2018-09-12 10:04:32
     * @Description: decimalFormat格式化数据
     * @param  f   需要格式化的数据
     * @Return  java.lang.Object 格式化的结果
     * 测试结果：decimalFormat--->>>111231.56
     */
    public static Object decimalFormat(Object f) {
        DecimalFormat df = new DecimalFormat("#.00");
        String result = df.format(f);
        System.out.println("decimalFormat--->>>"+result);
        return result;
    }
    /**
     * String.format打印最简便
     */
    /**
     * @Author: Wangjie
     * @Date:   2018-09-12 10:05:32
     * @Description: stringFormat格式化数据
     * @param  f   需要格式化的数据
     * @Return  java.lang.Object 格式化的结果
     * 测试结果：stringFormat--->>>111231.56
     */
    public static Object stringFormat(Object f) {
        String result = String.format("%.2f", f);
        System.out.println("stringFormat--->>>"+result);
        return result;
    }

    /**
     * @Author: Wangjie
     * @Date:   2018-09-12 10:05:32
     * @Description: numberFormat格式化数据
     * @param  f   需要格式化的数据
     * @Return  java.lang.Object 格式化的结果
     * 测试结果：numberFormat--->>>111,231.56
     */
    public static Object  numberFormat(Object f) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        String result = nf.format(f);
        System.out.println("numberFormat--->>>"+result);
        return result;
    }
    public static void main(String[] args) {
        double f = 111231.5585;
        bigDecimalFormat(f);
        decimalFormat(f);
        stringFormat(f);
        numberFormat(f);
        /**
         * 运行结果：
         *
         bigDecimalFormat--->>>111231.56
         decimalFormat--->>>111231.56
         stringFormat--->>>111231.56
         numberFormat--->>>111,231.56
         */
    }
}
