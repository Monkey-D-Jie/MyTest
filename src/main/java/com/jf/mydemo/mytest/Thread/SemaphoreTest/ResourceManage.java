package com.jf.mydemo.mytest.Thread.SemaphoreTest;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-20 14:29
 * @Description: 厕所坑位管理类
 * To change this template use File | Settings | File and Templates.
 */

public class ResourceManage {
    /**
     * 标识厕所的信号量，不为0时说明可以占到坑位；为0时需要等待
     */
    private final Semaphore semaphore;
    /**
     * 存放厕所使用状态
     */
    private boolean[] resourceArray;
    /**
     * 保证一波儿人进入到厕所中后，只能是1人一坑，不能抢
     */
    private ReentrantLock lock;
    /**
     * 模拟的坑位数 10个
     */
    private final int RESOURCE_COUNT = 10;

    public ResourceManage(){
        /**
         * 控制10个共享资源的使用，采用公平原则；
         * 公平原则下，先来的优先获得信号量，即获得厕所的使用资格
         */
        this.semaphore = new Semaphore(RESOURCE_COUNT,true);
        /**
         *公平模式下，先来的可以先选
         */
        this.lock = new ReentrantLock(true);
        /**
         * 初始化厕所状态 为 全部可用
         */
        this.resourceArray = new boolean[RESOURCE_COUNT];
        for (int i = 0; i < RESOURCE_COUNT; i++) {
            resourceArray[i] = true;
        }
    }

    /**
     * @Author: Wangjie
     * @Date:   2018-06-20 15:05:10
     * @Description: 优先来访的人员具有厕所坑位的选择权
     * @Return  int 厕所坑位编号
     */
    private int getResourceId(){
        int id = -1;
        /**
         * 保证来访的人可以优先选择，且要待其选择完后，才能轮到进入厕所中的其他人选
         * --->就好比是拿着10个坑位中任意一个坑位的锁一样，这某一时刻，他可以任意选择
         * 其中一个，不受其他人影响
         */
        lock.lock();
        try{
            for (int i = 0; i <RESOURCE_COUNT; i++) {
                if(resourceArray[i]){
                    /**
                     * 一旦选中后，则说明对应厕所坑位的状态是不可用的了
                     */
                    resourceArray[i] = false;
                    /**
                     * 返回对应的坑位编号
                     */
                    id = i;
                    break;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            /**
             * 把开锁资格方放出去，交给其他人
             */
            lock.unlock();
        }
        return id;
    }

    public void useResource(int userId){
        try {

            /**
             * 获取了可以使用厕所坑位的资格
             */
            semaphore.acquire();
            /**
             * 选中了某个坑位
             */
            int id = getResourceId();
            System.out.println("userId:"+userId+"拿到资格，正在使用"+id+"号坑位\n");
            /**
             * 模拟在上厕所的过程--->虽然这速度有点神奇
             */
            Thread.sleep(100);
            /**
             * 用完之后把对应的厕所坑位空出来--->厕所坑位被重置为可用状态
             */
            resourceArray[id] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            /**
             * 把使用资格释放出去，别人也要用嘚嘛。
             * 要保持有10个坑位可用的状态(物尽其用嘛)
             */
            semaphore.release();
        }
    }
}
