package com.jf.mydemo.mytest.Utils.txt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
 * 写入文件工具类
 * Created by xdj on 2017/9/6.
 */
public class WriteTxtKit {

    private static Logger logger = LogManager.getLogger(WriteTxtKit.class);

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
                if (logger.isErrorEnabled()) {
                    logger.error("写入文件错误", e);
                }
                return false;
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        if (logger.isErrorEnabled()) {
                            logger.error("写入文件关闭流异常", e);
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
