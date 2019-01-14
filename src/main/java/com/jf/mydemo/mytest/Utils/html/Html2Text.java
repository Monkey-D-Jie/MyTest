package com.jf.mydemo.mytest.Utils.html;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-12-12 14:12
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class Html2Text {
    public static String filterHtml(String htmlStr) {
        if (htmlStr == null) {
            return "";
        }
        //定义style的正则表达式
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        //定义script的正则表达式
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        //定义HTML标签的正则表达式
        String regEx_html = "<[^>]+>";
        //删除css
//        htmlStr = Regex.Replace(htmlStr, regEx_style, "");
//        //删除js
//        htmlStr = Regex.Replace(htmlStr, regEx_script, "");
//        //删除html标记
//        htmlStr = Regex.Replace(htmlStr, regEx_html, "");
//        //去除tab、空格、空行
//        htmlStr = Regex.Replace(htmlStr, "\\s*|\t|\r|\n", "");
//        //去除空格
//        htmlStr = htmlStr.Replace(" ", "");
//        //去除异常的引号
//        htmlStr = htmlStr.Replace("\"", "");
//
//        Pattern pattern = Pattern.compile(regxpForHtml);
//        Matcher matcher = pattern.matcher(htmlStr);
//        StringBuffer sb = new StringBuffer();
//        boolean result1 = matcher.find();
//        while (result1) {
//            matcher.appendReplacement(sb, "");
//            result1 = matcher.find();
//        }
//        matcher.appendTail(sb);
//        return sb.toString();
        return null;
    }
}
