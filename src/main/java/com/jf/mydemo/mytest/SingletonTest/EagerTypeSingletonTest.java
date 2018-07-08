package com.jf.mydemo.mytest.SingletonTest;

/**
 * Created by wjie on 2018/5/20.
 * 饿汉式单例-不管是否需要，先加载出可能会用到的对象
 * 特点:是线程安全的
 * 为什么是线程安全的呢？
 * ①：有共享数据；
 * ②：操作共享数据的代码只有一个 即 return single
 * 弊端:会降低代码的执行效率，因为要去作判断
 */
public class EagerTypeSingletonTest {
    //生成一个对外的唯一对象
    private static final EagerTypeSingletonTest single = new EagerTypeSingletonTest();
    //私有的构造方法
    private EagerTypeSingletonTest(){}
    //对外的获取单例对象的方法
    public static EagerTypeSingletonTest getInstance(){
        return single;
    }
}
