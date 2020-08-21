package com.l2c.web.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	// List<Student> students=new ArrayList<>();

	// get the whole list of students
	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			// get the connection stablished

			myConn = dataSource.getConnection();

			// create the sql and statement

			String sql = "select * from student order by last_name";

			myStmt = myConn.createStatement();

			// execute query

			myRs = myStmt.executeQuery(sql);

			// process the results

			while (myRs.next()) {

				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");

				Student tempStudent = new Student(id, firstName, lastName, email);

				students.add(tempStudent);
			}

			return students;
		}

		finally {

			// close the jdbc objects means closing of myConn, myStmt, myRs

			close(myConn, myStmt, myRs);

		}

	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub

		try {

			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addStudent(Student theStudent) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		// get the connection
		try {
			myConn = dataSource.getConnection();

			// create the sql for insertion

			String sql = "insert into student " + "(first_name, last_name, email) " + "values (?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);

			// set the parameter values

			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());

			// execute the query

			myStmt.execute();

		} finally {
			close(myConn, myStmt, null);
		}

	}

	public Student getStudent(String theStudentId) throws Exception {
		Student theStudent = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;

		try {

			// convert studentid to integer
			studentId = Integer.parseInt(theStudentId);

			// get the connection
			myConn = dataSource.getConnection();

			// create the sql to get selected students using id
			String sql = "select * from student where id=?";

			// create prepared statement
			myStmt = myConn.prepareStatement(sql);

			// set parameter of the sql
			myStmt.setInt(1, studentId);

			// execute the query
			myRs = myStmt.executeQuery();

			// retrieve student row of the selected id
			if (myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				theStudent = new Student(studentId, firstName, lastName, email);

			} else {
				throw new Exception("could not find student id : " + studentId);
			}

			return theStudent;

		} finally {
			// close all the jdbc connections
			close(myConn, myStmt, myRs);

		}
	}

	public void updateStudent(Student theStudent) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			// get a connection
			myConn = dataSource.getConnection();

			// create sql
			String sql = "update student " + "set first_name=?, last_name=?, email=? " + "where id=?";

			// create statement
			myStmt = myConn.prepareStatement(sql);

			// set the parameter
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			// myStmt.setInt(4, theStudent.getId());
			myStmt.setInt(4, theStudent.getId());
			// execute the query
			myStmt.execute();

		} finally {
			// close all the jdbc connection objects
			close(myConn, myStmt, null);

		}

	}

	public void deleteStudent(String theStudentId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {

			// convert the string id into integer
			int studentId = Integer.parseInt(theStudentId);

			// get the connection
			myConn = dataSource.getConnection();

			// create the sql
			String sql = "delete from student where id=?";

			// prepare the statement
			myStmt = myConn.prepareStatement(sql);

			// set the params
			myStmt.setInt(1, studentId);

			// execute the query
			myStmt.execute();

		} finally {
			// close all the jdbc connections
			close(myConn, myStmt, null);

		}
	}

	/*
	 * public void registerAdmin(registerDao admReg) throws Exception{ String
	 * url="jdbc:mysql://localhost:3306/eialid"; String mysqlUsername="root"; String
	 * mysqlPassword="newrootpassword"; Connection con=null; PreparedStatement
	 * st=null;
	 * 
	 * try { Class.forName("com.mysql.jdbc.Driver");
	 * con=DriverManager.getConnection(url,mysqlUsername, mysqlPassword);
	 * 
	 * String sql="insert into register1 " + ("id, username, email, pass ") +
	 * " values (?,?,?,?)";
	 * 
	 * st=con.prepareStatement(sql);
	 * 
	 * st.executeUpdate("insert into register1 values("+registerDao.getId()+",'"+
	 * uname+"','"+email+ "','"+password1+"')"); response.sendRedirect("login.jsp");
	 * 
	 * st.setString(1, admReg.getId()); st.setString(2, admReg.getUsername());
	 * st.setString(3, admReg.getEmail()); st.setString(4, admReg.getPass1());
	 * 
	 * st.execute();
	 * 
	 * } finally { close(con,st,null); } }
	 */
}
