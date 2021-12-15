<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored = "false" %>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cart</title>
</head>
<%@ include file="header.jsp"%>
<div align="center">
    </br>
    <h2>Shopping Cart</h2></br>
    <c:if test="${cartItems.size()==0}">
        Your cart is empty
    </c:if>
    <c:if test="${cartItems.size()>0}">
        <table border="1" cellpadding="5">
            <tr>
                <th></th>
                <th>Product</th>
                <th>Details</th>
                <th></th>
                <th style="color: red">Estimated total</th>
            </tr>
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td>
                        <form:form action="${pageContext.request.contextPath}/cart/deleteproduct/${item.product.id}" method="POST">
                            <input type="submit" value="delete"/>
                        </form:form>
                    <td>
                        <a href="${pageContext.request.contextPath}/product/${item.product.id}" target="_blank">
                        <c:out value="${item.product.title}" />
                    <td>
                        <p>Quantity: <input type="number" value="${item.quantity}" id="'qnt'+${item.product.id}" class="form-control"/></p>

                        <c:set var="finalPrice" value="${item.product.loyalPrice>0 ? item.product.loyalPrice : item.product.happyPrice>0? item.product.happyPrice : item.product.price}"/>
                        <c:set var="totalPrice" value="${totalPrice+(finalPrice*item.quantity)}" />
                        <p>Price: <c:out value="${finalPrice}" /></p>
                    </td>
                    <td>
                        <img src="${pageContext.request.contextPath}/image/${item.product.images[0].id}" height="150"><br>
                    </td>
                    <td>
                        <p><c:out value="${item.quantity*finalPrice}" /></p>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <p style="color:red">${totalPrice}</p>
                    <p><button type="button" onclick="handleClick()" id="addToCartButton" >Check out</button></p>
                </td>

            </tr>
        </table>
</c:if>
</div>
</body>
</html>