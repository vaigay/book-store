<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Bill</title>
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
        <table border="1" cellpadding="5">
            <caption><h2>List of Bill</h2></caption>
            <tr>
                <th>ID</th>
                <th>Time order</th>
                <th>View</th>
            </tr>
            <c:forEach var="bill" items="${listBill}">
                <tr>
                    <td><c:out value="${bill.id}" /></td>
                    <td><c:out value="${bill.day}" /></td>
                    <td>
                        <a href="<%=request.getContextPath() %>/DetailBill?id=<c:out value='${bill.id_cart}'  />">Detail</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;               
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>