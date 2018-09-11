package com.jf.mydemo.mytest.FileTest;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import sun.misc.Cleaner;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void nameLength(){
        File[] roots = File.listRoots();
        List<String> list = new ArrayList<>(roots.length);
        for (int i = 0; i < roots.length; i++) {
            System.out.println(roots[i].toString()+":"+roots[i].getFreeSpace());
            list.add(roots[i].toString());
        }
    }
    @Test
    public void fileTypeTest(){
        String filePath ="E:\\Users\\uploadTestFiles\\gif";
        File file = new File(filePath);
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println("*******************"+(i+1)+"**********************");
                System.out.println("方法判断结果"+CloudFileUtil.isSurportedAndLegalFile(files[i].getName(),files[i]));
                if(!CloudFileUtil.isSurportedAndLegalFile(files[i].getName(),files[i])){
                    System.out.println("------------------------warning----warning----warning----->>>文件"+files[i].getName()+"需要再检查下验证方式");
                }
                System.out.println("-------------------"+(i+1)+"----------------------");
            }
        }
    }
    @Test
    public void creatFile() throws Exception {
        File file = new File("E:\\Users\\myVideo\\录播资源-新的开始-myVideo-20多M.mp4");
        toByteArray3(file);
        System.out.println("文件"+file.getName()+"的大小为:"+file.length());
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(File file) {
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bos = new ByteArrayOutputStream((int) file.length());
            byte[] b = new byte[1024];
            int n;
            while ((n = bis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            buffer = bos.toByteArray();
            System.out.println("getBytes************"+buffer.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }finally {
            try {
                IOUtils.closeQuietly(bos);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new ServiceException(e);
            }
        }
        return buffer;
    }

    /**
     * the traditional io way
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(File f) throws IOException{
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
        BufferedInputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while(-1 != (len = in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            System.out.println("toByteArray**************"+bos.toByteArray().length);
            return bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * NIO way
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(File file)throws IOException{
        FileChannel channel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(file);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            while((channel.read(byteBuffer)) > 0){
                // do nothing
//              System.out.println("reading");
            }
            System.out.println( byteBuffer.array().length);
            return byteBuffer.array();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                channel.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            try{
                fs.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mapped File  way
     * MappedByteBuffer 可以在处理大文件时，提升性能
     * @param file
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray3(File file) throws Exception {

        FileChannel fc = null;
        MappedByteBuffer byteBuffer = null;
        try {
            fc = new RandomAccessFile(file, "r").getChannel();
            byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            System.out.println("***********newByte：" + result.length);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            clean(byteBuffer);
            IOUtils.closeQuietly(fc);
        }
    }

    public static void clean(final Object buffer) throws Exception {
        AccessController.doPrivileged((PrivilegedAction) () -> {
            try {
                Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
                getCleanerMethod.setAccessible(true);
                Cleaner cleaner = (Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
                cleaner.clean();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }
}
