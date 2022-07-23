<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Магазин товаров</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">SanekShop</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/home">Главная</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/cabinet/open">Кабинет</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/cart/open">Корзина</a>
                </li>
            </ul>
            <form method="post" action="${contextPath}/search" class="d-flex">
                <input class="form-control me-2" id="search" type="text" placeholder="Введите название товара"
                       name="search"
                       required>
                <button class="btn btn-primary" type="button">Найти</button>
            </form>
        </div>
    </div>
</nav>


<div class="container mt-3">
    <h2>Все товары</h2>
    <p>Выберите категорию</p>
    <c:if test="${not empty categories}">

        <div class="row">
            <c:forEach items="${categories}" var="category">

                <div class="col-sm-2 p-3 ">
                    <img class="card-img" style="width:150px;height:120px"
                         src="${contextPath}/images/${category.getImagePath()}" alt="Card image"> <br>
                </div>
                <div class="col-sm-6 p-3">
                    <a href="${contextPath}/category/${category.getId()}">${category.getName()}</a>
                </div>
                <div class="col-sm-3 p-3">

                </div>
            </c:forEach>
        </div>

    </c:if>
</div>
</body>
</html>



