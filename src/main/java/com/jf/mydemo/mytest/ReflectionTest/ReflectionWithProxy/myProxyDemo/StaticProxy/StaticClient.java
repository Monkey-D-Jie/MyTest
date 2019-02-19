package com.jf.mydemo.mytest.ReflectionTest.ReflectionWithProxy.myProxyDemo.StaticProxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-18 23:29
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class StaticClient {

    public static void main(String[] args){
        UserManager userManager2=new UserManagerImplProxy(new UserManagerImpl());
        userManager2.addUser("2222", "张三");
    }
}

//---------------------
//        作者：何静媛
//        来源：CSDN
//        原文：https://blog.csdn.net/hejingyuan6/article/details/36203505
//        版权声明：本文为博主原创文章，转载请附上博文链接！
