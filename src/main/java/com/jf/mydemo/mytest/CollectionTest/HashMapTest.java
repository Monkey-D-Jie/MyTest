package com.jf.mydemo.mytest.CollectionTest;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import com.jf.mydemo.mytest.Utils.string.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/2/28 0028
 * @time: 16:48
 * To change this template use File | Settings | File and Templates.
 */
//@RunWith(MyJunit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:logback.xml"})
public class HashMapTest {
    /**
     * 打印日志用logger-用debug级别
     *
     */
    private Logger LOGGER = LoggerFactory.getLogger(HashMapTest.class);
//    private Logger LOGGER = null;

//    private static final Logger logger = LoggerFactory.getLogger(HashMapTest.class);


    @Before
    public void initLog(){
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure("src/main/resources/logback.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
        System.out.println("===================");
        LOGGER.debug("Hello {}","debug message");

       /* ---------------------
                作者：你好丶明天
        来源：CSDN
        原文：https://blog.csdn.net/qq592304796/article/details/52325230
        版权声明：本文为博主原创文章，转载请附上博文链接！*/
    }

    @Test
    public void hashMapTest(){
        Map<String,Object> map1 = new HashMap<String,Object>(8);
        //map1.put("test",123);
        //map1.put("test2",null);
        map1.put(null,"666");
        map1.put(null,"777");
        map1.put("test3",456);
        System.out.println(map1.get("test"));
        map1.remove("test3");
    }
    @Test
    public void toArrayTest(){
        LOGGER.info("测试List.toArray()的测试用例开始{}","*****start*****");
        List<Integer> list2 = new ArrayList<>();
        for(int i=0; i < 5; i++) {
            list2.add(i);
        }
        Integer[] arr1 = new Integer[list2.size()];
        //要在 toArray()中声明转换的数组类型，因为list.toArray()得到的是一个 Object[]，不能对其作强转操作
        arr1 = list2.toArray(arr1);
        LOGGER.info("由list转化来的数组为：{}", JsonUtil.convertObjectJson(arr1));
        System.out.println("arr1："+JsonUtil.convertObjectJson(arr1));
        Integer[] arr2 = new Integer[2];
        arr2 = list2.toArray(arr2);
        LOGGER.info("由list转化来的容量小一半数组为：{}", JsonUtil.convertObjectJson(arr2));
        System.out.println("arr2："+JsonUtil.convertObjectJson(arr2));
        LOGGER.info("测试List.toArray()的测试用例开始{}","*****end*****");
    }
}
