package com.jf.mydemo.mytest.Thread.SemaphoreTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-20 14:43
 * @Description: 使用资源-上厕所的类
 * To change this template use File | Settings | File and Templates.
 */

public class ResourceUser implements Runnable{

    private ResourceManage resourceManage;
    private int userId;
    /**
     * @Author: Wangjie
     * @Date:   2018-06-20 15:13:29
     * @Description: 用户使用厕所的类说明
     * @param  resourceManage 要用的厕所-商场里的同一个
     * @param  userId 进入到厕所的用户id
     */
    public ResourceUser(ResourceManage resourceManage,int userId){
        this.resourceManage = resourceManage;
        this.userId = userId;
    }

    @Override
    public void run() {
        System.out.println("userId："+userId+"--->>准备使用厕所坑位\n");
        this.resourceManage.useResource(userId);
        System.out.println("userId："+userId+"<<---用完了厕所坑位\n");
    }

    public static void main(String[] args) {
        ResourceManage resourceManage = new ResourceManage();
        /**
         * 模拟超过实际坑位数的使用人数，
         * 这样才能有‘排队’的效果
         */
        for (int i = 0; i < 30 ; i++) {
            Thread thread = new Thread(new ResourceUser(resourceManage,i));
            thread.start();
        }
    }
}
