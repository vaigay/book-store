<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register successful</title>
</head>
<body>
<div style = "width:100%" align="center">
	<h1>Book store PTTK</h1>
	<a style = "margin:0 5px" href="<%=request.getContextPath() %>/listBookServlet">Home</a>
 	<a style = "margin:0 5px" href ="<%=request.getContextPath() %>/cart-show">Cart Show</a>
    <a style = "margin:0 5px" href ="<%=request.getContextPath() %>/bill-show">Bill Show</a>
    <a style = "margin:0 5px" href= "<%=request.getContextPath() %>/profile">Profile</a>
    <a style = "margin:0 5px" href= "<%=request.getContextPath() %>/logout">Logout</a> 
</div>
 <div align="center">
  <h1>You have registered successfully click here to continue</h1>
  <a align="center" href="home.jsp">Continue</a>
 </div>
</body>
</html>