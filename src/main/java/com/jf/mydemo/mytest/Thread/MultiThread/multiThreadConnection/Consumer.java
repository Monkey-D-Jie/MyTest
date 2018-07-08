package com.jf.mydemo.mytest.Thread.MultiThread.multiThreadConnection;

/**
 * @Author wangjie
 * @Date 2018-06-03 12:40
 * @Description 消费者
 */
public class Consumer implements Runnable {
    private Resource resource;
    private Resource_Lock resource_lock;

    public Consumer(Resource resource) {
        this.resource = resource;
    }

    public Consumer(Resource_Lock resource) {
        this.resource_lock = resource;
    }
    public void run() {
        //循环调用，以显示多线程的效果
        while (true) {
//            resource.out();
            resource_lock.out();
        }
    }
}
