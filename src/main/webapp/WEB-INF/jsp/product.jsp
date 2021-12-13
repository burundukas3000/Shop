<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Products</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>${product.title}</h2></caption>
        <tr>
            <th>Title</th>
            <th>Price</th>
            <th>Info</th>
        </tr>

            <tr>
                <td><c:out value="${product.title}" />
                    <c:if test="${userin}">Hello, ${userName}</c:if>
                    <form:form action="${pageContext.request.contextPath}/customer/purchase/${purch.id}" method="GET">
                        <input type="submit" value="${product.title}" />
                    </form:form>
                </td>
                <td>
                    <p style="${product.happyPrice > 0 ? 'text-decoration: line-through;':''}"><c:out value="${product.price}" /></p>
                    <c:if test="${product.happyPrice>0}">
                        <p style="color:red">Discount price: </p>
                        <p style="${product.loyalPrice > 0 ? 'text-decoration: line-through;':'color:red'}"><c:out value="${product.happyPrice}" /></p>
                    </c:if>
                    <c:if test="${product.loyalPrice>0}">
                        <p style="color:red">Loyal customer: </p>
                        <p style="color:red"><c:out value="${product.loyalPrice}" /></p>
                    </c:if>
                </td>
            </tr>
    </table>
    <div>
        <c:forEach var="image" items="${product.images}">
            <img src="image/${image.id}"><br>
        </c:forEach>
    </div>

</div>
</body>
</html>