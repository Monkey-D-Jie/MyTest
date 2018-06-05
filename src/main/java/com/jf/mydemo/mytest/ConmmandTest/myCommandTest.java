package com.jf.mydemo.mytest.ConmmandTest;

import org.junit.Test;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/16 0016
 * @time: 09:44
 * To change this template use File | Settings | File and Templates.
 * <p>
 * 测试:在java代码中执行cmd的命令
 */

public class myCommandTest {
    @Test
    public void comTest() {
        String command = "javac";
        String s = "IPv4";
        String line = null;
        StringBuilder sb = new StringBuilder();
        Runtime runtime = Runtime.getRuntime();
        try {
            Process process = runtime.exec(command);
            BufferedReader bufferedReader = new BufferedReader
                    (new InputStreamReader(process.getInputStream()));


            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line + "\n");
                //if (line.contains(s)) {
                System.out.println(line);
                //}
            }
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
        System.out.println(sb.toString());
    }

    @Test
    public void conmondTest2() {
        /*获取cmd命令*/
        try {
            /* //添加要进行的命令，"cmd  /c
            calc"中calc代表要执行打开计算器，如何设置关机请自己查找cmd命令*/
            //能打开
            String command = "cmd /c calc";
            String cmdStr = "cmd /c D:\\Software\\ElasticSearch-LogStash\\logstash-5.5.0\\bin logstash.bat -f mysqltoes.conf" ;
            //其他命令：没多大反应嘚
            String command2 = "java -version";
            Process pro = Runtime.getRuntime().exec(cmdStr);
            //虽然cmd命令可以直接输出，但是通过IO流技术可以保证对数据进行一个缓冲。
            BufferedReader br = new BufferedReader(new InputStreamReader(pro .getInputStream()));
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (IOException exception) {
        }

/*cmd /c dir 是执行完dir命令后关闭命令窗口
cmd /k dir 是执行完dir命令后不关闭命令窗口
cmd /c start dir  会打开一个新窗口后执行dir命令，原窗口会关闭
cmd /k start dir  会打开一个新窗口后执行dir命令，原窗口不会关闭
cmd /?  查看帮助信息*/
    }

    @Test
    public void ProcessBuilderTest(){
        try
        {
            // 执行ping命令
            Process process = Runtime.getRuntime().exec("ping 127.0.0.1");
            // 记录dos命令的返回信息
            StringBuffer resStr = new StringBuffer();
            // 获取返回信息的流
            InputStream in = process.getInputStream();
            Reader reader = new InputStreamReader(in);
            BufferedReader bReader = new BufferedReader(reader);
            for (String res = ""; (res = bReader.readLine()) != null;)
            {
                resStr.append(res + "\n");
            }
            bReader.close();
            reader.close();
            System.out.println(resStr.toString());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
