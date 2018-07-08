package com.jf.mydemo.mytest.Thread.SomeExampleInLife.myThreadTest;

/**
 * @Author wangjie
 * @Date 2018-06-04 23:10
 * @Description 输入线程
 */
public class Input implements Runnable {

    private Resource resource;

    //初始化时即创建资源对象
    public Input(Resource resource) {
        this.resource = resource;
    }

    public void run() {
        int x = 0;
        //分两种情况来循环打印男，或者女

        while (true) {

//            synchronized (resource) {
//                if (resource.flag){
                    //说明已经对resource赋值了，可以不用继续赋值
//                    try {
                        /**
                         * 这里用resource.wait();而不用wait()的原因：
                         * 1.说明wait()是从属于哪一个锁；
                         * 2.要说明线程是在哪个锁上被wait了；
                         * 3.只有这样，才能明确要唤醒的锁是哪一个！
                         * 直接用wait，这里会报错：IllegalMoniterStateException，违法
                         * 的监视器异常，即不晓得是哪一个的wait()，从而就报出了异常。
                         */
//                        resource.wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (x == 0) {
//                    resource.setName("张三");
//                    resource.setSex("男男男男");
//                } else {
//                    resource.setName("rose");
//                    resource.setSex("女女女女");
//                }
//                resource.flag = true;
//                resource.notify();
//                x = (x + 1) % 2;
//
//            }
            /**
             * 重构后的调用形式
             */
            if (x == 0) {
                resource.set("张三","男男男男");
            } else {
                resource.set("rose","女女女女");
            }
            x = (x + 1) % 2;
        }
    }

}
