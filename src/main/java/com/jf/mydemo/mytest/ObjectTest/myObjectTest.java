package com.jf.mydemo.mytest.ObjectTest;

import com.jf.mydemo.mytest.ObjectTest.Clone.Student;
import com.jf.mydemo.mytest.ObjectTest.Clone.Teacher;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-05 10:30
 * @Description: 创建对象的测试类
 * To change this template use File | Settings | File and Templates.
 * ？在Java OO 思想下创建对象的方法有哪几种？
 * 1.new 构造器
 * ---平时都在用，不用说了嘛
 * 2.反序列化
 * 3.利用反射
 * ①：摘自 https://blog.csdn.net/u011784767/article/details/78165908
 * 反射Class.forName(classFullPathName).newInstance()创建对象，一定要调用默认的无参构造函数
 * 通过反射Player.class.getConstructor(int.class,String.class).newInstance()创建对象，一定要调用相应的构造函数
 * 4.直接Clone
 * 参考：
 * 1）clone方法是如何工作的 - ImportNew
 * http://www.importnew.com/6960.html
 * 2）Java中创建对象的5种不同方法 - ImportNew
 * http://www.importnew.com/22405.html
 * 意味着clone()返回的对象可能会违反这些约定（通过调用super.clone()方法返回的对象），当重写clone()方法时，你可以遵循前面两条（a.clone()!=a和a.clone().getClass()==a.getClass()）。
为了遵循第三个特性（clone.equals(a)），你必须重写equals方法。
 */

public class myObjectTest {


    /**
     * Clone方法创建对象单元测试
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
