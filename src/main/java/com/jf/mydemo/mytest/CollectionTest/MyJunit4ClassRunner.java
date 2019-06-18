package com.jf.mydemo.mytest.CollectionTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-06-18 15:28
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import org.junit.runners.model.InitializationError;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 将logback集成到junit中
 *
 * @author LIUTAO
 * @version 2017/5/11
 * @see
 * @since
 */
public class MyJunit4ClassRunner extends SpringJUnit4ClassRunner {
    static{
        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure("src/main/resources/logback.xml");
        } catch (JoranException e) {
            e.printStackTrace();
        }
    }
    public MyJunit4ClassRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }
}