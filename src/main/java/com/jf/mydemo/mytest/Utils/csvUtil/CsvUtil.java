package com.jf.mydemo.mytest.Utils.csvUtil;

import com.jf.mydemo.mytest.Unsafe.DateFormatKit;
import com.jf.mydemo.mytest.Utils.csvUtil.entity.DisciplineStudentInfo;
import com.univocity.parsers.csv.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//import com.univocity.parsers.csv.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-05-13 22:08
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 *
 官方说明文档：
Univocity-parsers - tutorial – uniVocity data integration
https://www.univocity.com/pages/univocity_parsers_tutorial
 *
 */

public class CsvUtil {
    /**
     * 打印日志用logger-用debug级别
     *
     */
    private static Logger LOGGER = LoggerFactory.getLogger(CsvUtil.class.getName());

    /**
     *
     作者：Summer329
     来源：CSDN
     原文：https://blog.csdn.net/xiaxinxx/article/details/51655999
     版权声明：本文为博主原创文章，转载请附上博文链接！
     */
    public static void createCSVFile(String[] heads, List<Object[]> rows, String outPutPath, String filename)
    {

        // CsvWriter (and all other file writers) work with an instance of
        // java.io.Writer
        File csvFile = new File(outPutPath + filename + ".csv");
        File parent = csvFile.getParentFile();
        if (parent != null && !parent.exists())
        {
            parent.mkdirs();
        }
        try
        {
            csvFile.createNewFile();
            Writer fileWriter = new FileWriter(csvFile);

            // By default, only values that contain a field separator are enclosed within quotes.
            // If quotes are part of the value, they are escaped automatically as well. Empty rows are discarded automatically.
            // Set the field delimiter to ';', the default value is ','
            CsvWriterSettings settings = new CsvWriterSettings();
            CsvFormat format = settings.getFormat();
            format.setDelimiter(';');

            CsvWriter writer = new CsvWriter(fileWriter, settings);

            // Write the record headers of this file
            writer.writeHeaders(heads);

            // Write contents and close the given output Writer instance.
            writer.writeRowsAndClose(rows);
        } catch (Exception e)
        {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
        }
    }
//读取csv文件：
    public static List<Object> ReadCSV(String filePath) throws IOException {
//        List<MyType> eslImports = new ArrayList<MyType>();

        File file = new File(filePath);
        InputStream in = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(in, "UTF-8");

        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setLineSeparator("\n");

        CsvParser parser = new CsvParser(settings);
        //读取数据到列表
        List<String[]> allRows = parser.parseAll(reader);

        //处理读取到的数据

//       .....
        return null;
    }

    /**
     * 来自：
     * Java 导出 CSV - 残星 - 博客园的评论
     https://www.cnblogs.com/mingforyou/p/4103132.html
     */
    public static File createCSVFile(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename) {
        File csvFile = null;
        try {
            // 1. 创建文件
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // ===========  2. 写出CSV文件     ============//
            CsvWriterSettings writerSettings = new CsvWriterSettings();
            writerSettings.setHeaders((String[]) head.toArray());
            CsvWriter writer = new CsvWriter(new FileWriter(csvFile), writerSettings);
            writer.writeRows(dataList);
        } catch (IOException e) {
            System.out.println("Failed to create CSV file for writing data.");
        }

        return csvFile;
    }



    //来自：
    /**
     *  * Java 导出 CSV - 残星 - 博客园的评论
     https://www.cnblogs.com/mingforyou/p/4103132.html
     */
    /**
     * CSV文件生成方法
     * @return
     */
    public static File createCSVFile2(List<Object> head, List<List<Object>> dataList,
                                     String outPutPath, String filename) {

        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(outPutPath + File.separator + filename + ".csv");
            File parent = csvFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312使正确读取分隔符","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                    csvFile), "GB2312"), 1024);
            // 写入文件头部
