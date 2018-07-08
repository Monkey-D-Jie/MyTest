package com.jf.mydemo.mytest.Thread.SomeExampleInLife.SaleTickets;

/**
 * Created by wjie on 2018/5/20.
 */
public class ThreadDemo01 {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Ticket2 ticket2 = new Ticket2();
        Thread t1 = new Thread(ticket2,"t1");
        Thread t2 = new Thread(ticket2,"t2");
        Thread t3 = new Thread(ticket2);
        Thread t4 = new Thread(ticket2);
        /*t1开启线程*/
        t1.start();
//        System.out.println("t1--->"+t1.getName());
        Thread.sleep(100);
        ticket2.flag = false;
        /*t2直接调用run，相当于调用普通方法*/
        t2.start();
        t3.yield();
        //        System.out.println("t2--->"+t2.getName());
//        t3.start();
//        t4.start();
    }
}
