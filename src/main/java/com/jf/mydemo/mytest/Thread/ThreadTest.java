package com.jf.mydemo.mytest.Thread;

import org.junit.Test;



/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/1/16 0016
 * @time: 14:28
 * To change this template use File | Settings | File and Templates.
 */

public class ThreadTest {
    /**
     * @author: wjie
     * @date: 2018/1/16 0016 14:30
     * threadִ��˳��Ĳ�����
     */
    /**
     * @author: wjie
     * @date: 2018/1/16 0016 14:52
     * �����ؼ�����ժ�ԣ�https://www.cnblogs.com/whyalwaysme/p/4495959.html
     * <p>
     * ֱ�ӵ������̵߳�run�������ͺñ��ǵ�����A�е���ͨ����method_Aһ��,
     * �Ѿ�ʧȥ���̵߳����ԡ�������ִ��·����˳��ֻ��һ������run����ִ����ɺ󣬲��ܼ���ִ��
     * �����ĵĴ��룻
     * ���ԣ�������ִ�ж��ٴΣ���ӡ���Ľ�����ǣ�
     * static show
     * thread show
     * ���ǣ�������õ�start������Ͳ�һ�����ޣ�
     * start����ʱ��������¿�����test(���߳�)֮���һ�����̣߳�
     * ִ�е����ǣ�start���̻߳�ȡ������Դ����ִ�е����ڲ���run������
     * ��������У���Ȼ�ʹ���ͬmain���߳̾����������
     * ׼ȷ��������
     * ��start�������̣߳���������ִ�е��˶��̡߳������󷽵Ĵ��룬���Բ��õ�run����ִ���꣬����ִ�е���
     * ��Ϊ������start()���������ö�Ӧ���̴߳���һ�־���(������)��״̬��
     * �����õ�ʱ��Ƭ�󣬲���ȥִ�е�run����(�ֱ���Ϊ�߳���)����Ҳ���ܽ���Ϊʲô ��ʱ��ֻ�ܴ�ӡ��һ�䡮thread show���ˣ�
     * �ɹ����ʱ��Ƭ���õ�ִ��Ȩʱ����Ȼ�������䶼����ӡ��������
     * ���Գ��ֵĽ������Ϊ��
     * thread show
     * static show��
     * ����ֻ��һ�� thread show��
     * ���ǣ�Ϊʲô��������������ֽ���أ���
     * ���Ͻ�����
     * �ܽ᣺start()���������̵߳ķ�����run()�Ǹ���ͨ���е��෽�������߳������Ĺ�ϵ����
     */
    @Test
    public void threadTest() throws InterruptedException {
        System.out.println("�̲߳��Կ�ʼ------------------");
        Thread thread = new Thread() {
            @Override
            public void run() {
                show();
            }
        };
        thread.start();
        Thread.sleep(2000);
        System.out.println("�߳�ִ�����������£��������ҵ�����");

    }

    static void show() {
        try {
            Thread.sleep(2000);
            System.out.println("I am a method in thread");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @author: wjie
     * @date: 2018/2/7 0007 16:22
     * ���ԣ�http://www.importnew.com/18126.html
     * ���Ԥ�У����������100000
     * ���Խ����
     */
    @Test
    public void volatileTest() {
        System.out.println("�����������");
    }

    /**
     * ThreadTest ----the diffrence of sleep and wait
     */
    @Test
    public void sleepAndWaiTest() throws InterruptedException {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread t1 = new Thread(threadDemo);
        t1.start();
        threadDemo.method2();
    }

    /**
     * join test
     */
    @Test
    public void joinTest(){
        Thread thread = new Thread(new JoinDemo());
        thread.start();
        for (int i = 0; i < 20; i++) {
            System.out.println("���̵߳�" + i + "��ִ�У�");
            if (i >= 2) {
                try {
                    // t1�̺߳ϲ������߳��У����߳�ִֹͣ�й��̣�ת��ִ��t1�̣߳�ֱ��t1ִ����Ϻ������
                    thread.join();
                    /**
                     * join
                     * ����˼�壬���൱�� ��ӣ�
                     * ���Խ����
                     * ���̵߳�0��ִ�У�
                     ���̵߳�1��ִ�У�
                     ���̵߳�2��ִ�У�
                     join�̵߳�0��ִ�У�------------
                     join�̵߳�1��ִ�У�------------
                     join�̵߳�2��ִ�У�------------
                     join�̵߳�3��ִ�У�------------
                     join�̵߳�4��ִ�У�------------
                     join�̵߳�5��ִ�У�------------
                     join�̵߳�6��ִ�У�------------
                     join�̵߳�7��ִ�У�------------
                     join�̵߳�8��ִ�У�------------
                     join�̵߳�9��ִ�У�------------
                     ���̵߳�3��ִ�У�
                     .....
                     һ����join�������߳��õ���cpu��ִ��Ȩ����������������ܡ�
                     �����ȵİ��Լ��߳�����ִ���ꡣ
                     Ȼ��Ż����ִ��֮ǰ�̵߳��߳����񡣡���
                     */
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ThreadDemo threadDemo = new ThreadDemo();
            Thread t1 = new Thread(threadDemo,"�����߳�");
            ThreadDemo2 threadDemo2 = new ThreadDemo2();
            Thread t2 = new Thread(threadDemo2);
            t2.start();
            t1.start();
            threadDemo.method2();
            System.out.println("���̼߳���ִ��....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
