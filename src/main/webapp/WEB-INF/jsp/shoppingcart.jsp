<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ page isELIgnored = "false" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart</title>
</head>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>Shopping Cart</h2></caption>
        <tr>
            <th></th>
            <th>Product</th>
            <th>Details</th>
            <th></th>
            <th style="color: red">Estimated total</th>
        </tr>
        <c:forEach var="item" items="${cartItems}">
            <tr>
                <td><i class="fa fa-trash"></i></td>
                <td>
                    <a href="${pageContext.request.contextPath}/product/${item.product.id}" target="_blank">
                    <c:out value="${item.product.title}" />
                <td>
                    <p>Quantity: <input type="number" value="${item.quantity}"></p>
                    <p>Price: <c:out value="${item.product.loyalPrice>0 ? item.product.loyalPrice : item.product.happyPrice>0? item.product.happyPrice : item.product.price}" /></p>
                    <p>Total: <c:out value="${item.quantity*item.product.loyalPrice>0 ? item.product.loyalPrice : item.product.happyPrice>0? item.product.happyPrice : item.product.price}" /></p>
                </td>
                <td>
                    <img src="image/${item.product.images[0].id}" height="150"><br>
                </td>
                <td>${item.product.price*2}</td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <p>${cartItems.get(0).product.price*100}</p>
                <p><button type="button" class="btn btn-danger">Check out</button></p>
            </td>

        </tr>
    </table>
</div>
</body>
</html>