package com.jf.mydemo.mytest.Thread.ThreadLocalTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-06 15:07
 * @Description: ThreadLocal�Ĵ���demo����
 * ���ԣ�
 * To change this template use File | Settings | File and Templates.
 */

public class MyThreadLocalTest {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();
    ThreadLocal<String> stringThreaLocal = new ThreadLocal<String>();
    private void init(){
        //    Thread.currentThread().getId()
        this.longThreadLocal.set((long)2);
        //Thread.currentThread().getName()
        this.stringThreaLocal.set("test");
    }

    private Long getLong(){
        return this.longThreadLocal.get();
    }

    private String getString(){
        return stringThreaLocal.get();
    }

    public static void main(String[] args) {
        final MyThreadLocalTest test = new MyThreadLocalTest();
        //���߳���
        test.init();
        System.out.println("*****���߳��д�ӡ*****");
        System.out.println(test.getLong());
        System.out.println(test.getString());

        //�������߳���������
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * ֻҪ�����߳��е�����set���������Ӧ��init���������õ�ֵ���ǵ�ǰ�߳�thread��
                 * ������set������get����Ҳ����ˡ�
                 * �������get����Ҳ�����ڵ�ǰ�̶߳������������߳�û�й�ϵ��
                 * ע�ͺ�Ĵ�ӡ���----
                 *
                 *****���߳��д�ӡ*****
                 1
                 main
                 *****���߳��д�ӡ-�ڶ���*****
                 1
                 main
                 *****thread1�߳��д�ӡ*****
                 null
                 null

                 */
                test.init();
                /**
                 * δע��ʱ�Ĵ�ӡ���
                 ******���߳��д�ӡ*****
                 1
                 main
                 *****���߳��д�ӡ-�ڶ���*****
                 1
                 main
                 *****thread1�߳��д�ӡ*****
                 11
                 Thread-0
                 */
                System.out.println("*****thread1�߳��д�ӡ*****");
                System.out.println(test.getLong());
                System.out.println(test.getString());
            }
        });
        thread.start();
        System.out.println("*****���߳��д�ӡ-�ڶ���*****");
        System.out.println(test.getLong());
        System.out.println(test.getString());
    }
}
