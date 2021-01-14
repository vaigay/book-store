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
 <div align="center">
  <form action="listBookServlet" method="get">
   <h3>Welcome  <c:out value="${account.customer.name}" /></h3>
   <p>Searching Book: </p>
   <input type = "text" name = "searchingbook">
   <input type = "submit" value = "Search"/>
  </form>
 </div>
 <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Books</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Author</th>
                <th>Price</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="book" items="${listBook}">
                <tr>
                    <td><c:out value="${book.id}" /></td>
                    <td><c:out value="${book.name}" /></td>
                    <td><c:out value="${book.author}" /></td>
                    <td><c:out value="${book.price}" /></td>
                    <td>
                        <a href="<%=request.getContextPath() %>/chooseItem?id=<c:out value='${book.id}'  />">Add</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;               
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>