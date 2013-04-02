package com.database.controllers;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import com.database.ConnectionInfo;



public class Mssql implements ControllerIfc {
	
	private ConnectionInfo db;
	

	public Mssql(){
		
	}

	
	private Connection createConnection(){
		Connection conn =null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			String url= "jdbc:sqlserver://"+db.getIpaddress();
			if(db.getDbname()!=null){
				url=url+";DatabaseName="+db.getDbname();
			}
			System.out.println(url);
			conn = DriverManager.getConnection(url,
					db.getUsername(), db.getPassword());
			System.out.println("connected");
			

		} catch (Exception e) {
			e.printStackTrace();
			//Error err = new Error(e.toString());
		}
		return conn;
	}
	
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#getTableMetadata(java.lang.String)
	 */
	public ResultSetMetaData getTableMetadata(String tablename) throws SQLException{
		Connection conn = createConnection();
		ResultSetMetaData met = null;
		try{
			ResultSet rs = null;
			Statement st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM  ["+tablename+"]");
			met = rs.getMetaData();
			System.out.println(met.getColumnCount());

		}catch (SQLException s){
			System.out.println("SQL code does not execute3." +s);
			Error err = new Error(s.toString());
			conn.close();
		}
		//conn.close();
		return met;
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#getColumns(java.lang.String)
	 */
	public ArrayList<String> getColumns(String tablename) throws SQLException{
		//String columnnames[];
//		ArrayList columnnames = new ArrayList();
//		ResultSetMetaData m = getTableMetadata(tablename);
		
		Connection conn = createConnection();
		ResultSet rs = null;
		
		ArrayList<String> columnnames = new ArrayList<String>();
		Statement s = conn.createStatement();
		rs = s.executeQuery("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"+tablename+"'");
		while (rs.next()) {
			columnnames.add(rs.getString(1));
		}
		conn.close();
		
		
		//columnnames = new String[m.getColumnCount()];
		//System.out.println(columnnames.toString());
//		System.out.println(m.getColumnCount());
//		for (int i = 0;i<m.getColumnCount();i++){
//			columnnames.add(m.getColumnName(i+1));
//		}
		return columnnames;
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#insertRow(java.lang.String, java.util.Vector)
	 */
	public void insertRow(String tablename, Vector content) throws SQLException{
		Connection conn = createConnection();
		//String columns[] = getColumns(tablename);
		ArrayList<String> columns = getColumns(tablename);
		
		String query = "INSERT INTO "+"["+tablename+"]"+" (";

		for (int i=0;i<columns.size();i++){
			query = query +columns.get(i)+", ";
		}
		query = query.substring(0, query.length()-2)+") VALUES (";

		for (int i=0;i<columns.size();i++){
			if (((Vector) content.get(0)).get(i)==null){
				query = query + "null, ";
			}
			else{
				query = query + "'"+((Vector) content.get(0)).get(i)+ "', ";
			}
		}
		query = query.substring(0, query.length()-2)+")";
		conn.createStatement();
		Statement s = conn.createStatement();
		s.executeUpdate(query);
		conn.close();
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#getTableList()
	 */
	public ArrayList<String> getTableList() throws SQLException{
		Connection conn = createConnection();
		ResultSet rs = null;
		ArrayList<String> tablelist = new ArrayList();
		Statement s = conn.createStatement();
		rs = s.executeQuery("select TABLE_NAME FROM  INFORMATION_SCHEMA.TABLES");
		while (rs.next()) {
			tablelist.add(rs.getString(1));
		}
		conn.close();
		return tablelist;
		
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#isTableEditable(java.lang.String)
	 */
	public boolean isTableEditable(String tablename) throws SQLException{
		Connection conn = createConnection();
		Statement s = conn.createStatement();
		ResultSet rs = null;
		rs = s.executeQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS AS C JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS K ON C.TABLE_NAME = K.TABLE_NAME AND C.CONSTRAINT_CATALOG = K.CONSTRAINT_CATALOG AND C.CONSTRAINT_SCHEMA = K.CONSTRAINT_SCHEMA AND C.CONSTRAINT_NAME = K.CONSTRAINT_NAME WHERE  (C.CONSTRAINT_TYPE = 'PRIMARY KEY' or C.CONSTRAINT_TYPE ='UNIQUE') AND K.TABLE_NAME = '"+tablename+"';");
		conn.close();
		if(rs.getInt(1)>0){
			return true;
		}
		else{
			return false;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#getUniqueColumn(java.lang.String)
	 */
	public String getUniqueColumn(String tablename) throws SQLException{
		String column = null;
		if(isTableEditable(tablename)){
			Connection conn = createConnection();
			Statement s = conn.createStatement();
			ResultSet rs = null;
			String query = "SELECT K.TABLE_NAME, "+
							"K.COLUMN_NAME, "+
							"K.CONSTRAINT_NAME, "+
							"C.CONSTRAINT_TYPE "+
							"FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS AS C "+
							"JOIN INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS K "+
							"ON C.TABLE_NAME = K.TABLE_NAME "+
							"AND C.CONSTRAINT_CATALOG = K.CONSTRAINT_CATALOG "+
							"AND C.CONSTRAINT_SCHEMA = K.CONSTRAINT_SCHEMA "+
							"AND C.CONSTRAINT_NAME = K.CONSTRAINT_NAME "+
							"WHERE "+
							"(C.CONSTRAINT_TYPE = 'PRIMARY KEY' or C.CONSTRAINT_TYPE ='UNIQUE') AND K.TABLE_NAME = '"+tablename+"' "+
							"ORDER BY CONSTRAINT_TYPE;";
			rs= s.executeQuery(query);
			conn.close();
			column = rs.getString(2);
			return column;
		}
		else{
			return column;
		}

	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#deleteRow(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void deleteRow(String uniquecolumn, String uniquevalue, String tablename) throws SQLException{
		Connection conn = createConnection();
		Statement s = conn.createStatement();
		
		String query = "DELETE FROM "+"["+tablename+"]"+
						"WHERE "+uniquecolumn+" = "+"'"+uniquevalue+"';";
		
		s.executeUpdate(query);
		conn.close();
	}
	
//	public void editRow(String uniquecolumn, String uniquevalue, String tablename) throws SQLException{
//		Connection conn = createConnection();
//		Statement s = conn.createStatement();
//		
//		String query = "UPDATE "+"["+tablename+"] "+
//		"SET"
//	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#getDbList()
	 */
	public ArrayList<String> getDbList() throws SQLException{
		ArrayList<String> databases = new ArrayList();
		Connection conn = createConnection();
		Statement s = conn.createStatement();
		ResultSet rs = null;
		rs = s.executeQuery("EXEC sp_databases");
		
		while(rs.next()){
			databases.add(rs.getString(1));
		}
		conn.close();
		return databases;
		
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#testConnection()
	 */
	public boolean testConnection(){
		Connection conn = createConnection();
		boolean success = true;
		try {
			Statement s = conn.createStatement();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			success = false;
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return success;
			}
			return success;
		}
		return success;
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#setDb(com.database.ConnectionInfo)
	 */
	public void setDb(ConnectionInfo db) {
		this.db = db;
	}

	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#getDb()
	 */
	public ConnectionInfo getDb() {
		return db;
	}
	
	/* (non-Javadoc)
	 * @see com.database.controllers.ControllerIfc#getConnection()
	 */
	public Connection getConnection(){
		Connection conn = createConnection();
		return conn;
	}

}
