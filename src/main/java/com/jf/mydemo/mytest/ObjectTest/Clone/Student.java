package com.jf.mydemo.mytest.ObjectTest.Clone;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-05 13:00
 * @Description: 用于测试clone的实体类
 * To change this template use File | Settings | File and Templates.
 */

public class Student implements Cloneable{

    private String name;
    private String age;

    private Teacher teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student student =  (Student) super.clone();
        //只有在加上这个代码段的声明后，才能保证原对象不受影响---深拷贝！
        student.setTeacher((Teacher) student.getTeacher().clone());
        return student;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", teacher=" + teacher +
                '}';
    }
}
