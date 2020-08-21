package com.l2c.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;



@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	loginDao cc=new loginDao();
	

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			try {
				
				
				
				
				
				
				String theCommand=request.getParameter("command");
				
				if(theCommand==null) {
					theCommand="LOGIN";
				}
				
				switch(theCommand) {
				
				case "LOGIN":
					loginAdmin(request,response);
					break;
					
				case "REGISTER":
					registerAdmin(request,response);
					break;
				
				case "LIST":
					listStudents(request,response);
					break;
				
				case "ADD":
					addStudent(request,response);
					break;
				
				case "LOAD":
					loadStudent(request,response);
					break;
				
				case "UPDATE":
					updateStudent(request,response);
					break;
					
				case "DELETE":
					deleteStudent(request,response);
					break;
				
					
				default:
					listStudents(request,response);
					
				}
				
				
				
				
			}
			catch(Exception e) {
				throw new ServletException(e);
			}
			
	}

		private void registerAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			String id=request.getParameter("id1");
			String uname=request.getParameter("userName");
			String email=request.getParameter("emailId");
			String password1=request.getParameter("pass1");
			String password2=request.getParameter("pass2");
			
			//registerDao admReg=new registerDao(id,uname,email,password1,password2);
			
			//studentDbUtil.registerAdmin(admReg);
			
			
			
			  String url="jdbc:mysql://localhost:3306/eialid"; String mysqlUsername="root";
			  String mysqlPassword="newrootpassword";
			  
			  Class.forName("com.mysql.jdbc.Driver"); Connection
			  con=DriverManager.getConnection(url,mysqlUsername, mysqlPassword); Statement
			  st=con.createStatement();
			  st.executeUpdate("insert into register1 values("+id+",'"+uname+"','"+email+
			  "','"+password1+"')"); response.sendRedirect("login.jsp");
			 
		}

		private void loginAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception{
			String username=request.getParameter("eialid");
			String password=request.getParameter("joy");
			
			PrintWriter out=response.getWriter();
			//cd.logoutCheck(store);
			
			if(cc.check(username, password))
			{	
				
				 HttpSession session=request.getSession(); //creating session
				 session.setAttribute("uname", username); //starting session
				 listStudents(request,response);
		
		}else {
			
			RequestDispatcher req=request.getRequestDispatcher("login.jsp");
			
			out.println("Please register first! You do not have any authorized account here yet!");
			req.include(request,response);
			
			//out.println("Please register first! You do not have any authorized account here yet!");
			//response.sendRedirect("login.jsp");
			}
			
		}

		private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			//read id from the jsp page
			String theStudentId=request.getParameter("studentId");
			
			//delete the student from the database
			studentDbUtil.deleteStudent(theStudentId);
			
			//print the updated list
			listStudents(request,response);
		}

		private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			//read data from update form
			int id=Integer.parseInt(request.getParameter("studentId"));
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");
			String email=request.getParameter("email");
			
			//create a student object with the upper info
			Student theStudent=new Student(id,firstName,lastName,email);
			
			//pass this object to studentDbUtil
			studentDbUtil.updateStudent(theStudent);
			
			//list all the students
			listStudents(request,response);
			
		}

		private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
			// read the student id
			String theStudentId=request.getParameter("studentId");
			
			//get the student from database
			Student theStudent=studentDbUtil.getStudent(theStudentId);
			
			//set the request object for jsp
			request.setAttribute("THE_STUDENT", theStudent);
			
			//send it to jsp page
			RequestDispatcher dispatcher=request.getRequestDispatcher("update-student-form.jsp");
			dispatcher.forward(request, response);
		}
		
		private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
			// TODO Auto-generated method stub
			
			//get the parameter from add form
			String firstName=request.getParameter("firstName");
			String lastName=request.getParameter("lastName");
			String email=request.getParameter("email");
			
			//create a student object to update the student list
			
			Student theStudent=new Student(firstName,lastName,email);
			
			//add this student in the database as well
			studentDbUtil.addStudent(theStudent);
			
			//send back 
			listStudents(request,response);
			
		}

		private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			//get students from studentDbUtil
			List<Student> students=studentDbUtil.getStudents();
			
			//add students to the request
			
			request.setAttribute("STUDENT_LIST", students);
			
			
			//send it to the main jsp page or view page
			
			RequestDispatcher dispatcher=request.getRequestDispatcher("list-students.jsp");
			dispatcher.forward(request, response);
			
			
		}

		@Override
		public void init() throws ServletException {
			
			super.init();
			try {
			studentDbUtil=new StudentDbUtil(dataSource);
			 
			}
			catch (Exception e) {
				throw new ServletException(e);
			}
		}

}
