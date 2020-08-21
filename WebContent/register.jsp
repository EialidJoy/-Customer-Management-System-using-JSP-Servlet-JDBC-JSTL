<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<form action="StudentControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="REGISTER" />
	
	ID: <input type="number" name="id1"	><br><br>
	Username: <input type="text" name="userName"><br><br>
	Email ID: <input type="email" name="emailId"><br><br>
	Password: <input type="password" name="pass1"><br><br>
	Repeat Password: <input type="password" name="pass2"><br><br>
	<input type="submit" value="Register">
	
	
	
	</form>

</body>
</html>