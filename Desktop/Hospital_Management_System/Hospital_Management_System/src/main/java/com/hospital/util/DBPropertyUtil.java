package com.hospital.util;

import java.io.InputStream;
import java.util.Properties;

public class DBPropertyUtil {

	public static String getConnectionString(String fileName) {
		String url = "";
		String username = "";
		String password = "";
		
		try {
			InputStream is = DBPropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
			Properties property = new Properties();
			property.load(is);
			url = property.getProperty("db.url");
			username = property.getProperty("db.username");
			password = property.getProperty("db.password");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return url + "," + username + "," + password;
	}
}
