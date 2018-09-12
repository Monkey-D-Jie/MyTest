package com.jf.mydemo.mytest.Utils.uploadFileUtils.officeUtils;

import com.jf.mydemo.mytest.Utils.uploadFileUtils.cloudUtils.CloudFileUtil;
import com.jf.mydemo.mytest.Utils.uploadFileUtils.exception.ServiceException;
import com.jf.mydemo.mytest.Utils.uploadFileUtils.officeUtils.txtFiledeDecodeUtil.EncodingDetect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.OfficeException;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-06-25 17:22
 * @Description: libreOffice专用的转换类
 * To change this template use File | Settings | File and Templates.
 */

public class LibreOfficeConvert2PdfUtil {


    private static Logger logger = LogManager.getLogger(LibreOfficeConvert2PdfUtil.class);


    private static volatile OfficeManager officeManager = null;
    //, 8005, 8006----->多个端口开启时，启动时有报错的可能
    private static final int[] libreOfficePort = {8200};

    /**
     * 临时文件存储路径
     */
    public final static String LIBRE_CONVERT_TEMP_DIR = System.getenv("SystemDrive")+"\\convertTmp\\";

    /**
     * @Author: Wangjie
     * @Date: 2018-07-26 16:19:02
     * @Description: 转码文件
     * @param inputFile 需要转码的文件
     * @Return file 转码后的文件
     */
    public static File convertFile(File inputFile, String fileName, String libreHome) {
        File outFile;
        try {
            synchronized (LibreOfficeConvert2PdfUtil.class) {
                if (officeManager == null) {
                    officeManager = OfficeSingletonUtil.getLibreOfficeSingleton(libreHome, libreOfficePort);
                    if (officeManager != null) {
                        officeManager.start();
                        logger.debug("libreOffice*******************officeManager对象开启成功，只有一次******************libreOffice");
                    }
                }
            }
            // Convert
            logger.debug("开始转换----------libreOffice-----------开始转换");
            //准备文件 "GBK"
            String fileNameSuffix = CloudFileUtil.getFileNameSuffix(fileName);
            if ("txt".equals(fileNameSuffix)) {
                String txtCode= EncodingDetect.getJavaEncode(inputFile);
                String txtContent = CloudFileUtil.getFileContentFromCharset(inputFile, txtCode);
                CloudFileUtil.saveFile2Charset(inputFile, "UTF-8", txtContent);
            }
            String pdfName = fileName.replace(fileNameSuffix, "pdf");
            outFile = new File(LIBRE_CONVERT_TEMP_DIR + pdfName);
            //转码文件
            OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
            converter.getFormatRegistry();
            converter.convert(inputFile, outFile);
            logger.debug("转换结束----------libreOffice-----------转换结束");
        } catch (OfficeException e) {
            e.printStackTrace();
            throw new ServiceException("转码文件时出现了异常---->>" + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("转码文件时出现了异常---->>" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("转码文件时出现了异常---->>" + e.getMessage());
        }
        return outFile;
    }
}
