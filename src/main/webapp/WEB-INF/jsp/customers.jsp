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
        <caption><h2>List of users</h2></caption>
        <tr>
            <th>ID</th>
            <th>UserName</th>
            <th>Email</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td><c:out value="${customer.id}" /></td>
                <td><c:out value="${customer.userName}" /></td>
                <td><c:out value="${customer.email}" /></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>