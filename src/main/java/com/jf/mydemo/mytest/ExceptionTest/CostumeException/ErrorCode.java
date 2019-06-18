package com.jf.mydemo.mytest.ExceptionTest.CostumeException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-06-17 16:13
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */


public enum ErrorCode {

    NULL_OBJ("LUO001","对象为空"),
    ERROR_ADD_USER("LUO002","添加用户失败"),
    UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试....");

    private String value;
    private String desc;

    private ErrorCode(String value, String desc) {
        this.setValue(value);
        this.setDesc(desc);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "[" + this.value + "]" + this.desc;
    }
}
