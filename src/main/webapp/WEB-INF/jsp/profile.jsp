<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/css/rest.css">
    <title>Profile</title>
</head>
<%@ include file="header.jsp"%>
<body>
</br>
<div align="center">
    </br>
   <h2>Profile</h2>
    <table border="1" cellpadding="5">
        <tr>
            <th>Username</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Purchases</th>
        </tr>

        <tr>
            <td>
                ${user.userName}
            </td>
            <td>
                ${user.firstName}
            </td>
            <td>
                ${user.lastName}
            </td>
            <td>
                <c:forEach var="purchase" items="${user.purchases}" varStatus="index">
                    <p>
                        <form:form method="GET" action="${pageContext.request.contextPath}/customer/purchase/${purchase.id}">
                            ${index.index+1} <input type="submit" value="${purchase.feDateCreated}" />
                        </form:form>
                    </p>
                </c:forEach>
            </td>
        </tr>
    </table>
</div>
</body>
</html>