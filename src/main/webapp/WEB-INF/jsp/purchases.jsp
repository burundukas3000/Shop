<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>PURCHASE LIST</title>
</head>
<body>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>MONIKA - THIS IS HOW TO ITERATE THROUGH LIST AND DISPLAY IN TABLE ANY LIST</h2></caption>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Status</th>
        </tr>
        <c:forEach var="purch" items="${purchases}">
            <tr>
                <td><c:out value="${purch.id}" /></td>
                <td><c:out value="${purch.dateCreated}" /></td>
                <td><c:out value="${purch.status}" /></td>
                <form:form action="${pageContext.request.contextPath}/customer/purchase/${purch.id}" method="GET">
                    <input type="submit" value="view purchase"  />
                </form:form>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>