package com.jf.mydemo.mytest.ConsumeTest;

import com.google.common.net.HttpHeaders;
import com.jf.mydemo.mytest.Unsafe.DateCalculateKit;
import com.jf.mydemo.mytest.Unsafe.DateFormatKit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaMetadataKeys;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/3/16 0016
 * @time: 17:16
 * To change this template use File | Settings | File and Templates.
 */

public class MyCostumeTest {

    private static final int WEEK = 7;
    private static final long MILLISECONDS_PER_DAY = 24L * 3600 * 1000;

    /**
     * @author: wjie
     * @date: 2018/3/16 0016 17:39
     * <p>
     * >> 右移,高位补符号位” 这里右移一位表示除2
     * “>>> 无符号右移,高位补0”； 与>>类似
     * “<< 左移” 左移一位表示乘2，二位就表示4，就是2的n次方
     * <p>
     * 设a=8，则表达式a＞＞＞2的值是( )。
     * A) 1
     * B) 2
     * C) 3
     * D) 4
     * 正确答案
     * B
     * 答案解析
     * [解析] 本题具体考查对位运算符中无符号右移运算符的掌握。
     * 无符号右移运算符“＞＞＞”用于将一个数的各二进制位全部无符号右移若干位，与运算符“＞＞”不同的是左补0。
     * 在本题中，8的二进制表示是1000，右移两位后变成了0010，对应的十进制数是2。
     */
    @Test
    public void costumeTest() {
        int n = 2;
        System.out.println("(n << 1)的值:" + (n << 1));
        System.out.println("(n >>> 16)的值:" + (n >>> 16));
        int result = (n << 1) - (n >>> 1);
        System.out.println("(n << 1) - (n >>> 1)的值为:" + result);
        String OS_NAME = System.getProperty("os.name").toLowerCase();
        System.out.println(OS_NAME);
    }

    public class MyTask extends TimerTask {
        int count = 0;

        @Override
        public void run() {
            count++;
            System.out.println("------count:" + count);
            if (count == 10) {
                super.cancel();
            }
        }
    }

    @Test
    public void randomTest() throws IOException {
        File file = new File("E:\\Users\\myVideo\\录播资源-新的开始-myVideo.mp4");
        InputStream inputStream = new FileInputStream(file);
        System.out.println(getFileHeader(inputStream).substring(0,6));
        File file1 = new File("E:\\Users\\openOffice\\2Pdf\\test-txt.txt");
        InputStream inputStream6 = new FileInputStream(file1);
        System.out.println(getFileHeader(inputStream6));
        File file2 = new File("E:\\Users\\openOffice\\2Pdf\\test-word.docx");
        File file3 = new File("E:\\Users\\openOffice\\2Pdf\\test-xls2.xls");
        InputStream inputStream1 = new FileInputStream(file1);
        long length = FileUtils.sizeOf(file);
        System.out.println("xlsx--"+isEmptyFile(file));
        System.out.println("xls--"+isEmptyFile(file3));
        long length1 = FileUtils.sizeOf(file1);
        System.out.println("txt--"+isEmptyFile(file1));
        long length2 = FileUtils.sizeOf(file2);
        System.out.println("word--"+isEmptyFile(file2));
        System.out.println(getFileNameSuffix("test-xls2.xls"));
        System.out.println(getFileNameSuffix("test-word.docx"));

        File file4 = new File("E:\\Users\\openOffice\\2Pdf\\test-word-dest.docx");
        copyFileUsingApacheCommonsIO(file2,file4);

    }

