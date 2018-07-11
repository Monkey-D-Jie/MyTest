package com.jf.mydemo.mytest.FileTest;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/20 0020
 * @time: 15:57
 * To change this template use File | Settings | File and Templates.
 */

public class myFileTest {
    @Test
    public void deleteFile() throws IOException {
        System.out.println(System.getProperty("sun.desktop"));
        String osName = System.getProperty("os.name").toLowerCase().replace(" ","");
        if(osName != null && osName.contains("windows")){
            System.out.println("当前系统环境为"+osName);
        }else{
            System.out.println("当前系统不是windows噢");
        }
        System.out.println(System.getProperty("os.name").toLowerCase().replace(" ","").contains(""));
        File file = new File("E://Users//转码文件测试//2吴总//wu-大转码文件6.docx");
        File file2 = new File("E://Users//转码文件测试//2吴总//wu-大转码文件6.pdf");
        if(file2.exists()){
            System.out.println("file2文件创建成功");
        }else{
            System.out.println("用new的方式并没有创建的有文件");
        }

        System.out.println(file.getAbsolutePath());
    }
}
