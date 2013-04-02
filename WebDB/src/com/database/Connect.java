package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import com.database.AdapterInfo;
import com.database.controllers.*;
import java.util.List;

public class Connect {
	
	ControllerIfc conn;
	ConnectionInfo db;
	
//	public Connect(ConnectionInfo db){
//		
//		this.db = db;
//		
//		if(db.getDbtype().equalsIgnoreCase("mssql")){
//			ControllerIfc conn = new Mssql(db);
//			System.out.println(conn.testConnection());
//		}
//	}
	
	public void setDb(ConnectionInfo db){
		this.db = db;
		
	}
	
	public ConnectionInfo getDb(){
		return db;
	}
	
	public ControllerIfc getConn(ConnectionInfo db){
		AdapterInfo adapter = null;
		XmlControllerReader read = new  XmlControllerReader();
		List<AdapterInfo> adapterList = read.readConfig("Config.xml");
		
		for(int i =0; i< adapterList.size();i++){
			if(adapterList.get(i).getServerTypeName().equalsIgnoreCase(db.getDbtype())){
				adapter = adapterList.get(i);
			}
		}
		
		try {
			Class clazz = Class.forName(adapter.getControllerLocation());
			conn = (ControllerIfc) clazz.newInstance();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		if(db.getDbtype().equalsIgnoreCase("mssql")){
//			conn = new Mssql(db);
//			System.out.println(conn.testConnection());
//		}
		conn.setDb(db);
		return conn;
		
	}

}
