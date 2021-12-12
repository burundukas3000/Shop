<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
    <title>Breed List</title>
    <link href="/css/rest.css" rel="stylesheet">
</head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>${dog.name}</h2>
                <td><img src=${dog.image.url} height="200"></td>
                <form:form action="${pageContext.request.contextPath}/rest/randombreed" method="GET">
                    <input type="submit" value="Get Another Cutie"  />
                </form:form>
            </div>
        </div>
    </body>
</html>
