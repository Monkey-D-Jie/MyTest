package com.jf.mydemo.mytest.Utils.uploadFileUtils.officeUtils.txtFiledeDecodeUtil;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2018-08-30 19:43
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class EncodingDetect {

    /**
     * 得到文件的编码
     * @param file j解析文件
     * @return 文件的编码
     */
    public static String getJavaEncode(File file){
        BytesEncodingDetect s = new BytesEncodingDetect();
        String fileCode = BytesEncodingDetect.javaname[s.detectEncoding(file)];
        return fileCode;
    }

    public static void readFile(String file, String code) {

        BufferedReader fr;
        try {
            String myCode = code!=null&&!"".equals(code) ? code : "UTF8";
            InputStreamReader read = new InputStreamReader(new FileInputStream(
                    file), myCode);

            fr = new BufferedReader(read);
            String line = null;
            int flag=1;
            // 读取每一行，如果结束了，line会为空
            while ((line = fr.readLine()) != null && line.trim().length() > 0) {
                if(flag==1) {
                    line=line.substring(1);//去掉文件头
                    flag++;
                }
                // 每一行创建一个Student对象，并存入数组中
                System.out.println(line);
            }
            fr.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
