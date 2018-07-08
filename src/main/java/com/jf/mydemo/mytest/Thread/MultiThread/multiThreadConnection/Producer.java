package com.jf.mydemo.mytest.Thread.MultiThread.multiThreadConnection;

/**
 * @Author wangjie
 * @Date 2018-06-03 12:39
 * @Description 生产者
 */
public class Producer implements Runnable {

    private Resource res;

    private Resource_Lock resource_lock;

    public Producer(Resource res) {
        this.res = res;
    }

    public Producer(Resource_Lock res) {
        this.resource_lock = res;
    }

    public void run() {
        //循环调用，以显示多线程的效果
        while (true) {
//            res.set("面包");
            resource_lock.set("面包");

        }
    }
}
