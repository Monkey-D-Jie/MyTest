package com.jf.mydemo.mytest.Utils.uploadFileUtils.cloudUtils;

import com.alibaba.fastjson.JSONObject;
import com.jf.mydemo.mytest.Utils.string.JsonXmlObjectKit;
import com.jf.mydemo.mytest.Utils.uploadFileUtils.exception.ServiceException;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Cleaner;

import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-07-25 16:57
 * @Description: 上传文件至服务器的工具
 * To change this template use File | Settings | File and Templates.
 */

public class CloudUploadFileUtil {
    /**
     * 打印日志用logger-用debug级别
     */
    private static Logger LOGGER = LoggerFactory.getLogger(CloudUploadFileUtil.class.getName());


    /**
     * 存放9620上传文件cloud类型必要参数的list
     */
    private static List<String> cloudParamList = null;
    private static String videoUpDirId = null;
    /**
     * 存储类型
     */
    private static String KMS_TYPE = "kms";
    private static String CLOUD_TYPE = "cloud";
    /**
     * 上传文件
     * 通过System.getenv("SystemDrive")获得系统盘目录
     */
    public static final String uploadTempDir = getMaxDriver()+"\\convertTemp\\upload\\";

    public static String getMaxDriver() {
        String maxDriver;
        // 当前文件系统类
        // 列出所有windows 磁盘
        File[] fs = File.listRoots();
        Map<String, Long> map = new HashMap<>(fs.length);
        // 显示磁盘卷标
        for (int i = 0; i < fs.length; i++) {
            map.put(fs[i].toString(), fs[i].getFreeSpace());
        }
        //对map排序
        List<Map.Entry<String, Long>> list2 = new ArrayList<>();
        list2.addAll(map.entrySet());
        Collections.sort(list2, Comparator.comparingLong(Map.Entry::getValue));
        maxDriver = (list2.get(list2.size()-1).getKey()).replace("\\","");
        return maxDriver;
    }

