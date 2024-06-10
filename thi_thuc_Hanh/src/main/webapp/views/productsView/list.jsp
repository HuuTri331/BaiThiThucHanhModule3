<%--
  Created by IntelliJ IDEA.
  User: nguye
  Date: 6/10/2024
  Time: 10:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 6/10/2024
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Management</title>
    <link rel="stylesheet" href="../../styles.css">
</head>
<body>
<div class="container">
    <h1>Management Product</h1>
    <div class="search-bar">
        <h2>Find Product</h2>
        <form>
            <input type="text" placeholder="Enter Product Name">
            <input type="text" placeholder="Enter Price">
            <input type="text" placeholder="Enter Category">
            <input type="text" placeholder="Enter Color">
            <button type="button" onclick="clearFields()">Clear</button>
            <button type="submit">Search</button>
        </form>
    </div>
    <button class="add-product"><a href="product/create">Add Product</a></button>
    <table>
        <thead>
        <tr>
            <th>STT</th>
            <th>Product Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th>Description</th>
            <th>Category</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="i" value="1"/>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${i}</td>
                <td>${product.nameProduct}</td>
                <td><fmt:formatNumber value="${product.price}" pattern="#,##0"/></td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.description}</td>
                <td>${product.idCategory.nameCategory}</td>

                <td>
                    <div style="display: flex;">
                        <form action="/product/edit" method="get" class="mt-3" style="margin-right: 10px">
                            <input type="hidden" name="idProduct" value="${product.idProduct}">
                            <button type="submit" class="btn btn-Info" style="width: 80px;">Edit</button>
                        </form>

                        <form action="/product/delete" method="post" class="mt-3"
                              onsubmit="return confirm('Are you sure you want to delete this user?');">
                            <input type="hidden" name="idProduct" value="${product.idProduct}">
                            <button type="submit" class="btn btn-danger" style="width: 80px;">Delete</button>
                        </form>
                    </div>
                </td>
            </tr>
            <c:set var="i" value="${i + 1}"/>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function clearFields() {
        document.querySelectorAll('.search-bar input').forEach(input => input.value = '');
    }
</script>
</body>
</html>
