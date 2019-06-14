package com.jf.mydemo.mytest.Java8Action.OptinalNPE;

import org.junit.Test;

import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-06-14 15:27
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class OptinalTest {

    @Test
    public void test1() throws Exception {
        User user = new User();
        Address address = new Address();
        address.setCity("成都市");
        address.setArea("金牛区");
        address.setProvince("四川省");
        //注释掉后，就直接抛出了异常。因为没有Address属性值对象嘛
        user.setAddress(address);
        //这里将 入参user 置空后，也是一样的。直接就会到 orElseThrow的部分去
        System.out.println("获取到的地址信息为："+getCity(user));
//        Address address2 = new Address();
        System.out.println(ofNullMethod(user));
        System.out.println(ofMethod(user));
    }

    public static String getCity(User user) throws Exception {
        return
                //这里用 ofNullable 有异常就会直接扔出来
                Optional.ofNullable(user)
                .map(u->u.getAddress())
                .map(address -> address.getCity())
                .orElseThrow(()->new Exception("地址信息获取错误！"+Optional.empty()));
    }

    public Optional<User> ofNullMethod(User user){
        //当传入对象user是 null 时，它这里返回的值为 Optional.empty
        return Optional.ofNullable(user);
    }
    public Optional<User> ofMethod(User user){
        //当传入的对象为 null 时，它这里会报 空指针 异常
        return Optional.of(user);
    }

    @Test
    public void orElseTest() throws Exception {
        User user = new User();
        user.setName("测试数据");
        System.out.println(user.toString());
        User testUser  = null;
        testUser = Optional.ofNullable(testUser).orElse(createUser());
        System.out.println(testUser.toString());
        User user2 = null;
        user2 = Optional.ofNullable(user2).orElseGet(() -> createUser());
        System.out.println(user2.toString());
        User user3 = new User();
        user3.setName("testtest");
        user3 = Optional.ofNullable(user3).orElse(createUser());
        System.out.println(user3.toString());
//        Optional.ofNullable(user3).orElseThrow(()->new Exception("用户不存在"));
    }

    public User createUser(){
        User user = new User();
        user.setName("zhangsan");
        return user;
    }

}
