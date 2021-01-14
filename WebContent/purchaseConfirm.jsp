<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Confirm purchase</title>
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
	<form action = "confirm" method = "get">
		<p>Address Detail: <input type ="text" name = "detail" required="required"><br></p>
		<p>Select Shipper:<br>
			<br>
			<input type="radio" id="FastShip" name="shipper" value="FastShip">
  			<label for="FastShip">FastShip</label><br>
  			<input type="radio" id="JSShip" name="shipper" value="JSShip" required ="">
  			<label for="JSShip">JSShip</label><br>
  			<br>
  		</p>
	<div >
        <table border="1" cellpadding="5">
            <caption><h2><c:out value="${account.customer.name}" />'s Cart</h2></caption>
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
        <c:if test="${lBook.size() gt 0}">
        	<br>
        	<div>
        		<p style="margin: 0; display: inline;">Total money: </p>
        		<c:out value="${Cart.money}" />
        	</div>
        </c:if>
        
    </div>
    <br>
    <input type="submit" value="Accept" style = "margin:0 15px">
    <a href="<%=request.getContextPath() %>/listBookServlet">Back</a>
    </form>
    </div>
</body>
</html>