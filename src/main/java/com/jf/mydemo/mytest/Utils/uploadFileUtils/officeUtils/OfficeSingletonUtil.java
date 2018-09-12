package com.jf.mydemo.mytest.Utils.uploadFileUtils.officeUtils;

import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-05-08 11:02
 * @Description: 用双同步锁单例的方式创建Manager对象
 * To change this template use File | Settings | File and Templates.
 */

public class OfficeSingletonUtil {
    /**
     * 打印日志用logger-用debug级别
     *
     */

    private static volatile OfficeManager singletonLibre = null;

    public static OfficeManager getLibreOfficeSingleton(String url, int[] ports) {
        if (singletonLibre == null) {
            synchronized (OfficeSingletonUtil.class) {
                if (singletonLibre == null) {
                    DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
                    // libreOffice的安装目录
                    configuration.setOfficeHome(url);
                    // 端口号--默认也是8100端口
                    configuration.setPortNumbers(ports);
                    //在多个端口上开启libreOffice的进程，并设置其任务最大执行时间和等待最长时间为30s
                    configuration.setRetryTimeout(30000);
                    //设置任务执行超时为10分钟
                    configuration.setTaskExecutionTimeout(1000 * 60 * 10L);
                    //设置任务队列超时为1小时
                    configuration.setTaskQueueTimeout(1000 * 60 * 60 * 1L);
                    singletonLibre = configuration.buildOfficeManager();
                }
            }
        }
        return singletonLibre;
    }
}
