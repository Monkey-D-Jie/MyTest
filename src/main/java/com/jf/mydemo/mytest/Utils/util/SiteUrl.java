package com.jf.mydemo.mytest.Utils.util;

import java.io.IOException;
import java.util.Properties;

public class SiteUrl {
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(SiteUrl.class.getClassLoader().getResourceAsStream("tool.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readUrl(String key){
		return (String)properties.get(key);
	}
}
