package com.jf.mydemo.mytest.Utils.util;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.*;

public class Email extends Thread{

	
	private String username; // 发件人邮箱账号
	private String password; // 发件人账号密码
	private String hostName; // 邮件服务器域名
	private int smtpPort;        // 邮件服务器smtp协议端口
	private boolean isOpenSsl; // 是否启用SSL
	private String sslPort; // 若启用，设置smtp协议的SSL端口号
	private String subject; // 邮件主题
	private String msg;    // 正文

	private String receivers ; //接收者
	
   /*
      
       SMTP服务器地址:smtp.sina.com.cn（端口：25）  
       SMTP服务器:smtp.vip.sina.com （端口：25）
       SMTP服务器地址:smtp.sohu.com（端口：25）
      SMTP服务器地址:smtp.126.com（端口：25）
      SMTP服务器地址：SMTP.139.com(端口：25)
      SMTP服务器地址:smtp.163.com（端口：25）
      SMTP服务器地址：smtp.qq.com（SSL启用 端口：465）
      SMTP服务器地址:smtp.gmail.com（SSL启用 端口：587）
      SMTP服务器地址:SMTP.foxmail.com（端口：25）
    */
	
	private static Map<String, Integer>host=new HashMap<String, Integer>();
	static
	{
//		host.put("@sina.com.cn", 25);
//		host.put("@vip.sina.com", 25);
//		host.put("@sohu.com", 25);
//		host.put("@126.com", 25);
//		host.put("@139.com", 25);
		host.put("@qq.com", 465);
		host.put("@gmail.com",587);
	}
	
	
	public Email() {
		super();
		this.username="1170881778@qq.com";
		this.password="uuqnynsefyxbhejh";
		this.smtpPort=25;
		this.isOpenSsl=false;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public boolean isOpenSsl() {
		return isOpenSsl;
	}
	public void setOpenSsl(boolean isOpenSsl) {
		this.isOpenSsl = isOpenSsl;
	}
	public String getSslPort() {
		return sslPort;
	}
	public void setSslPort(String sslPort) {
		this.sslPort = sslPort;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getReceivers() {
		return receivers;
	}
	public void setReceivers(String receivers) {
		this.receivers = receivers;
	}
	@Override
	public void run(){

		HtmlEmail email = new HtmlEmail(); 
		ready();
		  // 字符编码集的设置  
        email.setCharset("gbk");
		email.setHostName(hostName); // 邮件服务器域名
		email.setSmtpPort(smtpPort); // 邮件服务器smtp协议端口
		email.setAuthentication(username, password); // 发送者邮箱账户
		email.setSSLOnConnect(isOpenSsl); // 是否启用SSL
	
		try {
			if(isOpenSsl)
			{
				email.setSslSmtpPort(sslPort); // 若启用，设置smtp协议的SSL端口号
			}
			email.setFrom(username); // 发件人地址
			email.setSubject(subject); // 邮件主题
			email.setMsg(msg); // 邮件正文
			email.addTo(receivers); // 收件人地址，可以设置多个	
			email.send();// 发送邮件
		} catch (Exception e) {
		
			e.printStackTrace();
		} 
		
	}
		
	private void ready() {
		this.hostName="smtp." + receivers.substring(receivers.indexOf("@") + 1, receivers.length()); // 邮件服务器域名
		Object object=host.get(hostName);
		if(object!=null)
		{
			sslPort=object.toString();
			isOpenSsl=true;
		}
		
	}
	
	 public static void main(String[] args) {
		 Email maill = new Email();

		maill.setSubject("1");
		maill.setMsg("1");
		maill.setReceivers("1170881778@qq.com");
	    maill.run();
		
		 
	}
	
}
