package com.jf.mydemo.mytest.Thread.SomeExampleInLife.myThreadTest;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author wangjie
 * @Date 2018-06-04 23:08
 * @Description 测试用的资源类,资源描述对象
 */
public class Resource {

    private String name;
    private String sex;

    //用于协助生成间隔输出结果的标记
    public boolean flag = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * @Author  wangjie
     * @Date    2018-06-05 23:01:40
     * @Description 重构代码的方法
     * 1.重构设置值的方法；
     * 2.重构输出的方法；
     * 3.两者都属于是共享对象的代码，且会被操作多次，故有线程安全的问题
     * 所以需要加上锁；
     * 4.锁对象即调用这些方法的对象本身，也即this(到底是指调用方法的对象还是方法所在对象？)
     * 由此可以写出下面的代码
     * 5.升级为Lock锁来保证线程的安全---->使用了lock锁，就不需要再使用同步了，不然，会造成死锁，程序假死的情况
     */
    //先要创建锁对象，并保证唯一性
    private final Lock lock = new ReentrantLock();
    //然后获取到该锁对应的监视器对象
    private  Condition condition = lock.newCondition();

    public /*synchronized*/ void set(String name,String sex){
            try {
                lock.lock();
            if (this.flag) {
                try {
//                    System.out.println("this对象指到底是？" + this.getClass().toString());
                    /**
                     * 从打印结果来看，这里的this指的就是调用了该方法的对象本身，即Resource。
                     * 而非当前类。
                     */
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name;
            this.sex = sex;
            this.flag = true;
//            this.notify();
            condition.signal();
            }finally {
                lock.unlock();
            }
    }

    public /*synchronized*/ void out(){
        try {
            lock.lock();
            if (!this.flag) {
                try {
//                    this.wait();
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(name + "-----" + sex);
            this.flag = false;
//            this.notify();
            condition.signal();
        }finally {
            lock.unlock();
        }
    }


}
