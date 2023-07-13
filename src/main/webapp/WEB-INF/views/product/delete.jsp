<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting product</title>
</head>
<body>
<h1>Delete product</h1>
<form action="/product" method="post">
    <table>
        <tr>
            <td>ID</td>
            <td><input type="text" value="${product.id}" name="id"/></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" value="${product.name}" name="name"/></td>
        </tr>
        <tr>
            <td>Quantity</td>
            <td><input type="text" value="${product.quantity}" name="quantity"/></td>
        </tr>
        <tr>
            <td>Price</td>
            <td><input type="text" value="${product.price}" name="price"/></td>
        </tr>
        <tr>
            <td>Date release</td>
            <td><input type="text" value="${product.dateRelease}" placeholder="dd/MM/yyyy" name="dateRelease"/></td>
        </tr>
        <tr>
            <td>Category</td>
            <td>
                <select name="categoryId">
                    <c:forEach items="${categories}" var="c">
                        <option value="${c.id}">${c.name}</option>
                        <%--                            vì sao value là c.id--%>
                    </c:forEach>
                </select>
            </td>
        </tr>
<%--        <tr>--%>
<%--            <td>Origin</td>--%>
<%--            <td><input type="text" name="origin" value="${product.origin}"></td>--%>
<%--        </tr>--%>
        <tr>
            <td colspan="2">
                <input type="submit" value="Delete">
            </td>
        </tr>
        <input type="hidden" name="action" value="delete" />
    </table>
</form>
</body>
</html>
