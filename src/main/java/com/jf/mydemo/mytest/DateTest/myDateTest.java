package com.jf.mydemo.mytest.DateTest;

import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/27 0027
 * @time: 13:25
 * To change this template use File | Settings | File and Templates.
 */

public class myDateTest {

    @Test
    public void currentMillsTest() throws ParseException {
        long mills = System.currentTimeMillis();
        System.out.println("mills value:"+mills);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        Date formateDate = sdf.parse(date.toString());
        System.out.println("转化后的时间为:"+formateDate);
    }
    @Test
    public void nameTest(){
        File path = new File("C:\\jf_exwsp\\file\\新建文件夹");
        if(path.isDirectory()){
            path.renameTo(new File("C:\\jf_exwsp\\file\\测试改名用"));
        }
    }
    @Test
    public void todayTest(){
        double rate2 = (double) 1/15463;
        System.out.println(rate2);
        float size = Float.valueOf(String .format("%.2f",(rate2*100)));
        System.out.println(size);
    }

}
