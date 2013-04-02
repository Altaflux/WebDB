package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.Connect;
import com.database.ConnectionInfo;
import com.database.controllers.ControllerIfc;
import com.google.gson.Gson;

/**
 * Servlet implementation class ColumnRetreive
 */
public class ColumnRetreive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ColumnRetreive() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnectionInfo db = (ConnectionInfo) request.getSession().getAttribute("db");
		Connect conn = new Connect();

		String database= (String) request.getParameter("database");
		database = database.substring(2);
		db.setDbname(database);
		ControllerIfc contr = conn.getConn(db);
		
		String tablename= (String) request.getParameter("tablename");
		tablename = tablename.substring(2);
		
		String json = null;
		
		try {
			List<String> tableList= contr.getColumns(tablename);
			json = new Gson().toJson(tableList);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
