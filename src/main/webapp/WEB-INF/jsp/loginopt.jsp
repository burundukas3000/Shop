<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<form:form action="${pageContext.request.contextPath}/login" method="GET">
    <input type="submit" value="login"  />
</form:form>
<form:form action="${pageContext.request.contextPath}/registration" method="GET">
    <input type="submit" value="register"  />
</form:form>