package com.database;

public class AdapterInfo {
	private String serverTypeName;
	private String controllerLocation;
	private String jdbcLocation;
	
	public AdapterInfo(){
	}
	
	public String getServerTypeName() {
		return serverTypeName;
	}
	public void setServerTypeName(String serverTypeName) {
		this.serverTypeName = serverTypeName;
	}
	public String getControllerLocation() {
		return controllerLocation;
	}
	public void setControllerLocation(String controllerLocation) {
		this.controllerLocation = controllerLocation;
	}
	public String getJdbcLocation() {
		return jdbcLocation;
	}
	public void setJdbcLocation(String jdbcLocation) {
		this.jdbcLocation = jdbcLocation;
	}
}
