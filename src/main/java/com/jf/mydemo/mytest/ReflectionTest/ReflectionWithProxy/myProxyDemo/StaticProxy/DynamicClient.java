package com.jf.mydemo.mytest.ReflectionTest.ReflectionWithProxy.myProxyDemo.StaticProxy;

import com.jf.mydemo.mytest.ReflectionTest.ReflectionWithProxy.myProxyDemo.DynamicProxy.LogHandler;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-18 23:29
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class DynamicClient {

    public static void main(String[] args){
        LogHandler logHandler=new LogHandler();
        UserManager userManager1=(UserManager)logHandler.newProxyInstance(new UserManagerImpl());
        userManager1.addUser("1111", "张三");
        userManager1.findUser("1111");
    }
}

//---------------------
//        作者：何静媛
//        来源：CSDN
//        原文：https://blog.csdn.net/hejingyuan6/article/details/36203505
//        版权声明：本文为博主原创文章，转载请附上博文链接！
