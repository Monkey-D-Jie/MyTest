package com.jf.mydemo.mytest.FileTest;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author x
 * 判断参数的不为空的方法
 * 1.方法isNotBlank() 判断你的参数是否为空，为空就显示你后面写的字符串
 * 2.orgCodeIsNotBlank() 封装orgcode参数不为空的判断
 * 3.tokenIsNotBlank() 封装token参数不为空的判断
 */
public class ParamUtil {

    public static<T> void isNotBlank(T arg ,String exceptionInfo) {
        if(StringUtils.isEmpty(arg.toString())){
            throw new ServiceException(exceptionInfo);
        }
    }
    public static void orgCodeIsNotBlank(String orgCode){
        if(StringUtils.isEmpty(orgCode)){
            throw new ServiceException("orgCode不能为空");
        }
    }
    public static void tokenIsNotBlank(String token){
        if(StringUtils.isEmpty(token)){
            throw new ServiceException("token不能为空");
        }
    }

    /**
     *名称判定
     */
    public static void nameIsLegal(String name){
        isNotBlank(name,"名称参数不能为空");
        if(name.length() > 128){
            throw new ServiceException("名称参数长度已超过128位");
        }
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(name);
        if(m.find()){
            throw new ServiceException("课堂名称包含特殊字符");
        }

    }
}

















