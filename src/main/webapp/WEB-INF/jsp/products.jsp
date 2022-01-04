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
            <h2>Product List in ${Products.products[0].category}</h2>
            <form:form action="${pageContext.request.contextPath}/products/category/topsold/${Products.products[0].category}" method="GET">
                <input type="submit" value="sort by top sold products"/>
            </form:form>
            <form:form action="${pageContext.request.contextPath}/products/category/bypriceasc/${Products.products[0].category}" method="GET">
                <input type="submit" value="sort by price asc"/>
            </form:form>
            <form:form method = "POST" modelAttribute="Products" action = "${pageContext.request.contextPath}/manager/products/adddiscount">
                <table border="1" cellpadding="5">
                    <tr>
                        <th>Title</th>
                        <th>Price</th>
                        <th>Info</th>
                        <sec:authorize access="hasRole('ADMIN')">
                            <th>Assign discount</th>
                        </sec:authorize>
                        <sec:authorize access="!hasRole('ADMIN')">
                            <th>Add</th>
                        </sec:authorize>
                    </tr>
                    <c:forEach var="product" items="${Products.products}" varStatus="loop">
                        <tr>
                            <td>
                                <form:form />
                                <form:form method="GET" action="${pageContext.request.contextPath}/product/${product.id}">
                                    <input type="submit" value="${product.title}" />
                                </form:form>
                            </td>
                            <td>
                                <p style="${product.happyPrice > 0 || product.loyalPrice > 0 ? 'text-decoration: line-through;':''}"><c:out value="${product.price}" /></p>
                                <c:if test="${product.happyPrice>0}">
                                    <p style="color:red">Discount price: </p>
                                    <p style="${product.loyalPrice > 0 ? 'text-decoration: line-through;':'color:red'}"><c:out value="${product.happyPrice}" /></p>
                                </c:if>
                                <c:if test="${product.loyalPrice>0}">
                                    <p style="color:red">Loyal customer: </p>
                                    <p style="color:red"><c:out value="${product.loyalPrice}" /></p>
                                </c:if>
                            </td>
                            <td>
                                <img width="100" height="100" src="image/${product.images[0].id}">
                            </td>
                            <sec:authorize access="hasRole('ADMIN')">
                                <td>
                                    <form:input path = "products[${loop.index}].id" value="${product.id}" hidden="true" />
                                    <form:input path = "products[${loop.index}].category" value="${product.category}" hidden="true" />

                                    <form:select  path="products[${loop.index}].discount.id">
                                        <form:option value="0"> --SELECT--</form:option>
                                        <c:forEach var="discount" items="${listOfDiscounts}">
                                            <form:option value="${discount.id}" title="Amount: ${discount.percentage}% Date from: ${discount.dateFrom} Date to: ${discount.dateTo}">${discount.promo}</form:option>
                                        </c:forEach>
                                    </form:select>

                                    <form:form action="${pageContext.request.contextPath}/manager/discounts/remove/${product.id}" method="POST">
                                        <input type="submit" value="delete discount"/>
                                    </form:form>
                                </td>
                            </sec:authorize>
                            <sec:authorize access="!hasRole('ADMIN')">
                                <td>
                                    <form:form action="${pageContext.request.contextPath}/cart/addproduct/${product.id}" method="POST">
                                        <input type="submit" value="add to chart"/>
                                    </form:form>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                </table>
                <sec:authorize access="hasRole('ADMIN')">
                    <div><input type = "submit" value = "Assign Discounts"/></div>
                </sec:authorize>
            </form:form>
        </div>
    </body>
    <c:if test="${Products.products[0].category=='ACCESSORIES'}">
        <%@ include file="restrandombreed.jsp"%>
    </c:if>
</html>