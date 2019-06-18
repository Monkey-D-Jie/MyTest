package com.jf.mydemo.mytest.CommonDemoTest;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Wangjie
 * @Date: 2019-03-07 20:41
 * @Description: 这里是描述
 * To change this template use File | Settings | File and Templates.
 */

public class ParamData {
    private String ip;
    private Integer port;

    public ParamData(){

    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "IPData{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
