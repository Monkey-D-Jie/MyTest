package com.jf.mydemo.mytest.ReflectionTest.ReflectionWithProxy.myProxyDemo.StaticProxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-18 23:24
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public interface UserManager {
     void addUser(String userId, String userName) ;
     void delUser(String userId);
     String findUser(String userId) ;
     void modifyUser(String userId, String userName);
}
