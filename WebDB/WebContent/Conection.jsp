<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="dbinfo" class="com.database.ConnectionInfo" scope="session"/>

<jsp:setProperty property="username" name="dbinfo" value="${param.username}"/>
<jsp:setProperty property="password" name="dbinfo" value="${param.password}"/>
<jsp:setProperty property="dbtype" name="dbinfo" value="<%= request.getParameter(\"dbtype\")%>" />
<jsp:setProperty property="ipaddress" name="dbinfo" value="<%= request.getParameter(\"ipaddress\")%>" />
<jsp:setProperty property="dbname" name="dbinfo" value="<%= request.getParameter(\"dbname\") %>"/>




<%@page import="com.database.*" %>
<%@page import="java.sql.Connection"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
try{
	Connect x = new Connect(dbinfo);
	Connection conn = x.getConn(); 
	}
	catch (Exception e) {
			e.printStackTrace();
		}
	%>
</body>
</html>