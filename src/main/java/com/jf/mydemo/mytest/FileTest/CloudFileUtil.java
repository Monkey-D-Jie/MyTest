package com.jf.mydemo.mytest.FileTest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-25 16:34
 * @Description: 自定义的文件处理工具
 * To change this template use File | Settings | File and Templates.
 */

public class CloudFileUtil {

    /**
     * 打印日志用logger-用debug级别
     *
     */
    private static Logger LOGGER = LoggerFactory.getLogger(CloudFileUtil.class.getName());

    /**
     * 文档类型文件信息
     */
    public static final HashMap<String, String> DUCUMENT_TYPE_MAP = new HashMap<>(10);
    /**
     * 视频类型文件文件信息
     */
    public static final HashMap<String, String> VIDEO_TYPE_MAP = new HashMap<>(10);

    /**
     * 不支持文件类型集合
     */
    public static final HashMap<String, String> UNSURPORT_FILE_TYPE = new HashMap<>(20);
    /**
     * 可在线浏览的文件类型
     */
    public static final HashMap<String, String> SURPORT_ONLINE_PREVIEW_FILE_TYPE = new HashMap<>(30);
    /**
     * 可在线浏览的文件头类型
     */
    public static final HashMap<String, String> SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER = new HashMap<>(30);
    /**
     * 魔数值 magic number 头部值
     */
    public static final HashMap<String, String> FILE_MAGIC_NUMBER_HEADER = new HashMap<>(20);
    /**
     * 文件名长度限制
     */
    public static final Integer FILE_NAME_MAX_LENGTH = 128;

    private  static final  long FILE_LIMIT =  10*1024*1000;

    /**
     * 文件后缀名不能为
     * cmd、exe、bat、vbs、cmd、log 、reg、lng 、ini、ico、inf、js、sh、class、sql
     */
    static {
        UNSURPORT_FILE_TYPE.put("cmd", "cmd");
        UNSURPORT_FILE_TYPE.put("exe", "exe");
        UNSURPORT_FILE_TYPE.put("bat", "bat");
        UNSURPORT_FILE_TYPE.put("vbs", "vbs");
        UNSURPORT_FILE_TYPE.put("cmd", "cmd");
        UNSURPORT_FILE_TYPE.put("log", "log");
        UNSURPORT_FILE_TYPE.put("reg", "reg");
        UNSURPORT_FILE_TYPE.put("lng", "lng");
        UNSURPORT_FILE_TYPE.put("ini", "ini");
        UNSURPORT_FILE_TYPE.put("ico", "ico");
        UNSURPORT_FILE_TYPE.put("inf", "inf");
        UNSURPORT_FILE_TYPE.put("js", "js");
        UNSURPORT_FILE_TYPE.put("sh", "sh");
        UNSURPORT_FILE_TYPE.put("class", "class");
        UNSURPORT_FILE_TYPE.put("sql", "sql");
    }

