<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login form example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>

<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<div class="container">
    <div class="col-md-8 offset-md-4">
        <h2>Login</h2>
        <p>Please, enter your credentials</p>
        <form method="post" action="/login" class="needs-validation" novalidate>
            <div class="form-group">
                <label for="name">Name:</label>

                <input type="text" class="form-control w-25" id="name" placeholder="Enter name" name="name"
                       required>
                <div class="invalid-feedback">Name should be entered!</div>
            </div>
            <div class="form-group">
                <label for="surname">Surname:</label>
                <input type="text" class="form-control w-25" id="surname" placeholder="Enter surname" name="surname"
                       required>
                <div class="invalid-feedback">Surname should be entered!</div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control w-25" id="email" placeholder="Enter email" name="email"
                           required>
                    <span class="error">${emailError}</span>
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="text" class="form-control w-25" id="password" placeholder="Enter your password"
                           name="password" required>
                    <span class="error">${passwordError}</span>
                </div>
                <div class="form-group">
                    <label for="date">Date:</label>
                    <input type="text" class="form-control w-25" id="date" placeholder="Enter your birthday" name="date"
                           required>
                    <div class="invalid-feedback">Date should be entered!</div>
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
                <br>
                <p>If you are not registered, go to the registration page by clicking the button below</p>
                <a href="${contextPath}/eshop?command=register" button type="submit" class="btn btn-primary">Go to
                    Go to registration page</a>
            </div>
        </form>
    </div>
</div>

<script>
    // Disable form submissions if there are invalid fields
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            // Get the forms we want to add validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
</body>
</html>

