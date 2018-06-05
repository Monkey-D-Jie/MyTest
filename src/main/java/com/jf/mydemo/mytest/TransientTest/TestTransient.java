package com.jf.mydemo.mytest.TransientTest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/2/27 0027
 * @time: 18:41
 * To change this template use File | Settings | File and Templates.
 * 用例来自
 * transient关键字详解 - CSDN博客  http://blog.csdn.net/u013207877/article/details/52572975
 *
 *  * transient 关键字，被它修饰的属性不会被序列化。
 * （暂时先不管这个，有任务来了）
 *transient是Java语言的关键字，用来表示一个域不是该对象串行化的一部分。当一个对象被串行化的时候，
 * transient型变量的值不包括在串行化的表示中，然而非transient型的变量是被包括进去的。
【
transient：
transient关键字详解 - CSDN博客  http://blog.csdn.net/u013207877/article/details/52572975
transient的用途在于：阻止实例中那些用此关键字声明的变量持久化；
当对象被反序列化时（从源文件读取字节序列进行重构），这样的实例变量值不会被持久化和恢复。
比如你输入密码，只是希望它在登录时有用，你退出后，别人不能通过反序列化的方式把它的值给找到。
序列化：数据按照统一的规则 rule-one 变成数据流；
反序列化：获得数据时，也通过 rule-one 来重现数据；
使用transient值的一些注意事项：
1，一旦变量被transient修饰，变量将不再是对象持久化的一部分，该变量内容在序列化后无法获得访问。
　　2，transient关键字只能修饰变量，而不能修饰方法和类。注意，本地变量是不能被transient关键字修饰的。变量如果是用户自定义类变量，则该类需要实现Serializable接口。
　　3，被transient关键字修饰的变量不再能被序列化，一个静态变量不管是否被transient修饰，均不能被序列化。
反序列化后类中static型变量name的值为当前JVM中对应static变量的值，这个值是JVM中的不是反序列化得出的
】
 *
 */

public class TestTransient {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo("张三", "123456");
        System.out.println(userInfo);
        try {
            // 序列化，被设置为transient的属性没有被序列化
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("UserInfo.txt"));
            o.writeObject(userInfo);
            o.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        try {
            //在反序列化之前改变name的值---》由于是静态变量，所以当前JVM中的name值会变成Hello，
            //此时再去取值，获得的即是Hello---》即 反序列化后类中static型变量name的值为当前JVM中对应static变量的值，
            // 这个值是JVM中的，不是反序列化得出的---》如果是反序列化得出的，则就算是在这里设置了Hello，也会得到 ‘张三’
            userInfo.setName("hello");
            //测试的结果是 hello！
            // 重新读取内容
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("UserInfo.txt"));
            UserInfo readUserInfo = (UserInfo) in.readObject();
            //读取后psw的内容为null
            System.out.println(readUserInfo.toString());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
