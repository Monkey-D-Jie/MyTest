package com.jf.mydemo.mytest.CommonDemoTest;

import com.jf.mydemo.mytest.FileTest.ServiceException;
import org.junit.Test;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-11 10:01
 * @Description: 一些比较常见的demo的测试类
 * To change this template use File | Settings | File and Templates.
 */

public class myCommonDemoTest {
    @Test
    public void stringTest(){
        String sqlScriptDirCopy = "D://jf_icms_sql//"+"copySql//";
        File copyDir = new File(sqlScriptDirCopy);
        if(!copyDir.isDirectory()){
            copyDir.mkdir();
        }
    }
    @Test
    public void getJsonData(){
        String test = " \\#\\{orgCode\\}";
        String test2 = "#{orgCode}";
        System.out.println(test2.replace("\\",""));
    }

    public static void throwNumberExc(String str,String exceptionInfo) {
//        isNotBlank(str,exceptionInfo);
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(str);
        if (!m.find()) {
            throw new ServiceException(exceptionInfo);
        }
    }
}
