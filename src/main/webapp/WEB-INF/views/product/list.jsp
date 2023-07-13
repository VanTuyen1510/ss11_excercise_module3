<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List product</title>
</head>
<body>
<h1>List product</h1>


<table border="1">

    <h2><a href="/product?action=create">Add new product</a></h2>
    <tr>
        <form method="post" action="/product">
            <label>Product Name:</label>
            <input type="text" name="name">
<%-- name="name" là lấy thuộc tính--%>
            <input type="submit" value="SEARCH">
            <input type="hidden" name="action" value="search">
            <%--hidden là ẩn khi truyền action vào thì vào value "search" vào search để gọi hàm--%>
        </form>
    </tr>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Date release</th>
        <th>Category</th>
<%--        <th>Origin</th>--%>
        <th>Edit</th>
        <th>Delete</th>
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
<%--            <td>${p.origin}</td>--%>
            <td><a href="/product?action=edit&id=${p.id}">Edit Product</a></td>
            <td><a href="/product?action=delete&id=${p.id}">Delete Product</a></td>
<%--            <td><a href="/product?action=search&name=${p.name}">Search Name</a> </td>--%>

        </tr>
    </c:forEach>
</table>
</body>
</html>
