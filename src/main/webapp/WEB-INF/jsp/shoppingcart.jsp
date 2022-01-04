<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored = "false" %>

<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .error { color: red }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>Cart</title>
    <script>
        $(document).ready(function() {
            $("#quantity").change(function() {
                $.ajax({
                    url : '${pageContext.request.contextPath}/checkavailability/',
                    data : {
                        q : $("#quantity").val(),
                        id : $("#productId").val()
                    },
                    success : function(responseText) {
                        $("#errmsg").text(responseText);
                        if (responseText != "") {
                            $("#quantity").focus();
                        }else {
                            let qnt = $("#quantity").val();
                            console.log('qnt' + qnt);
                            let fin = $("#final").val();
                            console.log('fin'+ fin);
                            console.log('fin'+ (parseFloat(qnt)*parseFloat(fin)));
                            let ptot = (parseFloat(qnt)*parseFloat(fin)).toFixed(2);
                            $("#ptotal").text(ptot);
                        }
                    }
                });
            });
        });
    </script>
</head>
<div align="center">
    <%@ include file="header.jsp"%>
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
                            <form action="" method="post">
                                <input type="submit" name="upvote" value="Upvote" />
                            </form>
                    <td>
                        <a href="${pageContext.request.contextPath}/product/${item.product.id}" target="_blank">
                        <c:out value="${item.product.title}" />
                    <td>
                        <span class="error" id="errmsg"></span>
                        <p>Quantity: <input type="number" value="${item.quantity}" id="quantity" class="form-control"/></p>
                        <input type="number" id="productId" value="${item.product.id}" hidden="true"></input>

                        <c:set var="finalPrice" value="${item.product.loyalPrice>0 ? item.product.loyalPrice : item.product.happyPrice>0? item.product.happyPrice : item.product.price}"/>
                        <c:set var="totalPrice" value="${totalPrice+(finalPrice*item.quantity)}" />
                        <p>Price: <c:out value="${finalPrice}" /></p>
                    <input type="number" id="final" value="${finalPrice}" hidden="true"></input>
                    </td>
                    <td>
                        <img src="${pageContext.request.contextPath}/image/${item.product.images[0].id}" height="150"><br>
                    </td>
                    <td>
                        <p id="ptotal">${item.quantity*finalPrice}</p>
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