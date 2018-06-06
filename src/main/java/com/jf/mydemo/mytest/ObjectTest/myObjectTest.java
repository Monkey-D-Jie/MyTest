package com.jf.mydemo.mytest.ObjectTest;

import com.jf.mydemo.mytest.ObjectTest.Clone.Student;
import com.jf.mydemo.mytest.ObjectTest.Clone.Teacher;
import com.jf.mydemo.mytest.ObjectTest.Reflect.Person;
import com.jf.mydemo.mytest.ObjectTest.Serializable.User;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-05 10:30
 * @Description: 创建对象的测试类
 * To change this template use File | Settings | File and Templates.
 * ------------------------------------------------
 * ？在Java OO 思想下创建对象的方法有哪几种？
 * 1.new 构造器
 * ---平时都在用，不用说了嘛
 *------------------------------------------------
 * 2.序列化和反序列化
 * <p>来自:深入分析Java的序列化与反序列化-HollisChuang's Blog（相当不错的一篇帖子）
 * http://www.hollischuang.com/archives/1140
 *
 * ------------------------------------------------
 * 3.利用反射
 *  参考帖：
 *  来自
 *  Java反射机制创建对象 - CSDN博客
https://blog.csdn.net/smartboy_01/article/details/23201391
 *要点摘录:
 * 通过反射：
 * ①：可以创建对象；
 * ②：可以获取方法（有参数的，无参数的都可以），并调用之（私有的要设置accessible为true）；
 * ③：可以获取到属性，并调用之。（私有的要设置accessible为true）
 *
 *
 * ------------------------------------------------
 * 4.直接Clone
 * 参考：
 * 1）clone方法是如何工作的 - ImportNew
 * http://www.importnew.com/6960.html
 * 2）Java中创建对象的5种不同方法 - ImportNew
 * http://www.importnew.com/22405.html
 * 意味着clone()返回的对象可能会违反这些约定（通过调用super.clone()方法返回的对象），当重写clone()方法时，你可以遵循前面两条（a.clone()!=a和a.clone().getClass()==a.getClass()）。
 * 为了遵循第三个特性（clone.equals(a)），你必须重写equals方法。
 */

public class myObjectTest {


