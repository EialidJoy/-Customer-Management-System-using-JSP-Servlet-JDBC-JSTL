<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Log In</title>
</head>
<body>

<form action="StudentControllerServlet" method="GET">
		
			<input type="hidden" name="command" value="LOGIN" />


Enter Username : <input type="text" name="eialid"><br>
Enter Password : <input type="password" name="joy"><br>
				 <input type="submit" value="login"> 
				 <a href="register.jsp">Register</a>
				 

</form>

</body>
</html>