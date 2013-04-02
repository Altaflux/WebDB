package com.database.controllers;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

import com.database.ConnectionInfo;



public class Mysql {
	
	public Connection conn;
	
	public  Connection CreateConnection(ConnectionInfo db) {
		try {
			String url = "jdbc:mysql://"+db.getIpaddress()+":3306/";
			String dbName = db.getDbname();
			String driver = "com.mysql.jdbc.Driver";
			String userName = db.getUsername(); 
			String password = db.getPassword();

			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
			System.out.println("Connected to the com.database");

		} catch (Exception e) {
			e.printStackTrace();
			//Error err = new Error(e.toString());
		}
		
		return conn;
	}

}
