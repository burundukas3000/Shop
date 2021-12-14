<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP List Users Records</title>
</head>
<body>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Shopping Cart</h2></caption>
        <tr>
            <th>Product</th>
            <th>Quantity</th>
        </tr>
        <c:forEach var="item" items="${cartItems}">
            <tr>
                <td><c:out value="${item.product.title}" /></td>
                <td><c:out value="${item.quantity}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>