    /**
     * 2.上传文件并获取到上传的文件id
     */
    /**
     * @Author: Wangjie
     * @Date:   2018-08-01 18:35:17
     * @Description: 上传文件并获取到服务器返回的关键信息
     * @param  uploadFile  上传的文件对象
     * @param  addr  上传文件地址信息
     * @param  fileName  文件名
     * @param  contentType   文件头
     * @Return  java.lang.String 文件存储id
     */
    public static String uplpadFileAndGetStorageId(String orgCode, File uploadFile, UploadAddr addr,
                                                   String fileName, String contentType) throws Exception {
        URL url = new URL(addr.getUrl());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //是否回传视频信息的标识
        boolean backWriteVideoFlag = false;
//        byte[] file = toByteArray3(uploadFile);
        // 分隔符
        String BOUNDARY = "---------------------------7d4a6d158c9";
        StringBuffer sb = new StringBuffer();
        // 发送每个字段:
        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"666666\"\r\n\r\n");
        sb = sb.append("\r\n");


        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"666666\"\r\n\r\n");
        sb = sb.append("\r\n");


        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"666666\"\r\n\r\n");
        sb = sb.append("\r\n");


        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"666666\"\r\n\r\n");
        sb = sb.append("\r\n");


        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"666666\"\r\n\r\n");
        sb = sb.append("\r\n");


        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"666666\"\r\n\r\n");
        sb = sb.append("\r\n");



        // 发送文件:
        sb = sb.append("--");
        sb = sb.append(BOUNDARY);
        sb = sb.append("\r\n");
        sb = sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""+fileName+"\"\r\n");
        sb = sb.append("Content-Type: "+contentType+"\r\n\r\n");
        byte[] data = sb.toString().getBytes();
        byte[] end_data = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
        // 设置HTTP头:
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        conn.setRequestProperty("Content-Length", String.valueOf(data.length + uploadFile.length() + end_data.length));

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        //设置该选项，则不使用HttpURLConnection的缓存机制，直接将流提交到服务器上。
        conn.setChunkedStreamingMode(0);
        // 输出:
        FileInputStream fis = null;
        OutputStream output = conn.getOutputStream();
        try {
            /**
             *****************************************
             * Created with IntelliJ IDEA.
             * @Author: Wangjie
             * @Date: 2018-09-05 18:18:10
             * @Description: 修改读取文件的方法
             */
            output.write(data);
            fis = new FileInputStream(uploadFile);
            byte[] buffer = new byte[10240];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
            /**
             *****************************************
             */
            output.write(end_data);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭掉这个输出流
            IOUtils.closeQuietly(output);
            IOUtils.closeQuietly(fis);
        }

        if (conn.getResponseCode() >= 300) {
            throw new ServiceException("HTTP Request is not success, Response code is " + conn.getResponseCode());
        }

        if(conn.getResponseCode() == 200){
            InputStream inputStream = conn.getInputStream();
            byte[] buff = new byte[8192];
            int buffLen;
            String ret = "";
            while ((buffLen = inputStream.read(buff)) != -1) {
                ret += new String(buff, 0, buffLen, "utf-8");
            }
            inputStream.close();
            conn.disconnect();
            //处理返回结果
            String fileId=null;
            if(KMS_TYPE.equals(addr.getStorageType())){
                try {
                    Document doc = DocumentHelper.parseText(ret);
                    Element rootElt = doc.getRootElement();
                    String retStr = rootElt.element("666666").getTextTrim().toString();
                    if(!"0".equals(retStr)){
                        throw new ServiceException(
                                "retStr:"+retStr+"---"+ret);
                    }
                    fileId = rootElt.element("id").getTextTrim().toString();
                } catch (DocumentException e) {
                    throw new ServiceException(e.getMessage());
                }
            }else if(CLOUD_TYPE.equals(addr.getStorageType())){
                int a = ret.indexOf("\"ret\"");
                String retStr = ret.substring(a+6,a+7);
                if(!"0".equals(retStr)){
                    throw new ServiceException(
                            "mag");
                }
                fileId = addr.getKey();
//                upVideoUrl = "cloudUrl";
            }
            return fileId;
        }
        return null;
    }
    /**
     * 摘自：
     * 读取Java文件到byte数组的三种方式 - sharewind - ITeye博客
     http://sharewind.iteye.com/blog/1582869
     */
    /**
     * the traditional io way
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException{

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

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
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filename)throws IOException{

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            while((channel.read(byteBuffer)) > 0){
                // do nothing
//              System.out.println("reading");
            }
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
    public static byte[] toByteArray3(File file)throws IOException{

        FileChannel fc = null;
        MappedByteBuffer byteBuffer = null;
        try{
            fc = new RandomAccessFile(file,"r").getChannel();
            byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
            byte[] result = new byte[(int)fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }  finally{
            try {
                clean(byteBuffer);
                IOUtils.closeQuietly(fc);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 因为使用mappedByBuffer处理文件时，
     * 关掉信道FileChannel后，mappedByBuffer对象仍然会持有对应处理文件的句柄。
     * 要等到该文件的GC完成后，才能用delete方法删除掉转换用的临时文件。
     * 而该GC什么时候完成是不确定的，所以有了如下的删除方法
     */
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

    private static boolean isVideoTypeFile(String fileName) {
        boolean flag = false;
        String isVideo = CloudFileUtil.VIDEO_TYPE_MAP.get(CloudFileUtil.getFileNameSuffix(fileName));
        if (isVideo != null) {
            flag = true;
        }
        return flag;
    }

    /**
     * 4.转码文件上传
     */
    /**
     * @Author: Wangjie
     * @Date:   2018-08-08 14:42:46
     * @Description: 上传pdf文件
     * @param  orgCode  学校机构id
     * @param  pdfFile  pdf文件
     * @param  uploadAddr   上传地址信息
     * @Return  java.lang.String 返回的文件id
     */
    public static String uploadPdfFile(String orgCode, File pdfFile, UploadAddr uploadAddr) throws Exception {
        String pdfId;
        /**
         * 1)先获取到转好的pdf文件
         */
        if (pdfFile != null) {
            /**
             * 2)上传该pdf文件至9527
             */
            pdfId = uplpadFileAndGetStorageId(orgCode,pdfFile,uploadAddr, pdfFile.getName(),"application/pdf");
            if (pdfId == null) {
                throw new ServiceException(pdfFile.getName()+"上传至9527失败");
            }
        } else {
            throw new ServiceException("pdf文件参数为空");
        }
        return pdfId;
    }

    /**
     * 删除服务器文件
     * @param fileId 对应的图片文件id
     * @param orgCode 学校结构id
     * @return 响应结果
     */
    public static String delServerResFile2(String orgCode,String fileId) {
        String result = null;
        String url =null;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fileId",fileId);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonObject.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        try {
            HttpResponse httpResponse = client.execute(httpPost);
            HttpEntity resEntity = httpResponse.getEntity();
            Map<String,Object> resultMap = JsonXmlObjectKit.convertJsonObject(EntityUtils.toString(resEntity, "UTF-8"),Map.class);
            result = (String) resultMap.getOrDefault("code",null);
            if(!"1".equals(result)){
               LOGGER.debug("msg");
            }
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.debug(e.getMessage());
        }catch (Exception e) {
            e.printStackTrace();
            LOGGER.debug(e.getMessage());
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.debug(e.getMessage());
            }
        }
        return result;
    }
}
