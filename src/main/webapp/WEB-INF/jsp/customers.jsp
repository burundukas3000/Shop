<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/css/rest.css">
    <title>Products</title>
</head>
<%@ include file="header.jsp"%>
<body>
    <div align="center">
        <h2>Customers</h2>
        <form:form action="${pageContext.request.contextPath}/manager/customers/top/amount" method="GET">
            <input type="submit" value="sort by money spent"/>
        </form:form>
        <form:form action="${pageContext.request.contextPath}/manager/customers/top/freq" method="GET">
            <input type="submit" value="sort by purchase frequency"/>
        </form:form>

        <table border="1" cellpadding="5">
            <tr>
                <th>UserName</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>E-mail</th>
                <c:if test="${addInfo}">
                    <th>Money spent</th>
                    <th>Purchases made</th>
                </c:if>
                <th>Loyalty</th>
            </tr>
            <c:forEach var="c" items="${customers}" varStatus="loop">
                <tr>
                    <td>${c.userName}</td>
                    <td>${c.firstName}</td>
                    <td>${c.lastName}</td>
                    <td>${c.email}</td>
                    <c:if test="${addInfo}">
                        <td>${c.moneySpent}</td>
                        <td>${c.freq}</td>
                    </c:if>
                    <td>${c.role}</td>
            </c:forEach>
        </table>
    </div>
</body>
</html>
