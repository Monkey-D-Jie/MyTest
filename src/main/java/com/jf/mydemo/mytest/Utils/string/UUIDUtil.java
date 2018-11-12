package com.jf.mydemo.mytest.Utils.string;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 生成32位UUID
 */
public class UUIDUtil {

    /**
     * 返回头类型
     */
    public static final HashMap<String, String> CONTENT_TYPE = new HashMap<>(30);

    static {
        //图片
        CONTENT_TYPE.put("bmp","application/x-bot");
        CONTENT_TYPE.put("jpeg","image/jpeg");
        CONTENT_TYPE.put("jpg","image/jpeg");
        CONTENT_TYPE.put("png","image/png");
        CONTENT_TYPE.put("gif","image/gif");
        //视频
        CONTENT_TYPE.put("avi","video/avi");
        CONTENT_TYPE.put("3gp","video/3gpp");
    //    CONTENT_TYPE.put("mpeg","video/mpg");
    //    CONTENT_TYPE.put("mp4","video/mpeg4");
     //   CONTENT_TYPE.put("rmvb","application/vnd.rn-realmedia-vbr");
        CONTENT_TYPE.put("rm","application/vnd.rn-realmedia");
    //    CONTENT_TYPE.put("wmv","video/x-ms-wmv");
        CONTENT_TYPE.put("flv","video/x-flv");
     //   CONTENT_TYPE.put("mov","video/quicktime");

        CONTENT_TYPE.put("dat","video/mpeg4");
        CONTENT_TYPE.put("ts","video/mpeg4");

        //动画
        CONTENT_TYPE.put("swf","application/x-shockwave-flash");
        //文本
        CONTENT_TYPE.put("txt","application/pdf");
        CONTENT_TYPE.put("pdf","application/pdf");
        CONTENT_TYPE.put("doc","application/pdf");
        CONTENT_TYPE.put("docx","application/pdf");
        CONTENT_TYPE.put("xls","application/pdf");
        CONTENT_TYPE.put("xlsx","application/pdf");
        CONTENT_TYPE.put("ppt","application/pdf");
        CONTENT_TYPE.put("pptx","application/pdf");
    }

    public static String generateUUID() {
        String uuid= UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }
    /**
     * 获得默认课堂名称
     * @param subName
     * @param teacherName
     * @param claName
     * @param actureDate
     * @return
     */
    public static String getDefaultCurName(String subName, String teacherName, String claName, Date actureDate){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String date = simpleDateFormat.format(actureDate);
        return subName + "_" + teacherName + "_" + date + "_" + claName;
    }
    /**
     * 获得默认录播资源名称
     * @param subName
     * @param teacherName
     * @param claName
     * @param actureDate
     * @return
     */
    public static String getDefaultVodName(String subName, String teacherName, String claName, Date actureDate,Integer lessonOrder){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String date = simpleDateFormat.format(actureDate);
        return subName + "_" + teacherName + "_" + date + "_" + lessonOrder + "_" + claName+".mp4";
    }
    /**
     * 字符串MD5加密算法
     * @param sourceStr
     * @return
     */
    public static String MD5(String sourceStr)
    {
        MessageDigest mdInst;
        try
        {
            mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(sourceStr.getBytes());

            byte[] md = mdInst.digest();
            StringBuffer buf = new StringBuffer();
            for (int i = 0; i < md.length; ++i) {
                int tmp = md[i];
                if (tmp < 0) {
                    tmp += 256;
                }
                if (tmp < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(tmp));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获得下载文件的资源名称
     * @param fileName
     * @return
     */
    public static String getResourceDOwnFileName(String fileName) throws UnsupportedEncodingException {
        StringBuffer out = new StringBuffer(fileName.length());
        for (int i = 0; i < fileName.length();i++) {
            char c = fileName.charAt(i);
            String regEx =  "[ _`!@#$%^&()+=|{}*':;',\\[\\].<>/?~|\n|\r|\t";
            int flag = regEx.indexOf(c);
            if(flag != -1){
                out.append(c);
                continue;
            }
            out.append( URLEncoder.encode( String.valueOf(c), "UTF-8"));
        }
        return out.toString();
    }
}
