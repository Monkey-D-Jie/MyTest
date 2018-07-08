package com.jf.mydemo.mytest.SingletonTest;

/**
 * Created by wjie on 2018/5/20.
 * 懒汉式单例-需要的时候创建
 * 线程不安全
 * 原因：
 * ①：有共享数据
 * ②：操作共享数据的代码有多条
 * 优势和弊端同饿汉式单例类似
 */
public class LazyTypeSingletonTest {
    //创建一个自身的实例对象，因为是单例，所以不能用final去修饰
    private static LazyTypeSingletonTest single = null;

    //属于自己的私有构造方法
    private LazyTypeSingletonTest() {
    }

    //提供的对外获取单例对象的方法
    public static LazyTypeSingletonTest getInstance() {
        /**
         * 改进方法：加同步！
         * 因为是静态方法，所以锁对象为 类名.class
         * --->进一步改进，双重判断，减少对锁的判断次数
         */
        if (single == null) {
            synchronized (LazyTypeSingletonTest.class) {
                if (single == null) {
                    /**
                     * 这里就是造成线程不安全的原因：
                     * 不同的线程会因为CPU切换执行的缘故，能先后的进入到这个部分。
                     * 这样就会造成生成多个Single02的情况。
                     * 该状态是不符合单例定义的
                     */
                    single = new LazyTypeSingletonTest();
                }
            }
        }
        return single;
    }
}
