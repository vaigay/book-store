<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
  <h1>Login Form</h1>
  <form action="login" method="post">
   <table style="with: 100%">
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" required /></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" /></td>
    </tr>

   </table>
   <input type="submit" value="Login" />
	<a href = "<%=request.getContextPath()%>/register" > Register</a>
  </form>
  
</body>
</html>