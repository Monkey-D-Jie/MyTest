package com.jf.mydemo.mytest.TransientTest;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: wjie
 * @date: 2018/2/27 0027
 * @time: 18:40
 * To change this template use File | Settings | File and Templates.
 */

public class UserInfo implements Serializable{
    private static final long serialVersionUID = 996890129747019948L;
    private  static String name;
    private transient String psw;

    public UserInfo(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserInfo.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String toString() {
        return "name=" + name + ", psw=" + psw;
    }
}
