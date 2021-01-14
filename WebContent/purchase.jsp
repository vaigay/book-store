<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
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
		<p style = "font-size: 20px">Thanks for your purchase: </p>
		<p>money book: <c:out value="${moneyBook }"></c:out></p>
		<p>money ship: <c:out value="${moneyShip }"></c:out></p>
		<p>total: <c:out value="${money}"></c:out></p><br>
		<a href="<%=request.getContextPath() %>/listBookServlet">Continue Shopping</a><br>
	</div>
</body>
</html>