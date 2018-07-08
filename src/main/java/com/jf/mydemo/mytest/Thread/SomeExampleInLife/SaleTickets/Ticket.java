package com.jf.mydemo.mytest.Thread.SomeExampleInLife.SaleTickets;

/**
 * Created by wjie on 2018/5/20.
 */
public class Ticket extends Thread {

    private int tickets = 100;

    public Ticket(int tickets){
        this.tickets = tickets;
        System.out.println("tickets:"+tickets);
    }

    public void run(){
       /* while (tickets > 0){
                System.out.println(Thread.currentThread().getName()+"----->>>"+tickets--);
        }*/
        while (true){
            if(tickets > 0){
                System.out.println(Thread.currentThread().getName()+"----->>>"+tickets--);
            }
        }
    }
}