//            writeRow(head, csvWtriter);
            //改造
            List<Object> firstRow = new ArrayList<>();
            firstRow.add("广东省考试院");
            firstRow.add("考生总数："+10);
            firstRow.add("缺考总数："+10);
            firstRow.add("最终缺考总数："+10);
            writeRow2(firstRow,head, csvWtriter);
            // 写入文件内容
            for (List<Object> row : dataList) {
//                writeRow(row, csvWtriter);
                writeRow2(null,row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * 写一行数据方法
     * @param row
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

    private static void writeRow2(List<Object> firstRow,List<Object> row, BufferedWriter csvWriter) throws IOException {
        if(firstRow != null && firstRow.size() > 0){
            // 写入文件头部
            for (Object data : firstRow) {
                StringBuffer sb = new StringBuffer();
                String rowStr = sb.append("\"").append(data).append("\",").toString();
                csvWriter.write(rowStr);
            }
            csvWriter.newLine();
        }
        // 写入文件头部
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }

//    public @ResponseBody void exportExcel(HttpServletRequest request, HttpServletResponse response, KhxxCxVO vo) throws IOException{
//        File csvFile = createCSVFile(request,vo);
//
//        BufferedInputStream bis = null;
//        BufferedOutputStream bos = null;
//
//        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(csvFile.getName(), "UTF-8"));
//
//        response.setHeader("Content-Length", String.valueOf(csvFile.length()));
//
//        bis = new BufferedInputStream(new FileInputStream(csvFile));
//        bos = new BufferedOutputStream(response.getOutputStream());
//        byte[] buff = new byte[2048];
//        while (true) {
//            int bytesRead;
//            if (-1 == (bytesRead = bis.read(buff, 0, buff.length))) break;
//            bos.write(buff, 0, bytesRead);
//        }
//        bis.close();
//        bos.close();
//    }

//
//    public File createCSVFile(KhxxCxVO vo){
//        //1.获取到实体的bean类信息0
//        vo.setKhxm(StringUtil.formatDbLikeValue(vo.getKhxm()));
//
//        String yybh = ContextUtil.getLoginUser().getUserId();
//        String cur_ssjg = ContextUtil.getLoginUser().getUserUnit();
//        String unitPath = ContextUtil.getLoginUser().getUnitPath();
//
//        IPStaffVO staff = ipStaffService.findStaffByKey(yybh);
//
//        String yhlx = staff.getYhlx();
//
//        if((!cur_ssjg.equals(unitPath)) && yhlx.equals("2")){
//            vo.setCur_path(StringUtil.formatDbLeftLikeValue(unitPath.trim()));
//        }else if(yhlx.equals("1")){
//            vo.setCur_ssjg(cur_ssjg.trim());
//        }
//
//        // 设置表格头
//        Object[] head = {"客户姓名", "证件类型", "证件号码", "银行账号", "理财账号", "客户类型", "风险等级", "归属状况", "归属机构", "客户经理", "营销比例(%)" };
//        List<Object> headList = Arrays.asList(head);
//
//        List<KhxxCxVO> list = iKhxxCxService.findAllInfos(vo, Integer.MAX_VALUE, 0);
//
//        // 码表取出证件类型
//        Map<String, String> zjlx_map = new HashMap<String, String>();
//        List<IPCodeInfoVO> zjlx_list = directoryService.findInfoListByTypeCode("zjlx", null);
//        if ((zjlx_list != null) && (zjlx_list.size() > 0)){
//            for (Iterator i$ = zjlx_list.iterator(); i$.hasNext(); ) {
//                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
//                zjlx_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
//            }
//        }
//        Map<String, String> khlx_map = new HashMap<String, String>();
//        List<IPCodeInfoVO> khlx_list = directoryService.findInfoListByTypeCode("khlx", null);
//        if ((khlx_list != null) && (khlx_list.size() > 0)){
//            for (Iterator i$ = khlx_list.iterator(); i$.hasNext(); ) {
//                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
//                khlx_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
//            }
//        }
//        Map<String, String> fxdj_map = new HashMap<String, String>();
//        List<IPCodeInfoVO> fxdj_list = directoryService.findInfoListByTypeCode("fxdj", null);
//        if ((fxdj_list != null) && (fxdj_list.size() > 0)){
//            for (Iterator i$ = fxdj_list.iterator(); i$.hasNext(); ) {
//                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
//                fxdj_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
//            }
//        }
//        Map<String, String> gszk_map = new HashMap<String, String>();
//        List<IPCodeInfoVO> gszk_list = directoryService.findInfoListByTypeCode("gszk", null);
//        if ((gszk_list != null) && (gszk_list.size() > 0)){
//            for (Iterator i$ = gszk_list.iterator(); i$.hasNext(); ) {
//                IPCodeInfoVO ipci_vo = (IPCodeInfoVO)i$.next();
//                gszk_map.put(ipci_vo.getMblxbh(), ipci_vo.getMbtmz());
//            }
//        }
//        // 设置数据
//        List<List<Object>> dataList = new ArrayList<List<Object>>();
//        List<Object> rowList = null;
//        for (int i = 0; i < list.size(); i++) {
//            rowList = new ArrayList<Object>();
//            KhxxCxVO kc_vo = list.get(i);
//            //rowList.add(i + 1);
//            rowList.add(kc_vo.getKhxm());
//            rowList.add(StringUtil.nullToSpace(zjlx_map.get(kc_vo.getZjlx().trim())));
//
////                String zjhm= kc_vo.getZjhm();
////                System.out.println("zjhm----------"+zjhm);
////                DecimalFormat df = new DecimalFormat("#");//转换成整型
////                String zjhm_2 = df.format(zjhm);
////                System.out.println("zjhm2----------"+zjhm_2);
////                String zjhm_str = String.format("%.0f",kc_vo.getZjhm());
////                System.out.println("zjhmstr----------"+zjhm_str);
//
//            rowList.add(kc_vo.getZjhm());
//            rowList.add(kc_vo.getZhdh());
//            rowList.add(kc_vo.getLczh());
//            rowList.add(StringUtil.nullToSpace(khlx_map.get(kc_vo.getKhlx().trim())));
//            rowList.add(StringUtil.nullToSpace(fxdj_map.get(kc_vo.getFxdj().trim())));
//            rowList.add(StringUtil.nullToSpace(gszk_map.get(kc_vo.getGszk().trim())));
//            rowList.add(kc_vo.getGsjgmc());
//            rowList.add(kc_vo.getGsjl());
//            rowList.add(kc_vo.getYxbl());
//            //String cjsj = DateTimeUtil.formatDateTime(kc_vo.getCjsj());
//            //rowList.add(cjsj);
//            dataList.add(rowList);
//        }
//
//        // 导出文件路径
//        String downloadFilePath = "C:" + File.separator + "cap4j" + File.separator + "download" + File.separator;
//        IPCodeInfoVO codeInfoVO = directoryService.findInfoByTypeCodeAndInfoCode(
//                CFNConstants.PLATFORM_CONFIG, CFNConstants.PLATFORM_CONFIG_DOWNLOAD_PATH);
//        if (codeInfoVO != null && !StringUtils.isEmpty(codeInfoVO.getMbtmz())) {
//            downloadFilePath = codeInfoVO.getMbtmz();
//        }
//
////            String downloadFilePath = request.getSession().getServletContext().getRealPath("/exportload");
//
//        // 导出文件名称
//        String datetimeStr = DateTimeUtil.formatDate(new Date(), "yyyyMMddHHmmss");
//        String fileName = "客户列表_" + datetimeStr;
//
////            String fileName = "";
////            try {
////                fileName = URLDecoder.decode("khxxCx_list","utf-8");
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        // 导出CSV文件
//        File csvFile = CSVUtils.createCSVFile(headList, dataList, downloadFilePath, fileName);
//        return csvFile;
////    }
//
//

    @Test
    public void createCSVFile3(){
        //1.获取到实体的bean类信息0
        List<DisciplineStudentInfo> stuList = new ArrayList<>(10);
       for(int i=0; i < 10; i++) {
           DisciplineStudentInfo disciplineStudentInfo = new DisciplineStudentInfo();
           disciplineStudentInfo.setExamineeName("测试学生"+i);
           disciplineStudentInfo.setSex("1");
           disciplineStudentInfo.setExamNum("51012219980356"+i+"\t");
           //暂时先当做逻辑考场号来用
           disciplineStudentInfo.setRecordNum("102451362"+"\t");
           disciplineStudentInfo.setSubjectCode("3"+"\t");
           disciplineStudentInfo.setAuthentication(1);
           disciplineStudentInfo.setConfirmAbsentStatus(1);
           disciplineStudentInfo.setOrgCode("86.32.01");
           stuList.add(disciplineStudentInfo);
       }

        // 设置表格头
        Object[] head = {"姓名", "性别", "准考证号", "逻辑考场号", "科目类别", "身份验证缺考", "最终缺考"};
        List<Object> headList = Arrays.asList(head);


        // 设置数据
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList;
        for (int i = 0; i < stuList.size(); i++) {
            rowList = new ArrayList<>();
            DisciplineStudentInfo disciplineStudentInfo = stuList.get(i);
            //rowList.add(i + 1);
            rowList.add(disciplineStudentInfo.getExamineeName());
            boolean flag = i%2 == 0 ? true : false;
            if(flag){
                rowList.add("男");
            }else{
                rowList.add("女");
            }
            rowList.add(disciplineStudentInfo.getExamNum());
            rowList.add(disciplineStudentInfo.getRecordNum());
            rowList.add(disciplineStudentInfo.getSubjectCode());
//            rowList.add(disciplineStudentInfo.getConfirmAbsentStatus());
//            rowList.add(disciplineStudentInfo.getAuthentication());
            rowList.add("缺考");
            rowList.add("缺考");
            dataList.add(rowList);
        }

        // 导出文件路径
//        String downloadFilePath = "C:" + File.separator + "cap4j" + File.separator + "download" + File.separator;
        String downloadFilePath = "D:\\multipose_test";
        // 导出文件名称
        String datetimeStr = DateFormatKit.convert( "yyyyMMddHHmmss",new Date());
        String fileName = "测试列表_" + datetimeStr;
        // 导出CSV文件
        createCSVFile2(headList, dataList, downloadFilePath, fileName);
        System.out.println("******************************************csv文件创建完成******************************************");
    }



}
