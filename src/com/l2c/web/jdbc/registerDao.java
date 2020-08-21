package com.l2c.web.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;

public class registerDao {
	
	
	/*
	 * public void check() {
	 * 
	 * PrintWriter out=response.getWriter();
	 * 
	 * String url="jdbc:mysql://localhost:3306/eialid"; String mysqlUsername="root";
	 * String mysqlPassword="newrootpassword";
	 * 
	 * String id=request.getParameter("id1"); String
	 * uname=request.getParameter("userName"); String
	 * email=request.getParameter("emailId"); String
	 * password1=request.getParameter("pass1"); String
	 * password2=request.getParameter("pass2");
	 * 
	 * 
	 * 
	 * if(id.isEmpty() || uname.isEmpty() || email.isEmpty() || password1.isEmpty()
	 * || password2.isEmpty()) {
	 * 
	 * RequestDispatcher req=request.getRequestDispatcher("register.jsp");
	 * 
	 * out.println("Some informations are missing! Please fill it again!!!");
	 * req.include(request,response);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * else if(password1!=password2) {
	 * 
	 * RequestDispatcher req=request.getRequestDispatcher("register.jsp");
	 * 
	 * out.println("Your passwords do not match! Please fill it again!");
	 * req.include(request,response);
	 * 
	 * 
	 * 
	 * } else { Class.forName("com.mysql.jdbc.Driver"); Connection
	 * con=DriverManager.getConnection(url,mysqlUsername, mysqlPassword); Statement
	 * st=con.createStatement();
	 * st.executeUpdate("insert into register1 values("+id+",'"+uname+"','"+email+
	 * "','"+password1+"')"); response.sendRedirect("login.jsp"); } }
	 */
	private String id=null;
	private String username=null;
	private String email=null;
	private String pass1=null;
	private String pass2=null;
	
	public registerDao(String id, String username, String email, String pass1, String pass2) {
		this.id=id;
		this.username=username;
		this.email=email;
		this.pass1=pass1;
		this.pass1=pass2;
				
		
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPass1() {
		return pass1;
	}

	public void setPass1(String pass1) {
		this.pass1 = pass1;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	@Override
	public String toString() {
		return "registerDao [username=" + username + ", email=" + email + ", pass1=" + pass1 + ", pass2=" + pass2 + "]";
	}
	
	
	

}
