<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Searching Book</title>
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
 <div align = "center">
 	<h3>Welcome  <c:out value="${account.customer.name}" /></h3>
 	<p>Name: <c:out value="${account.customer.name}" /></p>
 	<p>Username: <c:out value="${account.username}" /></p>
 	<p style="display:inline; margin:0 5px">Country: <c:out value="${account.customer.address.country}" /></p>
 	<p style="display:inline; margin:0 5px">City: <c:out value="${account.customer.address.city}" /></p>
 	<p style="display:inline; margin:0 5px">Detail: <c:out value="${account.customer.address.detail}" /></p>
 </div>
</body>
</html>