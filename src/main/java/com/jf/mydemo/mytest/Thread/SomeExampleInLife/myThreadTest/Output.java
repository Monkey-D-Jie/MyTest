package com.jf.mydemo.mytest.Thread.SomeExampleInLife.myThreadTest;

/**
 * @Author wangjie
 * @Date 2018-06-04 23:14
 * @Description 输出对象
 */
public class Output implements Runnable {

    private Resource resource;

    public Output(Resource resource) {
        this.resource = resource;
    }

    public void run() {

        while (true) {
//            synchronized (resource) {
//                try {
//                    if(!resource.flag){
//                        //说明对象暂时还没有被赋值到
//                        resource.wait();
//                    }
////                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("得到的资源类型为:" + resource.getName() + "----" + resource.getSex());
//                resource.flag = false;
//                resource.notify();
//            }
            /**
             * 重构后的调用方式
             */
            resource.out();
        }
    }
}
