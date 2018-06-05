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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student student =  (Student) super.clone();
        //只有在加上这个代码段的声明后，才能保证原对象不受影响---深拷贝！
        //不考虑这个对象的clone，equals()可以得到true的结果，加了之后就相当于新生成了一个teacher对象，所以会有问题
        //当然也能对equals方法作变动 - -！
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

   @Override
    public boolean equals(Object obj) {
        /**
         * 最原始的equals方法
         * 直接就是：
         * return (this == obj);
         *//*
//        return super.equals(obj);
        /**
         * 重写后的equals方法
         */
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        final Student student = (Student)obj;
        if(this.name != student.getName()){
            return false;
        }
        if(this.age != student.getAge()){
            return false;
        }
        if(this.teacher != student.getTeacher()){
            return false;
        }
        return true;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) {
            return false;
        }
        if (age != null ? !age.equals(student.age) : student.age != null) {
            return false;
        }
        return teacher != null ? teacher.equals(student.teacher) : student.teacher == null;
    }*/

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }
}
