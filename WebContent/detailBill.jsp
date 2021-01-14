<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill Detail</title>
</head>
<body>
	<div style = "width:100%" align="center">
	<h1>Book store PTTK</h1>
	<a style = "margin:0 5px" href="<%=request.getContextPath() %>/listBookServlet">Home</a>
 	<a style = "margin:0 5px" href ="<%=request.getContextPath() %>/cart-show">Cart Show</a>
    <a style = "margin:0 5px" href ="<%=request.getContextPath() %>/bill-show">Bill Show</a>
    <a style = "margin:0 5px" href= "<%=request.getContextPath() %>/profile">Profile</a>
    <a style = "margin:0 5px" href= "<%=request.getContextPath() %>/logout">Logout</a> 
</div >
	<div align="center">
    	<form action="DetailBill" method ="get">
        <table border="1" cellpadding="5">
            <caption><h2>Your Bill</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Author</th>
                <th>Price</th>
                <th>Quantity</th>
                
            </tr>
            <c:forEach var="dic" items="${lBook}">
                <tr>
                    <td><c:out value="${dic.key.id}" /></td>
                    <td><c:out value="${dic.key.name}" /></td>
                    <td><c:out value="${dic.key.author}" /></td>
                    <td><c:out value="${dic.key.price}" /></td>
                    <td><c:out value="${dic.value}" /></td>
                    
                </tr>
            </c:forEach>
        </table>
        <p>Time order: <c:out value="${bill.day}" /></p>
        <p>Shipper: <c:out value="${bill.shipper}" /></p>
        <p>Money Ship: <c:out value="${bill.shipping}" /></p>
        <p>Money Book: <c:out value="${moneyBook}" /></p>
        <p>Total: <c:out value="${money}" /></p>
        </form>
    </div>
</body>
</html>