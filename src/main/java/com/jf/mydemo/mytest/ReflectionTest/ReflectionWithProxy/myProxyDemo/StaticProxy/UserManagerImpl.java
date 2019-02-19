package com.jf.mydemo.mytest.ReflectionTest.ReflectionWithProxy.myProxyDemo.StaticProxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-18 23:25
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String userId, String userName) {
        System.out.println("UserManagerImpl.addUser"+"，userId："+userId+",userName："+userName);
    }

    @Override
    public void delUser(String userId) {
        System.out.println("UserManagerImpl.delUser");
    }

    @Override
    public String findUser(String userId) {
        System.out.println("UserManagerImpl.findUser");
        return "张三";
    }

    @Override
    public void modifyUser(String userId, String userName) {
        System.out.println("UserManagerImpl.modifyUser");

    }
}

