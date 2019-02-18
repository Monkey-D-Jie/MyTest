package com.jf.mydemo.mytest.ReflectionTest.RefDemo;

import com.jf.mydemo.mytest.ReflectionTest.RefBean.User;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-02-18 13:07
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class MyReflectionTest {


    /**
     * 通过不同的方法获取到类对象
     *
     * @author: Wangjie
     * @date:   2019-02-18 13:16:34
     * @description: 以user类为蓝本
     * @return  void
     * 测试结果：
     * 获取到的对象名称都是一样的，而且三个也都是同一个对象
    com.jf.mydemo.mytest.ReflectionTest.RefBean.User
    com.jf.mydemo.mytest.ReflectionTest.RefBean.User
    com.jf.mydemo.mytest.ReflectionTest.RefBean.User
    c1 == c2 == c3 ：true
     */
    @Test
    public void getClasses() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class c1 = User.class;
        System.out.println(c1.getName());
        User user = new User("流浪地球",2500);
        Class c2 = user.getClass();
        System.out.println(c2.getName());
        //注意，用这种方法获取类对象，用的是类的全包路径哈
        Class c3 = Class.forName("com.jf.mydemo.mytest.ReflectionTest.RefBean.User");
        System.out.println(c3.getName());
        System.out.println("c1 == c2 == c3 ："+((c1 == c2)&&(c2 == c3)));
    }

    /**
     * 通过反射获取类中的方法
     *
     * @author: Wangjie
     * @date:   2019-02-18 13:45:07
     * @description:
     * 测试结果：
    通过getDeclaredMethods获取com.jf.mydemo.mytest.ReflectionTest.RefBean.User中的方法
    toString
    getName
    setName
    show
    changeMsg
    setPhone
    getPhone
    getAge
    setAge
    通过getMethods获取com.jf.mydemo.mytest.ReflectionTest.RefBean.User中的方法
    toString
    getName
    setName
    show
    setPhone
    getPhone
    getAge
    setAge
    wait
    wait
    wait
    equals
    hashCode
    getClass
    notify
    notifyAll
    通过getMethod获取到指定的方法:show
    通过getDeclaredMethod获取到私有的方法:changeMsg
    My name is 张弛,I am 32 years old
    msg changed.Now the msg is 我是反射调用方法传入的信息
     */
    @Test
    public void getMethodTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class userClass = User.class;
        //①：获取到所有的方法
        Method[] methods = userClass.getDeclaredMethods();
        System.out.println("通过getDeclaredMethods获取"+userClass.getName()+"中的方法");
        for (Method method:methods
             ) {
            System.out.println(method.getName());
        }
        //②：获取到所有方法，包括父类的(User的父类是Object)
        Method[] methods2 = userClass.getMethods();
        System.out.println("通过getMethods获取"+userClass.getName()+"中的方法");
        for (Method method:methods2
                ) {
            System.out.println(method.getName());
        }
        //③：获取到该类的带有指定参数列表的方法
        Method method1 = userClass.getMethod("show",String.class,Integer.class);
        System.out.println("通过getMethod获取到指定的方法:"+method1.getName());
        //④：获取到该类的私有方法
        Method method2 = userClass.getDeclaredMethod("changeMsg",String.class);
        System.out.println("通过getDeclaredMethod获取到私有的方法:"+method2.getName());
        method2.setAccessible(true);
        //调用方法
        //invoke方法中的object得是class的实例对象,即User对象，可以是实例，也可以构造函数创建的对象
        method1.invoke(new User(),"张弛",32);
        method2.invoke(userClass.newInstance(),"我是反射调用方法传入的信息");
    }

    /**
     * 获取类中的属性值
     *
     * @author: Wangjie
     * @date:   2019-02-18 14:17:37
     * @description:
     * 测试结果：
     *通过getDeclaredFields获取到的信息:
    name
    age
    phone
    msg
    通过getFields获取到的信息
    phone
    通过getDeclaredField获取的属性:class java.lang.String：msg
    通过getField获取的属性:class java.lang.String：phone
     */
    @Test
    public void getFieldTest() throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class userClass = User.class;
        //能够获取到类中的公有和私有属性
        Field[] fields1 = userClass.getDeclaredFields();
        System.out.println("通过getDeclaredFields获取到的信息:");
        for (Field field:fields1
                ) {
            System.out.println(field.getName());
        }
        //由于user类中的属性均为私有的，所以这个获取公有属性的方法中没有数据展示出来
        Field[] fields = userClass.getFields();
        System.out.println("通过getFields获取到的信息");
        for (Field field:fields
             ) {
            System.out.println(field.getName());
        }
        Field field1 = userClass.getDeclaredField("msg");
        System.out.println("通过getDeclaredField获取的属性:"+field1.getType()+"："+field1.getName());
        //修改属性
        field1.setAccessible(true);
        field1.set(userClass.newInstance(),"我是新设置的新msg信息");
        System.out.println(field1.get("msg"));
        //这个方法只能获取到类中的公有属性
        Field field = userClass.getField("phone");
        System.out.println("通过getField获取的属性:"+field.getType()+"："+field.getName());
    }

    /**
     * 获取到类中的构造函数
     *
     * @author: Wangjie
     * @date:   2019-02-18 14:31:38
     * @description: 同样是以user为蓝本
     * 测试结果：
    通过getDeclaredConstructors获取到的信息为:
    public com.jf.mydemo.mytest.ReflectionTest.RefBean.User()
    public com.jf.mydemo.mytest.ReflectionTest.RefBean.User(java.lang.String,java.lang.Integer)
    通过getConstructors获取到的信息为:
    public com.jf.mydemo.mytest.ReflectionTest.RefBean.User()
    public com.jf.mydemo.mytest.ReflectionTest.RefBean.User(java.lang.String,java.lang.Integer)
    User{name='耿浩', age=33, phone='null', msg='Hello Reflection World'}
    通过getDeclaredConstructor获取到指定构造函数:public com.jf.mydemo.mytest.ReflectionTest.RefBean.User(java.lang.String,java.lang.Integer)
    通过getConstructor获取到指定构造函数:public com.jf.mydemo.mytest.ReflectionTest.RefBean.User()
     */
    @Test
    public void getConstructors() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class userClass = User.class;
        Constructor<?>[] constructors = userClass.getDeclaredConstructors();
        System.out.println("通过getDeclaredConstructors获取到的信息为:");
        for (Constructor<?>constructor1:constructors
             ) {
            System.out.println(constructor1.toString());
        }
        Constructor<?>[] constructors2 = userClass.getConstructors();
        System.out.println("通过getConstructors获取到的信息为:");
        for (Constructor<?>constructor2:constructors2
                ) {
            System.out.println(constructor2.toString());
        }
        Constructor<?> constructor = userClass.getDeclaredConstructor(String.class,Integer.class);
        User user = (User) constructor.newInstance("耿浩",33);
        System.out.println(user.toString());
        System.out.println("通过getDeclaredConstructor获取到指定构造函数:"+constructor.toString());
        Constructor<?> constructor1 = userClass.getConstructor();
        System.out.println("通过getConstructor获取到指定构造函数:"+constructor1.toString());
    }

    /**
     * 反射和泛型的关系
     *
     * @author: Wangjie
     * @date:   2019-02-18 15:02:58
     * @description: 泛型是一种在编译期阶段的规约，
     *以确保数据类型的准确性
     *
     测试结果
    添加了元素的list1:[123, 20]
    添加了元素的list2:[123]
    true
    [123, 20]
    实验结果表明：
    泛型在编译期能检查数据类型的正确性，但是在运行期就不能起作用了，比如这里
    通过反射的方式，在程序运行过程中调用list的add方法，就能往其中加入非String类型的
    元素
     * */
    @Test
    public void ReflectionWithGeneric() throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //①：先创建两个List
        //无泛型限制
        List list1 = new ArrayList<>();
        //有泛型限制
        List<String> list2 = new ArrayList<>();

        //②：添加元素
        list1.add("123");
        list1.add(20);
        System.out.println("添加了元素的list1:"+list1.toString());
        list2.add("123");
        //因为有泛型的限制声明，这里添加的20不是String类型数据，所以会报错
//        list2.add(20);
        System.out.println("添加了元素的list2:"+list2.toString());
        //③：用泛型的方式向list2中添加元素
        Class c1= list1.getClass();
        Class c2 = list2.getClass();
        System.out.println(c1 == c2);
        //向List2中添加元素
        Method method = c2.getMethod("add",Object.class);
        method.invoke(list2,20);
        System.out.println(list2.toString());
    }

}
