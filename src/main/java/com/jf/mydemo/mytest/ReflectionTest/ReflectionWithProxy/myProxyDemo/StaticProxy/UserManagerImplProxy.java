package com.jf.mydemo.mytest.ReflectionTest.ReflectionWithProxy.myProxyDemo.StaticProxy;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-18 23:28
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class UserManagerImplProxy implements UserManager {

    // 目标对象
    private UserManager userManager;
    // 通过构造方法传入目标对象
    public UserManagerImplProxy(UserManager userManager){
        this.userManager=userManager;
    }
    @Override
    public void addUser(String userId, String userName) {
        try{
            //添加打印日志的功能
            //开始添加用户
            System.out.println("start-->addUser()");
            userManager.addUser(userId, userName);
            //添加用户成功
            System.out.println("success-->addUser()");
        }catch(Exception e){
            //添加用户失败
            System.out.println("error-->addUser()");
        }
    }

    @Override
    public void delUser(String userId) {
        userManager.delUser(userId);
    }

    @Override
    public String findUser(String userId) {
        userManager.findUser(userId);
        return "张三";
    }

    @Override
    public void modifyUser(String userId, String userName) {
        userManager.modifyUser(userId,userName);
    }

}

