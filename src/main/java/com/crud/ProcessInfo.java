package com.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;
/**
 * Servlet implementation class ProcessInfo
 */
@WebServlet("/ProcessInfo")
public class ProcessInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "/DisplayInfo.jsp";
		String fName = request.getParameter("fname");
		String lName = request.getParameter("lname");
		String phone = request.getParameter("phone");
		
		updateDB(fName, lName, phone);
		
//		Client client = new Client(fName, lName, phone);
//		request.setAttribute("client", client);
		getServletContext()
		.getRequestDispatcher(url)
		.forward(request, response);
	}
	
	protected void updateDB(String fName, String lName, String phone) {
		Connection con;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost/test1";
			String user = "root";
			String pw = "hamil5356667";
			con = DriverManager.getConnection(url, user, pw);
			Statement s = con.createStatement();
			String query = "INSERT INTO CUSTOMER " + 
			"(first_name, last_name, phone, cust_id) " + 
					"VALUES ('" + fName + "', '" + lName + "', '" + phone + "', NULL)";
			s.executeUpdate(query);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
