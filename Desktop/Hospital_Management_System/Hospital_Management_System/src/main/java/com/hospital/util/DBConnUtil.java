package com.hospital.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnUtil {

	public static Connection getConnection() {
		
		Connection con = null;
		String url = "";
		String username = "";
		String password = "";
		
		try {
			 String ConnStr = DBPropertyUtil.getConnectionString("db.properties");
			 String[] parts = ConnStr.split(",");
			 url = parts[0];
			 username = parts[1];
			 password = parts[2];
			 con = DriverManager.getConnection(url,username,password);
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
