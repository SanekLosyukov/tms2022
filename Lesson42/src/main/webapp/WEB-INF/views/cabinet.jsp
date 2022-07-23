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
    <h2>Кабинет пользователя </h2>

    <c:if test="${not empty user}">

        <div class="container mt-3">
            <p>Добрый день, рады вас видеть. Ваши данные:</p>
            <table class="table">
                <thead>
                <tr>
                    <th>Имя</th>
                    <th>Фамилия</th>
                    <th>Email</th>
                    <th>Дата рождения</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${user.getName()}</td>
                    <td>${user.getSurname()}</td>
                    <td>${user.getEmail()}</td>
                    <td>${user.getDate()}</td>
                </tr>
                </tbody>
            </table>
        </div>

    </c:if>
</div>

<div class="container mt-3">


    <p><h5>Купленные Вами товары:</h5></p>
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>Название товара</th>
            <th>Описание</th>
            <th>Стоимость</th>
        </tr>
        </thead>
        <tbody>
        <c
        <c:forEach items="${sessionScope.order.getProducts()}" var="product">
            <tr>
                <td><img class="card-img" style="width:65px;height:80px"
                         src="${contextPath}/images/${product.getImagePath()}" alt="Card image"></td>
                <td>${product.getName()}</td>
                <td>${product.getDescription()}</td>
                <td>${product.getPrice()}</td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <h5>Общая сумма купленных Вами товаров составляет ${order.totalPrice} руб.</h5>

</div>
</body>
</html>