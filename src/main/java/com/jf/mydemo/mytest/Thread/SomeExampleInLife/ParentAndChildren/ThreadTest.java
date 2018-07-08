package com.jf.mydemo.mytest.Thread.SomeExampleInLife.ParentAndChildren;

/**
 * Created by wjie on 2018/5/13.
 */
public class ThreadTest {

    public static void main(String[] args) {
        //子类中的方法会被优先的执行到
        Parent p = new Parent();
        p.sayHello();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("content in runnable's run");
            }
        })/*把子類部分給注釋掉就好了
        {
            public  void  run(){
                System.out.println("content in subThread run");
            }
        }*/.start();
    }

}
/**
 * 打印的结果是：
 * content in subThread run
 * 即第二个run中的数据。
 * 为什么呢？？
 * 因为：
 * 首先请明确一点，这里第二个run()跟Thread的关系。第二个run()是由{}包裹起来的。
 * 即是【new Thread(){}】的形式，意思即指 它是创建了一个Thread的子类。
 * 其次，子父类间的同名方法执行顺序问题。
 * 通过测试可以发现，当子类重写了父类的方法后，但凡最终的创建对象介质为子类的，
 * 方法最后的执行权利都是着重在子类上，即执行到的是子类中重写的同名方法。
 * 回到这里的场景也是一样，当重写Thread的run()后，且最终创建对象的是Thread的子类。
 * 故在调用start()开启了线程后，最终执行到的方法就是第二run()，而非第一个Runnable参数中的
 * run()。
 * 要想第一个run()被执行到，就不能用子类去开启线程，得直接用父类。
 * 这样才能保证Thread会去执行到自己架子中的默认方法。
 * 即判断arget参数是否为空，不为空的，才考虑执行runnable中的run方法，如果为空，
 * 则执行自己的run方法，该方法只是提供了一个壳子，并不会具体的去做事情。相当于给了一个进入
 * 其庄园的入口。
 *
 */