    public static void copyFileUsingStream(File source, File dest) {
        InputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(source);
            fos = new FileOutputStream(dest);
            //生成本地的临时文件
            byte[] fileBuffer = new byte[10240];
            int length;
            while ((length = fis.read(fileBuffer, 0, fileBuffer.length)) != -1) {
                fos.write(fileBuffer, 0, length);
            }
        } catch (Exception e) {
            try {
                fis.close();
                fos.close();
            } catch (Exception e1) {
                e.printStackTrace();
            }

        }
    }

    public static void copyFileUsingApacheCommonsIO(File source, File dest)
            throws IOException {
        FileUtils.copyFile(source, dest);
    }

    public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            inputChannel = new FileInputStream(source).getChannel();
            outputChannel = new FileOutputStream(dest).getChannel();
            outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
        } finally {
            if (inputChannel != null && outputChannel != null) {
                inputChannel.close();
                outputChannel.close();
            }
        }
    }

    public static String getFileNameSuffix(String fileName){
        String suffix = null;
        if(!StringUtils.isEmpty(fileName)){
            suffix  = fileName.substring(fileName.indexOf(".")+1,fileName.length());
        }
        return suffix;
    }
    public static boolean isEmptyFile(File file) throws IOException {
        boolean flag = true;
        if(file != null){
            long length = FileUtils.sizeOf(file);
            if(length != 0){
                flag = false;
                //针对xls这种还要再判断下内容
                    boolean isExcel = StringUtils.containsAny(file.getName(),"xls","xlsx");
                    if(isExcel){
                        FileInputStream inputStream = FileUtils.openInputStream(file);
                        XSSFWorkbook wb= new XSSFWorkbook(inputStream);
                        XSSFSheet sheet=wb.getSheetAt(0);
                        Iterator ite = sheet.rowIterator();
                        if (ite.hasNext()){
                            flag = false;
                        }else{
                            flag = true;
                        }
                }
            }
        }
        return flag;
    }

    /**
     * 参考来自
     * Java-POI读取Excel简单案例 - CSDN博客
     https://blog.csdn.net/orangleliu/article/details/38309349
     * @param inputStream
     * @throws IOException
     */
    public static void readExcelMethod(FileInputStream inputStream) throws IOException {
        XSSFWorkbook wb= new XSSFWorkbook(inputStream);
        XSSFSheet sheet=wb.getSheetAt(0);
        Iterator ite = sheet.rowIterator();
        if (ite.hasNext()){
            System.out.println("excel 不空");
        }else{
            System.out.println("excel 空");
        }
        for(Iterator ite1 = sheet.rowIterator(); ite1.hasNext();){
            HSSFRow row=(HSSFRow)ite.next();
            System.out.println();
            for(Iterator itet=row.cellIterator();itet.hasNext();){
                HSSFCell cell=(HSSFCell)itet.next();
                switch(cell.getCellType()){
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        //得到Boolean对象的方法
                        System.out.print(cell.getBooleanCellValue()+" ");
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        //先看是否是日期格式
                        if(HSSFDateUtil.isCellDateFormatted(cell)){
                            //读取日期格式
                            System.out.print(cell.getDateCellValue()+" ");
                        }else{
                            //读取数字
                            System.out.print(cell.getNumericCellValue()+" ");
                        }
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        //读取公式
                        System.out.print(cell.getCellFormula()+" ");
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        //读取String
                        System.out.print(cell.getRichStringCellValue().toString()+" ");
                        break;
                }
            }
        }
    }

    public static String getFileHeader(InputStream inputStream) {
        String value = null;
        try {
            byte[] b = new byte[10];
        /*int read() 从此输入流中读取一个数据字节。
        *int read(byte[] b) 从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。
        * int read(byte[] b, int off, int len) 从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
        */
            inputStream.read(b, 0, b.length);
            value = bytesToHexString(b);
        } catch (Exception e) {
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
        return value;
    }
    private static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF);
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }


    public static String getWeekDate(Date sbeginDate, int weekday, int weeks, String dateFormat) {
        String date = "";
        if (sbeginDate != null && weekday != 0 && weeks != 0) {
            Map<String, Object> weekMap = new HashMap<String, Object>();
            Calendar cal = Calendar.getInstance();
            cal.setTime(sbeginDate);
            /*判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了*/
            int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
            if (1 == dayWeek) {
                cal.add(Calendar.DAY_OF_MONTH, -1);
            }
            /*设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一*/
            cal.setFirstDayOfWeek(Calendar.MONDAY);
            /*获得当前日期是一个星期的第几天*/
            int day = cal.get(Calendar.DAY_OF_WEEK);
            /*根据日历的规则，给当前日期减去星期几与一个星期第一天的差值*/
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
            /*获取到传入日期所在周的周一*/
            long time = cal.getTime().getTime()+(weeks-1)*WEEK*MILLISECONDS_PER_DAY;
            Date mondayDate = new Date(time);
            for (int i = 1; i <= WEEK; i++) {
                String weekDay = DateFormatKit.convert(dateFormat, DateCalculateKit.addDate(mondayDate,i));
                weekMap.put("week"+i, weekDay);
            }
            date = String.valueOf(weekMap.get("week" + weekday));
        }
        return date;
    }

    @Test
    public void dateTest(){
//        System.out.println(getWeekDate(new Date("2018"),1,18,DateFormatKit.DATE_FORMAT_THREE));
        String isSurport = "mp43";
        Integer resFileTypeId = Integer.parseInt(isSurport.substring(isSurport.length()-1));
        System.out.println(resFileTypeId);
    }

    @Test
    public void listTest(){
        List<Resource> list1 = new ArrayList<>();
        List<Resource> list2 = new ArrayList<>();
        Resource resource3 = new Resource();
        resource3.setResourceId("123");
        resource3.setResName("test6666");
        Resource resource = new Resource();
        resource.setResourceId("123");
        resource.setResName("test");
        Resource resource2 = new Resource();
        resource2.setResourceId("456");
        resource2.setResName("test2");
        list1.add(resource);
        list1.add(resource2);
        System.out.println("list1 删前"+list1.toString());
        list2.add(resource3);
        System.out.println("list2"+list2.toString());
        list1.removeAll(list2);
        System.out.println("list1 删后"+list1.toString());
    }

    @Test
    public void checkTypeOfFile() throws IOException, TikaException, SAXException {
        File file = new File("E:\\Users\\上传的文件\\我的部分-视频上传文件.mp4");
        File file2 = new File("E:\\Users\\openOffice\\2Pdf\\test-word.docx");
        AutoDetectParser parser = new AutoDetectParser();
        parser.setParsers(new HashMap<>());

        Metadata metadata = new Metadata();
        metadata.add(TikaMetadataKeys.RESOURCE_NAME_KEY, file.getName());

        InputStream stream = new FileInputStream(file);
        parser.parse(stream, new DefaultHandler(), metadata, new ParseContext());
        stream.close();
        String mimeType = metadata.get(HttpHeaders.CONTENT_TYPE);
        System.out.println(mimeType);
    }
    @Test
    public void sizeTest(){
        System.out.println();
    }
}

