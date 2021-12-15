<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="js.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="icon" type="image/x-icon" href="/images/Icon.png" >
</head>
<sec:authorize access="hasAnyRole('ADMIN', 'CUSTOMER','LYCUSTOMER')" var="isAuthenticated"/>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="">KukuDuku</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="nav navbar-nav navbar-left">
            <li class="nav-item active"><a class="nav-link" href="home">Home <span class="sr-only">(current)</span></a></li>
            <li class="nav-item"><a class="nav-link" href="shop">Shop</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/products/category/clothes">Clothes</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/products/category/toys">Toys</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/products/category/accessories">Accessories</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/registration">Registration</a></li>
            <li class="nav-item"><a class="nav-link" href="contact">Contacts</a></li>
            <li class="nav-item"><a class="nav-link" href="about">About</a></li>
            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/cart">Cart</a></li>
            <c:if test="${isAuthenticated}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/logout"><i class="glyphicon glyphicon-log-in"></i>Logout</a></li>
            </c:if>
            <c:if test="${!isAuthenticated}">
                <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login"><i class="glyphicon glyphicon-log-in"></i>Login</a></li>
            </c:if>        </ul>

    </div>
</nav>
