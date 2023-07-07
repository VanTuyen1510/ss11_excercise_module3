<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new product</title>
</head>
<body>
    <h1>Create new product</h1>
    <form action="/product" method="post">
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" name="id"/></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name"/></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="quantity"/></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="price"/></td>
            </tr>
            <tr>
                <td>Date release</td>
                <td><input type="text" placeholder="dd/MM/yyyy" name="dateRelease"/></td>
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
            <tr>
                <td>Origin</td>
                <td><input type="text" name="origin"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="Add">
                </td>
            </tr>
            <input type="hidden" name="action" value="create" />
        </table>
    </form>
</body>
</html>
