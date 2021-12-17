<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<head>
    <title>Breed List</title>
    <link href="/css/rest.css" rel="stylesheet">
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h1>Breeds</h1>
        </div>
    </div>
    <div id="container">
        <div id="content">
            <table>
                <tr>
                    <th>id</th>
                    <th>Name</th>
                    <th>Temperament</th>
                    <th>Life span</th>
                    <th>Wikipedia</th>
                    <th>Origin</th>
                    <th>Country code</th>
                </tr>
                <c:forEach var="breed" items="${breeds}">
                    <tr>
                        <td>${breed.id}</td>
                        <td>
                            <form:form action="${pageContext.request.contextPath}/rest/breed" method="GET">
                                <input name="theBreed" type="submit" value="${breed.name}"  />
                            </form:form>
                        </td>
                        <td>${breed.temparament}</td>
                        <td>${breed.life_span}</td>
                        <td>${breed.wikipedia_url}</td>
                        <td>${breed.origin}</td>
                        <td>${breed.country_code}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    </body>
</html>
