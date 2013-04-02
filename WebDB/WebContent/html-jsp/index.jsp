<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.database.*" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="db" class="com.database.ConnectionInfo" scope="request"/>


<% 
	request.setAttribute("db",db);
	XmlControllerReader read = new  XmlControllerReader();
	List<AdapterInfo> adapterList = read.readConfig("Config.xml");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<BODY><p align="center">
Inserte los datos de el servidor</P><br><br><br><div align="center">
<FORM METHOD=POST ACTION="Connection">
<table>
<tr><td>Username</td><td><INPUT TYPE=TEXT NAME=username SIZE=20></td></tr>
<tr><td>Password</td><td><INPUT TYPE=PASSWORD NAME=password SIZE=20></td></tr>
<tr><td>ServerType</td><td><select NAME=dbtype>

<%
	for(int i=0;i<adapterList.size();i++){
		%><option value="<%=adapterList.get(i).getServerTypeName()%>"><%=adapterList.get(i).getServerTypeName()%></option>
	<%}
%>

</select></td></tr>
<tr><td>Ip Address</td><td><INPUT TYPE=TEXT NAME=ipaddress SIZE=20></td></tr>

</table>
<INPUT TYPE=SUBMIT value=Conectarse>
</FORM>
</div>
<%-- <jsp:include page="hello.jsp"/> --%>
</BODY>
</HTML>