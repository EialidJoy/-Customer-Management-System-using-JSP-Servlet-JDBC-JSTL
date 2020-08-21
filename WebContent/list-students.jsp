<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Student Tracker App</title>

<link type="text/css" rel="stylesheet" href="css/style.css">
</head>

	<%-- <%
	
		List<Student> theStudents=(List<Student>)request.getAttribute("STUDENT_LIST");
	
	
	%> --%>

<body>

	<div id="wrapper">
	
		<div id="header">
		
			<h2>BRAC University</h2>
		
		</div>
	
	</div>
	
	<div id="container">
	
		<div id="content">
		
			<!-- putting new button named as add student -->
			
			<input type="submit" value="Add Student"
				   onClick="window.location.href='add-student.jsp';return false;"
				   class="add-student-button"
			/>
		
			<table>
			
				<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
				
				</tr>
				
				<%-- <% for (Student tempStudent:theStudents){ %> --%>
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
					<c:url var="tempLink" value="StudentControllerServlet">
						<c:param name="command" value="LOAD"></c:param>
						<c:param name="studentId" value="${tempStudent.id}"></c:param>
				
					</c:url>
					<c:url var="deleteLink" value="StudentControllerServlet">
						<c:param name="command" value="DELETE"></c:param>
						<c:param name="studentId" value="${tempStudent.id}"></c:param>
				
					</c:url>
					<tr>
					
						<%-- <td> <%=tempStudent.getFirstName() %> </td>
						<td> <%=tempStudent.getLastName() %> </td>
						<td> <%=tempStudent.getEmail() %> </td> --%>
						
						<td> ${tempStudent.firstName} </td>
						<td> ${tempStudent.lastName} </td>
						<td> ${tempStudent.email} </td>
						<td> <a href="${tempLink}">Update</a> 
							|
							<a href="${deleteLink}"
							onClick="if (!(confirm('Are you sure you you want to delete the student?'))) return false">
							
							Delete</a>
						
						</td>
					
					</tr>
				</c:forEach>
				<%-- <% } %> --%>
			
			</table>
		
		</div>
	
	
	</div>
	

</body>
</html>