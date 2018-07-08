package com.jf.mydemo.mytest.Thread.SomeExampleInLife.SaleTickets;

/**
 * Created by wjie on 2018/5/20.
 */
public class Ticket2 implements Runnable {
    private static int tickets = 100;
    /*①：同步代码块*/
    private static Object obj = new Object();
    boolean flag = true;

    public void run() {
        if (flag) {
            System.out.println("if中的flag的值----->>"+Thread.currentThread().getName());
            while (true) {
            /*①：同步代码块*/
                synchronized (Ticket2.class) {
                    if (tickets > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println(Thread.currentThread().getName() + "--->>" + tickets--);
                    }
                }
            }
        } else {
            System.out.println("else中的flag值----->>"+flag);
            while (true) {
                 /*②：同步函数*/
                sale();
            }
        }


    }
    /*②：同步函数？---》》同步函数所用的锁是哪一个？*/
    public static synchronized void sale() {
        if (tickets > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + "--->>" + tickets--);
        }
    }
}