    /**
     * 2.序列化和反序列化测试
     * 测试结果：
     * 未序列化之前，user的信息为：User{name='冯宝宝', age=24, birthday=Wed Jun 06 10:37:37 CST 2018, gender='女'}
     * ----------------------------------------------------------静态成员测试
     * 反序列化后得到的对象信息:User{name='冯宝宝', age=24, birthday=Wed Jun 06 10:37:37 CST 2018, gender='null'}
     * ----------------------------------------------------------静态成员测试
     * gender 是transien修饰的，所以不能序列化，故为null
     */
    @Test
    public void serializableTest() {
        //initialize the object
        User user = new User();
        user.setName("冯宝宝");
        user.setAge(24);
        user.setBirthday(new Date());
        user.setGender("女");
        System.out.println("未序列化之前，user的信息为：" + user.toString());
        System.out.println("----------------------------------------------------------" + User.flag);
        //serializable the object-->write object to file
        ObjectOutputStream oos = null;
        try {
            /**
             * 将user的信息用序列化的方式写入到内存的临时文件tempFile中
             */
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }

        //unserializable the object--->read object from file
        /**
         * 创建一个名为tempFile的文件。其他名字行吗？？
         * 不行！
         * 因为前期FileOutputStream生成内存临时文件时，用的文件描述符（即一种标识）是tempFile，
         * 要想拿到它里面的字节流数据，则必须用用同样的标识去找、
         */
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            /**
             * 找到数据流的关键点最终都是落在了文件名 tempFile上
             */
            ois = new ObjectInputStream(new FileInputStream(file));
            User user1 = (User) ois.readObject();
            System.out.println("反序列化后得到的对象信息:" + user1);
            System.out.println("----------------------------------------------------------" + User.flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                IOUtils.closeQuietly(ois);
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 2-1 序列化的自定义-ArrayList
     * 在ArrayList的源码中，我们知道，其底层是数组实现的
     * 下面是加入元素的原理---》即往elementData数组中加入元素
     * public boolean add(E e) {
     * ensureCapacityInternal(size + 1);  // Increments modCount!!
     * elementData[size++] = e;
     * return true;
     * }
     * 注意： transient Object[] elementData;
     * elementData数组是被transient修饰的，则说明：存入该数组的元素不应该被序列化，反序列化输出后应该是元素的原始值，在这里应该是null
     * (这样限定的目的：用时间换空间---》避免了将null的元素放在内存内，但是要用循环来保证正常元素的持久化，就算元素是空，也会被遍历到)
     * 测试结果：
     * init StringList[hello, world, hollis, chuang]
     * new StringList[hello, world, hollis, chuang]
     * 反序列化输出的list，其数据是完整的，并非为null。
     * 这是为什么呢？？
     * ①：首先要明确，ArrayList在实现上，是作了可序列化的声明的
     * public class ArrayList<E> extends AbstractList<E>
     * implements List<E>, RandomAccess, Cloneable, java.io.Serializable
     * ②：保证其能序列化的要素：writeObject()，readObject()，它俩是其自定义的方法
     * 在序列化过程中，如果被序列化的类中定义了writeObject 和 readObject 方法，
     * 虚拟机会试图调用对象类里的 writeObject 和 readObject 方法，进行用户自定义的序列化和反序列化（即序列化过程有用户自行控制）。
     * 如果没有，才会走default的方法。
     */
    @Test
    public void serializableArrayListTest() throws IOException, ClassNotFoundException {
        List<String> stringList = new ArrayList<String>();
        stringList.add("hello");
        stringList.add("world");
        stringList.add("hollis");
        stringList.add("chuang");
        System.out.println("init StringList" + stringList);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("stringlist"));
        objectOutputStream.writeObject(stringList);

        IOUtils.closeQuietly(objectOutputStream);
        File file = new File("stringlist");
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
        List<String> newStringList = (List<String>) objectInputStream.readObject();
        IOUtils.closeQuietly(objectInputStream);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("new StringList" + newStringList);
    }
    /**
     * 3.反射方法创建对象的测试
     */
    @Test
    public void reflectTest() throws Exception {
        System.out.println("------------------反射获取对象--------------------------------------");
        Class class1 = Class.forName("com.jf.mydemo.mytest.ObjectTest.Reflect.Person");
        Person person1 = (Person)class1.newInstance();
        System.out.println("【Class.forName.newInstance()方法创建对象】person是否为Person的一个实例对象"+(person1 instanceof Person));
        Class class2 = Person.class;
        Constructor constructor = class2.getConstructor(String.class,int.class);
        Person person2 = (Person) constructor.newInstance("Jacky",24);
        System.out.println("【Person.class.getConstructor(parameters).newInstance()方法创建对象】person是否为Person的一个实例对象"+(person2 instanceof Person));
        System.out.println("其信息为："+person2.toString());
        System.out.println("-------------------反射获取方法-------------------------------------");
        Class class3 = Person.class;
        Method method = class3.getMethod("publicHelloMethod",String.class,   int.class);
        System.out.println("反射获取公有方法:"+method.getName());
        Person p = (Person) class3.getConstructor().newInstance();
        method.invoke(p,"小崔",42);
        Method method2 = class3.getDeclaredMethod("privateHelloMethod",String.class, int.class);
        System.out.println("反射获取私有方法:"+method2.getName());
        method2.setAccessible(true);
        method2.invoke(p,"小刚",42);
        System.out.println("反射获取所用方法:");
        Method[] methods = class3.getDeclaredMethods();
        for (int i = 0; i <methods.length ; i++) {
            System.out.println(methods[i].getName());
        }
        System.out.println("--------------------反射获取属性，并修改属性------------------------------------");
        Person p2 = new Person("小明",30);
        System.out.println("未修改属性前的信息:"+p2.toString());
        Class classType = Person.class;
        Field field = classType.getDeclaredField("name");
        Field field2 = classType.getDeclaredField("age");
        field.setAccessible(true);
        field2.setAccessible(true);
        field.set(p2, "lxf");
        field2.set(p2, 23);
        System.out.println("使用反射机制修改被private修饰的name是：" + p2.getName());
        System.out.println("使用反射机制修改被private修饰的age是：" + p2.getAge());


    }

    /**
     * 4.Clone方法创建对象单元测试
     */
    @Test
    public void cloneTest() {
        Student student = new Student();
        student.setName("小王");
        student.setAge("18");
        Teacher teacher = new Teacher();
        teacher.setName("张老师");
        teacher.setAge("30");
        student.setTeacher(teacher);
        System.out.println("初始创建的student对象的信息为:" + student.toString());
        System.out.println("----------------------------------------------------------");
        //clone对象
        try {
            Student studentClone = (Student) student.clone();
            //输出结果：false，说明是存在于堆中不同的位置上
            System.out.println("clone后的是否为同一对象？studentClone==student:" + (studentClone == student));
            //输出结果：true，说明原始对象都是同一个，即初始编译后的那个.class文件
            System.out.println("clone前后对应的原始对象是否一致:" + (student.getClass() == studentClone.getClass()));
            //输出结果：false、由堆中不同的位置可知，这里比较的是对象，最终比较的即对象在内存中地址。堆中不同，自然这里就是false了
            System.out.println("clone后的对象是否满足equals条件:" + studentClone.equals(student));
            //要对equal方法重写后，才能达到这个效果。
//           System.out.println("clone后的对象是否满足equals条件(重写equals方法后):" +  student.clone().equals(student));
            System.out.println("----------------------------------------------------------");
            //①：对clone对象的基本类型属性作改变
            /**
             * 测试结果：
             * 并不会对原对象的基本类型属性产生影响
             */
            studentClone.setName("克隆-小王");
            studentClone.setAge("克隆-18");
            System.out.println("改变clone对象基本类型属性的结果:" + studentClone.toString());
            System.out.println("原对象信息：" + student.toString());
            System.out.println("----------------------------------------------------------");
            //②：对cloned对象的引用类属性作改变
            Teacher teacherClone = studentClone.getTeacher();
            teacherClone.setName("克隆-张老师");
            teacherClone.setAge("克隆-30");
            studentClone.setTeacher(teacherClone);
            System.out.println("改变clone对象引用属性的结果:" + studentClone.toString());
            System.out.println("原对象信息：" + student.toString());
            System.out.println("----------------------------------------------------------");
            /**
             * 测试结果
             * 原对象的老师也被更改了。。。。
             * 说明：在这种克隆的对象上操作引用类型属性，实际上就是操作原有对象的相应属性。
             * 显然这不是我想要的。我只是想改个老师而已。
             * 【这就是浅拷贝造成结果：即对基本类型无影响，相当于在副本上操作。而引用对象则是对应的原来的。】
             */
            //③：先升级Clone()方法后，再对引用类型属性+基本类型作改变----》深拷贝转变
            /**
             * 测试结果：
             * 测试过程即②、
             * 作了更改：引用对象实现Cloneale接口，在主对象Student类对克隆对象的‘来源’作了声明
             */

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
