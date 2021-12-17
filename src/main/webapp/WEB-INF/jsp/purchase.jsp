<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Purchase</title>
</head>
<%@ include file="header.jsp"%>
<body>
</br>
<div align="center">
    </br>
    <h2>Purchase</h2>
    <table border="1" cellpadding="5">

        <tr>
            <th>Date created</th>
            <th>Status</th>
            <th>Products</th>
        </tr>

        <tr>
            <td>
                ${purchase.dateCreated}
            </td>
            <td>
                ${purchase.status}
            </td>
            <td>
                <c:forEach var="product" items="${purchase.products}">
                    <div>
                        <form:form method="GET" action="${pageContext.request.contextPath}/product/${product.id}">
                            <input type="submit" value="${product.title}" />
                        </form:form>
                        <img width="100" height="100" src="${pageContext.request.contextPath}/image/${product.images[0].id}">
                    </div>
                </c:forEach>
            </td>
        </tr>
    </table>
</div>
</body>
</html>