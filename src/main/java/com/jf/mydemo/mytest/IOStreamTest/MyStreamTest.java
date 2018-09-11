package com.jf.mydemo.mytest.IOStreamTest;

import org.junit.Test;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-16 10:49
 * @Description: 用来测试IO流类的测试
 * To change this template use File | Settings | File and Templates.
 */

public class MyStreamTest {
    @Test
    public void writeStreamTest() throws IOException {
        File f = new File("E:\\TestFolder" + File.separator + "test.txt"); //默认写入的数据
        //默认写入的数据
//        String str = "Hello World";
        //如果文件不存在会自动创建,追加内容时可能会涉及到 换行，文件中的换行 \r\n
        String str = "\r\nHello World---我是追加的内容--注释了close()";
//        OutputStream out = new FileOutputStream(f);
        //如果是要追加内容的，则要声明其写入方式
        OutputStream out = new FileOutputStream(f, true);
        byte[] b = str.getBytes();
        //因为是字节流，所以要转化成字节数组进行输出
        out.write(b);
        //就算是把这一句注释了，追加的内容依然能够成功的写入到指定的文件中
//        out.close();
    }

    @Test
    public void readStreamTest() throws IOException {
        File f = new File("E:\\TestFolder" + File.separator + "test.txt");
        InputStream in = new FileInputStream(f);
        //这里为了节省内存空间，要根据实际情况作调整：通常情况下，会考虑把这个‘桶’的容量设置的大一点；
        //这里呢，只是作测试，如果依旧用 new byte[1024]的，明显就有点浪费
        byte[] b = new byte[(int) f.length()];
        //把文件中的内容读出来，放到字节数组b中
        in.read(b);
        in.close();
        System.out.println(new String(b));
    }

    @Test
    public void writeTest() throws IOException {
        File f = new File("E:\\TestFolder" + File.separator + "test1.txt");
        Writer out = new FileWriter(f, true);
//        String str = "Hello World---Writer-字符流";
        String str = "\r\nHello World---Writer-字符流-追加方法，\r\n注释掉close()，用flush()方法写入";
        out.write(str);
        /**
         * 测试结果：
         *注释掉后，程序虽然能正常运行，但对应的内容却没有写入到指定的文件中。
         * 说明，这种条件下，如果不调用Writer相应对象的close()，并不能顺利得到
         * 我们想要的结果.
         * 如果不调用close,也想把内容追加进文件中,则要调用flush()来达到这一目的.
         * 如果有调用close()方法，则必须在close()之前调用。
         * 因为flush()是把缓冲区中的数据写入到文件中，关闭Writer对象后，缓冲区也关闭了，
         * 自然也就没有flush()一说了嘛
         * ------
         * 通过查看源码发现，
         * Writer的close()方法，最终也是落地到了flush()方法上，即把文件数据内容从
         * 缓冲区刷新到指定的文件中去.
         * 源码中的表述为；Closes the stream, flushing it first.
         */
        out.close();
//        out.flush();
    }

    @Test
    public void readerTest() throws IOException {
        File f = new File("E:\\TestFolder" + File.separator + "test1.txt");
        Reader input = new FileReader(f);
        char[] c = new char[1024];
        int len = input.read(c);
        /**
         * 读的情况下，不关闭reader对象，也不会对结果造成影响。
         * 但是不关闭的话，会有内存泄漏的隐患。所以，对于字符流，字节流的输出流和输入流对象，
         * 都要养成一个关闭相应对象的习惯。
         */
        input.close();
        System.out.println(new String(c, 0, len));
    }
}
