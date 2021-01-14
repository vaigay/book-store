<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<div >
	<h1>Book store PTTK</h1>
	<a href="login.jsp">Login</a>
</div>
  <form action="check-register" method ="post">
   <table style="with: 100%">
   	<tr>
   	 <td>Name</td>
   	 <td><input type="text" name="name" required="required" /></td>
   	</tr>
    <tr>
     <td>UserName</td>
     <td><input type="text" name="username" required="required"/></td>
    </tr>
    <tr>
     <td>Password</td>
     <td><input type="password" name="password" required="required"/></td>
    </tr>
    <tr>
     <td>Country</td>
     <td><input type="text" name="country" required="required"/></td>
    </tr>
    <tr>
     <td>City</td>
     <td><input type="text" name="city" required="required"/></td>
    </tr>
    <tr>
     <td>Detail</td>
     <td><input type="text" name="detail" required="required"/></td>
    </tr>   
   </table>
   <input type="submit" value="Register" />
  </form>
</body>
</html>