package com.l2c.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class loginDao {
	
	
	String sql="select * from register1 where username=? and pass=?";
	
	
	
	
	String url="jdbc:mysql://localhost:3306/eialid"; 
	String mysqlUsername="root";
	String mysqlPassword="newrootpassword";
	
	public boolean check(String uname, String pass) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(url,mysqlUsername, mysqlPassword);
			PreparedStatement st=con.prepareStatement(sql);
			
			st.setString(1, uname);
			st.setString(2, pass);
			
			ResultSet rs=st.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			
			 st.close();
			 con.close();
			 			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		
		return false;
		
	}
	
	
	
	
}