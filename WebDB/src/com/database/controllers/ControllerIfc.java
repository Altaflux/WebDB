package com.database.controllers;

import java.sql.Connection;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.database.ConnectionInfo;

public interface ControllerIfc {

	public abstract ResultSetMetaData getTableMetadata(String tablename)
			throws SQLException;

	public abstract ArrayList<String> getColumns(String tablename) throws SQLException;

	public abstract void insertRow(String tablename, Vector content)
			throws SQLException;

	public abstract ArrayList<String> getTableList() throws SQLException;

	public abstract boolean isTableEditable(String tablename)
			throws SQLException;

	public abstract String getUniqueColumn(String tablename)
			throws SQLException;

	public abstract void deleteRow(String uniquecolumn, String uniquevalue,
			String tablename) throws SQLException;

	public abstract ArrayList<String> getDbList() throws SQLException;

	public abstract boolean testConnection();

	public abstract void setDb(ConnectionInfo db);

	public abstract ConnectionInfo getDb();

	public abstract Connection getConnection();

}