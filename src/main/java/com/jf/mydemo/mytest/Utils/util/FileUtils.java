package com.jf.mydemo.mytest.Utils.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileUtils {
	
	
	private static Map<String, Integer>fileCategory;
	

	public  static void initFileCategory(){
		fileCategory=new HashMap<>();
		 //图片
		fileCategory.put("jpg", 100);
		fileCategory.put("png", 100);
		 
		 
		 //文档
		fileCategory.put("txt", 200);
		fileCategory.put("docx", 200);
		fileCategory.put("doc", 200);
		fileCategory.put("xls", 200);
		fileCategory.put("xlsx", 200);
		fileCategory.put("pdf", 200);
		fileCategory.put("zip", 200);
		fileCategory.put("rar", 200);
		 
		 //视频
		fileCategory.put("avi", 300);
		 
		 
		 //音乐
		fileCategory.put("mp3", 400);
		 
		 //其它 500
		 //返回 null
		
		
		
	}
	
	public static Integer getFileCategory(String fileSufix){
		if(fileCategory==null)
			initFileCategory();
		Integer r= fileCategory.get(fileSufix);
		if(r==null){
			r=-100;
		}
		return r;
	}
	
	public static String getFilePrefix(String fileName){
		int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
	}
	
	public static String getFileSufix(String fileName){
		int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
	}
	
	public static void copyFile(String inputFile,String outputFile) throws FileNotFoundException{
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;  
        try {  
			while ((temp = fis.read()) != -1) {  
			    fos.write(temp);  
			}
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally{
            try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } 
	}
	public static void main(String[] args) {
		String s ="dsdsdsd.rar";
		System.out.println(getFileSufix(s));
	}
}
