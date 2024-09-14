package com.revature.bank.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	private static DBConnection db;
	
	private DBConnection() {
		
	}
	public static synchronized DBConnection getInstance() {
		if (db == null) {
			db = new DBConnection();
		}
		return db;
	}
	public Connection getConnection() {
		Connection connect = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileReader("database.properties"));
			connect = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return connect;
	}
}
