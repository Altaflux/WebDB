package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Connect;
import com.database.ConnectionInfo;
import com.database.controllers.ControllerIfc;



/**
 * Servlet implementation class Connection
 */
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

		ConnectionInfo db =  new ConnectionInfo();
		db.setDbname(null); 
		db.setDbtype(request.getParameter("dbtype"));
		db.setUsername(request.getParameter("username"));
		db.setPassword(request.getParameter("password"));
		db.setIpaddress(request.getParameter("ipaddress"));
		
		request.getSession().setAttribute("db", db);
		ConnectionInfo db2 = (ConnectionInfo) request.getSession().getAttribute("db");
		
		Connect conn = new Connect();
		ControllerIfc contr = conn.getConn(db);
		System.out.println(contr.testConnection());
		
		try {
			ArrayList<String> dbList = contr.getDbList();
			request.getSession().setAttribute("dbList", dbList);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		String nextJSP = "/html-jsp/home.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);

		
		// TODO Auto-generated method stub
	}

}
