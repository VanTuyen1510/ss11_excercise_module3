<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search product</title>
</head>
<body>
<h1>Your property is looking for</h1>


<table border="1">

    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Date release</th>
        <th>Category</th>
<%--        <th>Origin</th>--%>
    </tr>
    <c:forEach items="${products}" var="p">
        <br>
        <td><a href="/product?action=detail&id=${p.id}">${p.id}</a></td>

        <td>${p.name}</td>

        <td>${p.quantity}</td>
        <td>${p.price}</td>
        <td>
            <fmt:formatDate value="${p.dateRelease}" pattern="dd/MM/yyyy" />
        </td>
        <td>${p.category.name}</td>
<%--        <td>${p.origin}</td>--%>
        </tr>
    </c:forEach>
</table>
</body>
</html>
