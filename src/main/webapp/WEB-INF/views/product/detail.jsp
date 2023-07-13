<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detail product</title>
</head>
<body>
<h1>Detail product</h1>

<form action="/product">
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Date release</th>
            <th>Category</th>
<%--            <th>Origin</th>--%>

        </tr>

        <tr>
            <td>${requestScope["products"].id}</td>
            <td>${requestScope["products"].name}</td>
            <td>${requestScope["products"].quantity}</td>
            <td>${requestScope["products"].price}</td>
            <td>
                <fmt:formatDate value="${requestScope['products'].dateRelease}" pattern="dd/MM/yyyy"/>
            </td>
            <td>${requestScope["products"].category.name}</td>
<%--            <td>${requestScope["products"].origin}</td>--%>

        </tr>
        <input type="hidden" name="action" value="detail"/>
    </table>
</form>

</body>
</html>