    /**
     * 能在线浏览的文件格式有,需要判断真实类型
     * a)	可在线浏览的资源格式有：
     i.	图片：bmp、jpeg、jpg、png、gif
     ii.	视频：avi、mov、mpeg、mp4、rmvb、rm、dat、ts、wmv、flv、
     iii.	动画（swf）：swf
     iv.	音频：mp3、wma(wav吧？？)
     v.	文本：txt、pdf、doc、docx、xls、xlsx、ppt、pptx
     */
    static {
        //图片
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("bmp", "bmp1");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("jpeg", "jpeg1");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("jpg", "jpg1");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("png", "png1");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("gif", "gif1");
        //视频
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("avi", "avi3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("mov", "mov3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("mpeg", "mpeg3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("mp4", "mp43");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("rmvb", "rmvb3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("rm", "rm3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("dat", "dat3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("ts", "ts3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("wmv", "wmv3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("flv", "flv3");
        //动画
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("swf", "swf3");
        //音频
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("mp3", "mp34");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("wav", "wav4");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("wma", "wma4");
        //文本
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("txt", "txt2");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("pdf", "pdf2");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("doc", "doc2");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("docx", "docx2");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("xls", "xls2");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("xlsx", "xlsx2");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("ppt", "ppt2");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE.put("pptx", "pptx2");
    }

    /**
     * 参看来自：
     * 根据文件头判断文件类型 - CSDN博客
     https://blog.csdn.net/yudajun/article/details/50263499
     */
    static {
        /**
         * 图片
         */
        //16色位图(bmp)
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("424d228c010000000000", "bmp");
        //24位位图(bmp)
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("424d8240090000000000", "bmp");
        //256色位图(bmp)
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("424d8e1b030000000000", "bmp");
        //JPEG (jpg)
        //TODO 还要再更正下
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("ffd8ffe000104a464946", "jpg");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("ffd8ffe000104a464946", "jpeg");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("49492a00a0e00200ffd8", "jpeg");
        //PNG (png)
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("89504e470d0a1a0a0000", "png");
        //GIF (gif)
        //TODO 需要做点更正，文件头判断这块儿
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("47494638396126026f01", "gif");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("474946383961b400e000", "gif");


        /**
         * 视频
         */
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("52494646d07d60074156", "avi");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("000001ba210001000180", "mpeg");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("000001ba210001000180", "mpg");
        //MP4直接以现在00000020667479706d70文件头来判断，有的判断是错误的。所以先变成前6位来判断
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("000000", "mp4");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("00000020667479706d70", "mp4");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("2e524d46000000120001", "rmvb");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("2e524d46000000120001", "rm");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("3026b2758e66cf11a6d9", "wmv");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("464c5601050000000900", "flv");
        //暂时未找到，待测试
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("ts", "ts");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("dat", "dat");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("mov", "mov");
        //动画
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("4357530871ac0600789c", "swf");
        //音频
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("49443303000000002176", "mp3");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("52494646e27807005741", "wav");
        //文本(txt的没有文件头，单独处理)
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("d0cf11e0a1b11ae10000", "doc");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("d0cf11e0a1b11ae10000", "ppt");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("d0cf11e0a1b11ae10000", "xls");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("504b0304140006000800", "docx");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("504b0304140006000800", "pptx");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("504b0304140006000800", "xlsx");
        //TODO pdf文件头还是有部分是不一样的，要再验证下
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("255044462d312e340a25", "pdf");
        SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.put("255044462d312e350d0a", "pdf");
    }

    static {
        DUCUMENT_TYPE_MAP.put("txt", "txt");
//        DUCUMENT_TYPE_MAP.put("pdf","pdf");
        DUCUMENT_TYPE_MAP.put("doc", "doc");
        DUCUMENT_TYPE_MAP.put("docx", "docx");
        DUCUMENT_TYPE_MAP.put("xls", "xls");
        DUCUMENT_TYPE_MAP.put("xlsx", "xlsx");
        DUCUMENT_TYPE_MAP.put("ppt", "ppt");
        DUCUMENT_TYPE_MAP.put("pptx", "pptx");
    }

    static {
        VIDEO_TYPE_MAP.put("avi", "avi");
        VIDEO_TYPE_MAP.put("mov", "mov");
        VIDEO_TYPE_MAP.put("mpeg", "mpeg");
        VIDEO_TYPE_MAP.put("mp4", "mp4");
        VIDEO_TYPE_MAP.put("rmvb", "rmvb");
        VIDEO_TYPE_MAP.put("rm", "rm");
        VIDEO_TYPE_MAP.put("dat", "dat");
        VIDEO_TYPE_MAP.put("ts", "ts");
        VIDEO_TYPE_MAP.put("wmv", "wmv");
        VIDEO_TYPE_MAP.put("flv", "flv");
        VIDEO_TYPE_MAP.put("swf", "swf");
    }

    public static Integer getResFileTypeId(String fileName) {
        int resFileTypeId = 0;
        if (isLeagalName(fileName)) {
            String suffix = getFileNameSuffix(fileName);
            String isSurport = SURPORT_ONLINE_PREVIEW_FILE_TYPE.get(suffix);
            if (isSurport != null) {
                resFileTypeId = Integer.parseInt(isSurport.substring(isSurport.length() - 1));
            } else {
                //其他类型文件
                resFileTypeId = 5;
            }
        }
        return resFileTypeId;
    }

    /**
     * @param file 判断的文件
     * @Author: Wangjie
     * @Date: 2018-07-26 15:52:39
     * @Description: 文档类文件判断工具
     * @Return boolean 判断的结果
     */
    public static boolean isLegalDocumentFile(File file, String fileName) throws IOException {
        boolean flag = false;
        if (file != null) {
            if (isLeagalName(fileName)) {
                if (!isEmptyFile(file)) {
                    String fileNameSuffix = getFileNameSuffix(fileName);
                    if (!isUnSurportedType(fileNameSuffix)) {
                        //txt没有文件头之分嘚
                        if (isSurportedAndLegalFile(fileName, file) || "txt".equals(fileNameSuffix)) {
                            String document = DUCUMENT_TYPE_MAP.get(fileNameSuffix);
                            if (document != null) {
                                //说明该文件类型为文档类
                                flag = true;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @param file 传入的待判断文件对象
     * @Author: Wangjie
     * @Date: 2018-07-30 17:31:38
     * @Description: 判断是否为合法视频类文件的工具
     * @Return boolean
     */
    public static boolean isLegalVideoFile(File file) throws IOException {
        boolean flag = false;
        if (file != null) {
            String fileName = file.getName();
            if (isLeagalName(fileName)) {
                if (!isEmptyFile(file)) {
                    String fileNameSuffix = getFileNameSuffix(fileName);
                    if (!isUnSurportedType(fileNameSuffix)) {
                        //txt没有文件头之分嘚
                        if (isSurportedAndLegalFile(fileNameSuffix, file)) {
                            String video = VIDEO_TYPE_MAP.get(fileNameSuffix);
                            if (video != null) {
                                //说明该文件类型为文档类
                                flag = true;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public static boolean isSurportedAndLegalFile(String fileName, File file) {
        boolean flag = false;
        try {
            System.out.println("当前检测文件【" + fileName + "】");
            if (isLeagalName(fileName) && !isEmptyFile(file)) {
                String extension = getFileNameSuffix(fileName);
                if (!StringUtils.isEmpty(extension)) {
                    String str = SURPORT_ONLINE_PREVIEW_FILE_TYPE.get(extension);
                    if (str != null) {
                        Integer imageType = getResFileTypeId(fileName);
                        //如果是图片，则直接判断
                        if (imageType == 1) {
                            int width = 0;
                            int height = 0;
                            Exception parseException = null;
                            try {
                                boolean isbigImgFile = file.length() >FILE_LIMIT ;
                                BufferedImage bufreader =null;
                                if(!isbigImgFile){
                                     bufreader = ImageIO.read(file);
                                }
                                if(bufreader == null){
                                    //可能会存在 明明是图片，但ImageIO读出来却是null的情况
                                    FileInputStream inputStream = new FileInputStream(file);
                                    String header = getFileHeader(inputStream);
                                    MAGIC_NUBER_HEADER magicNumerType = getImageNumerType(header);
                                    if (magicNumerType != null) {
                                        flag = true;
                                        return flag;
                                    }
                                }else{
                                    width = bufreader.getWidth();
                                    height = bufreader.getHeight();
                                }
                            } catch (Exception imageException) {
                                System.out.println("解析普通图片时出现错误--->>>"+imageException.getMessage());
                                try {
                                    //如果出现4096读取gif文件4096读取问题的，则用gifCoder来判断
//                                    if("gif".equals(extension)){
                                        GifDecoder.GifImage gif = GifDecoder.read(getBytes(file));
                                        if(gif == null){
                                            parseException  = imageException;
                                        }else{
                                            width = gif.getWidth();
                                            height = gif.getHeight();
                                        }
                                } catch (Exception gifException) {
                                    System.out.println("解析gif图片时出现错误--->>>"+gifException.getMessage());
                                    parseException = gifException;
                                }
                            }finally {
                                if(parseException != null){
                                    throw new ServiceException("图片文件解析失败:"+parseException.getMessage());
                                }
                            }
                            if (width == 0 || height == 0) {
                                flag = false;
                            } else {
                                flag = true;
                            }
//                            String mimetype = Files.probeContentType(file.toPath());
//mimetype should be something like "image/png"

//                            if (mimetype != null && mimetype.split("/")[0].equals("image")) {
//                                System.out.println("it is an image");
//                            }
//                            String mimetype = Files.probeContentType(file.toPath());
//                            String type = mimetype.split("/")[0];
//                            if(type.equals("image")) {
//                                System.out.println("************-----------------------当前文件【"+file.getName()+"】"+"为图片文件----------------------************");
//                                flag = true;
//                            } else {
//                                System.out.println("It's NOT an image");
//                                flag = false;
//                            }
                            return flag;
                        } else {
                            FileInputStream inputStream = new FileInputStream(file);
                            String header = getFileHeader(inputStream);
                            if (VIDEO_TYPE_MAP.get(extension) != null) {
                                header = header.substring(0, 6);
                            }
                            String str2 = SURPORT_ONLINE_PREVIEW_FILE_TYPE_HEADER.get(header);
                            //txt是一种特殊类型的文件
                            if (str2 != null || "txt".equals(extension)) {
                                flag = true;
                            } else {
                                //由魔法值头部再来判断
                                MAGIC_NUBER_HEADER magicNumerType = getImageNumerType(header);
                                if (magicNumerType != null) {
                                    flag = true;
                                }
                            }
                        }
                    } else {
                        //如果是不需要在线预览的，且不在不支持文件类型列表中的，也是支持上传的，属【其他】类型
                        str = UNSURPORT_FILE_TYPE.get(extension);
                        if (str == null) {
                            flag = true;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
        return flag;
    }

    private static byte[] getBytes(File file) {
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
    /**
     *****************************************
     * Created with IntelliJ IDEA.
     * @Author: Wangjie
     * @Date: 2018-08-14 18:47:36
     * @Description: 新增枚举的辅助类型来判断文件的真实类型
     * 来自：Java 通过魔数判断上传文件的类型 - CSDN博客
    https://blog.csdn.net/t894690230/article/details/51242110
     */

    /**
     * 魔数值判断
     */
    enum MAGIC_NUBER_HEADER {
        //图片
        JPEG("49492A"),
        JPG("FFD8FF"),
        PNG("89504E47"),
        GIF("47494638"),
        GIF2("89504E47"),
        BMP("424D"),

        //视频
        AVI("41564920"),
        AVI2("524946"),
        MP4("000000"),
        MPEG("000001"),
        RMVB("2E524D"),
        WMV("3026B2"),
        FLV("464C56"),
        TS("474011"),
        //动画(划归到视频类中)
        SWF("435753"),
        SWF2("465753"),
        //音频
        MP3("49443303"),
        WAV("52494646"),
        //文档
        XLS_DOC_PPT("D0CF11E0"),
        XLS_DOC_PPT2("504B0304"),
        PDF("25504446"),
        PDF2("255044462D312E"),
        /**
         * doc;xls;dot;ppt;xla;ppa;pps;pot;msi;sdw;db
         */
        OLE2("0xD0CF11E0A1B11AE1"),
        /**
         * Real Audio
         */
        RAM("2E7261FD"),
        /**
         * Real Media
         */
        RM("2E524D46"),
        /**
         * Quicktime
         */
        MOV("6D6F6F76"),
        /**
         * Windows Media
         */
        ASF("3026B2758E66CF11");

        private String value = null;

        private MAGIC_NUBER_HEADER(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * 判断文件类型
     */
    public static MAGIC_NUBER_HEADER getImageNumerType(String fileHead) throws IOException {
        if (fileHead != null && fileHead.length() > 0) {
            fileHead = fileHead.toUpperCase();
            MAGIC_NUBER_HEADER[] fileTypes = MAGIC_NUBER_HEADER.values();

            for (MAGIC_NUBER_HEADER type : fileTypes) {
                if (fileHead.startsWith(type.getValue())) {
                    return type;
                }
            }
        }
        return null;
    }

    /**
     * ****************************************
     */


    public static boolean isUnSurportedType(String fileNameSuffix) {
        boolean flag = false;
        if (!StringUtils.isEmpty(fileNameSuffix)) {
            String str = UNSURPORT_FILE_TYPE.get(fileNameSuffix);
            if (str != null) {
                flag = true;
            }
        }
        return flag;
    }

    public static boolean isLeagalName(String fileName) {
        ParamUtil.isNotBlank(fileName, "名称参数不能为空");
        boolean flag = false;
        if (!StringUtils.isEmpty(fileName)) {
            if (fileName.length() < FILE_NAME_MAX_LENGTH) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @param file
     * @Author: Wangjie
     * @Date: 2018-07-26 14:02:46
     * @Description: 判断是否为空文件
     * @Return boolean
     */
    public static boolean isEmptyFile(File file) {
        boolean flag = true;
        if (file != null) {
            long length = FileUtils.sizeOf(file);
            if (length != 0) {
                flag = false;
                //针对xls这种还要再判断下内容
                Map<String, String> excelMap = new HashMap<>(2);
                excelMap.put("xls", "xls");
                excelMap.put("xlsx", "xlsx");
                String fileNameSuffix = getFileNameSuffix(file.getName());
                if (fileNameSuffix != null) {
                    String excel = excelMap.get(fileNameSuffix);
                    if (excel != null) {
                        try {
                            FileInputStream inputStream = FileUtils.openInputStream(file);
                            System.out.println("---------" + excel);
                            if ("xlsx".equals(excel)) {
                                XSSFWorkbook wb = new XSSFWorkbook(inputStream);
                                XSSFSheet sheet = wb.getSheetAt(0);
                                Iterator ite = sheet.rowIterator();
                                if (ite.hasNext()) {
                                    flag = false;
                                } else {
                                    flag = true;
                                }
                            } else if ("xls".equals(excel)) {
                                HSSFWorkbook wb = new HSSFWorkbook(inputStream);
                                HSSFSheet sheet = wb.getSheetAt(0);
                                Iterator ite = sheet.rowIterator();
                                if (ite.hasNext()) {
                                    flag = false;
                                } else {
                                    flag = true;
                                }
                            }
                        } catch (Exception e) {
                            throw new ServiceException(e.getMessage());
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * @param fileName 文件名
     * @Author: Wangjie
     * @Date: 2018-07-26 15:15:10
     * @Description: 获取文件名后缀的方法
     * @Return java.lang.String 文件名的后缀
     */
    public static String getFileNameSuffix(String fileName) {
        String surffix = null;
        if (!StringUtils.isEmpty(fileName)) {
            surffix = FilenameUtils.getExtension(fileName);
        }
        return surffix;
    }

    /**
     * @param file 图片文件对象
     * @Author: Wangjie
     * @Date: 2018-07-30 18:47:28
     * @Description: 判断文件是否为图片
     * @Return boolean
     */
    public static final boolean isImage(String fileName, File file) {
        boolean flag = false;
        try {
            if (isSurportedAndLegalFile(fileName, file)) {
                String imgSuffix = getFileNameSuffix(fileName);
                if (!"gif".equals(imgSuffix)) {
                    BufferedImage bufreader = ImageIO.read(file);
                    int width = bufreader.getWidth();
                    int height = bufreader.getHeight();
                    if (width == 0 || height == 0) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
        } catch (IOException e) {
            flag = false;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * @param inputStream 文件的输入流
     * @Author: Wangjie
     * @Date: 2018-07-26 14:00:27
     * @Description: 获取到文件夹的头部(10个字节)
     * @Return java.lang.String 文件头字符串
     */
    public static String getFileHeader(FileInputStream inputStream) {
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

    /**
     * @param src 文件流字节数组
     * @Author: Wangjie
     * @Date: 2018-07-26 14:02:18
     * @Description: 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
     * @Return java.lang.String
     */
    public static String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            hv = Integer.toHexString(src[i] & 0xFF);
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }


    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    public static boolean deletFile(File file) {
        /**
         * @Author: Wangjie
         * @Date: 2018-07-26 15:23:53
         * @Description: 删除文件
         * @param  file  要删除的文件对象
         * @Return boolean 删除结果
         */
        boolean flag = false;
        if (file != null) {
            if (file.exists()) {
                file.delete();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 复制文件工具
     */
    /**
     * 传统流的形式，注意write()时的长度，不然会造成文件打不开的问题
     *
     * @param source 源文件
     * @param dest   复制文件
     */
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

    /**
     * 用apache的commons-io包中封装的方法
     *
     * @param source 源文件
     * @param dest   复制文件
     * @throws IOException
     */
    public static void copyFileUsingApacheCommonsIO(File source, File dest)
            throws IOException {
        FileUtils.copyFile(source, dest);
    }

    /**
     * Java NIO包括transferFrom方法,根据文档应该比文件流复制的速度更快
     *
     * @param source 源文件
     * @param dest   复制文件
     * @throws IOException
     */
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
            LOGGER.info(source.getName() + ">>>----文件复制成功！----->>>" + dest.getName() + "--->>>" + "成功复制后的文件大小为:" + dest.length());
        }
    }


    /**
     * Created with IntelliJ IDEA.
     * @Author: Wangjie
     * @Date: 2018-07-29 10:05:10
     * @Description: 处理txt类型文件的工具类
     */
    /**
     * 编码方式
     */
    private final static String CODING = "UTF-8";

    /**
     * 新建或追加内容到一个文件
     *
     * @param path     文件目录
     * @param fileName 文件名
     * @param content  内容
     * @param append   是否追加写入文件
     * @return 是否写入成功
     */
    public static boolean writeFile(String path, String fileName, String content, boolean append) {
        File dir = new File(path);
        if (dir.exists() || dir.mkdirs()) {
            Writer writer = null;
            try {
                OutputStream os = new FileOutputStream(path + File.separator + fileName, append);
                writer = new OutputStreamWriter(os, CODING);
                writer.write(content);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 以指定编码方式读取文件，返回文件内容
     *
     * @param file            要转换的文件
     * @param fromCharsetName 源文件的编码
     * @return
     * @throws Exception
     */
    public static String getFileContentFromCharset(File file,
                                                   String fromCharsetName) throws Exception {
        if (!Charset.isSupported(fromCharsetName)) {
            throw new UnsupportedCharsetException(fromCharsetName);
        }
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream,
                fromCharsetName);
        char[] chs = new char[(int) file.length()];
        reader.read(chs);
        String str = new String(chs).trim();
        reader.close();
        return str;
    }

    /**
     * 以指定编码方式写文本文件，存在会覆盖
     *
     * @param file          要写入的文件
     * @param toCharsetName 要转换的编码
     * @param content       文件内容
     * @throws Exception
     */
    public static void saveFile2Charset(File file, String toCharsetName,
                                        String content) throws Exception {
        if (!Charset.isSupported(toCharsetName)) {
            throw new UnsupportedCharsetException(toCharsetName);
        }
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outWrite = new OutputStreamWriter(outputStream,
                toCharsetName);
        outWrite.write(content);
        outWrite.close();
    }
}
