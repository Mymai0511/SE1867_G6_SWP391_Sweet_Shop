<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/28/2024
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome for Icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        body {
            background: linear-gradient(135deg, #f8f9fa, #e9ecef);
            height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .login-container {
            background-color: #ffffff;
            padding: 2rem;
            border-radius: 15px;
            box-shadow: 0 4px 25px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 500px;
        }

        .login-header {
            text-align: center;
            margin-bottom: 1.5rem;
        }

        .login-header h2 {
            margin-bottom: 0;
        }

        .login-form .form-label {
            font-weight: 600;
        }

        /* Updated button class from btn-custom to btn-login */
        .btn-login {
            background-color: #ffc107;
            border: none;
            transition: background-color 0.3s;
            color: #fff; /* Ensure text is visible */
        }

        .btn-login:hover {
            background-color: #e0a800;
            color: #fff; /* Maintain text color on hover */
        }

        #username:focus, #password:focus {
            box-shadow: none;
            border-color: #ffc107;
        }

        .messages {
            min-height: 1.5rem;
        }

        .login-footer {
            text-align: center;
            margin-top: 1.5rem;
        }

        /* Optional: Style for the "Forgot password?" link */
        .login-form a {
            font-size: 0.9rem;
            color: #ffc107;
            text-decoration: none;
        }

        .login-form a:hover {
            text-decoration: underline;
            color: #e0a800;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp" flush="true"/>

<section class="d-flex justify-content-center" style="margin-top: 32px">
    <div class="login-container">
        <div class="login-header">
            <h2>Login</h2>
        </div>
        <div class="messages text-center ${mess != null ? "text-success" : (message != null ? "text-danger" : "")} mb-3" id="messages">
            ${mess != null ? mess : message}
        </div>

        <form action="login" method="post" class="login-form" onsubmit="return checkLogin(event)">
            <div class="mb-3">
                <label for="username" class="form-label">Username or Email</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                    <input type="text" class="form-control" value="${username == null ? "" : username}" id="username"
                           name="username"
                           placeholder="Enter your username or email">
                </div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-lock"></i></span>
                    <input type="password" class="form-control" value="${password == null ? "" : password}"
                           id="password" name="password"
                           placeholder="Enter your password">
                </div>
            </div>
            <div class="d-flex justify-content-center align-items-center mb-3">
                <a href="#" class="text-decoration-none">Forgot password?</a>
            </div>
            <!-- Updated button class -->
            <button type="submit" name="btnLogin" value="btnLogin"
                    class="btn-login w-100 p-2" style="border-radius: 5px">Submit
            </button>
        </form>
        <div class="login-footer">
            <p class="mt-4">Don't have an account? <a href="/login?action=register">Register</a></p>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp" flush="true"/>

<!-- Bootstrap JS and dependencies Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76A28Yd8uWAtIcH4n9t+K37CRvW0OZZ9N4L9M+0KfE11cMFA9hB7UwPbmdLM/5d" crossorigin="anonymous">
</script>
<script>
    function checkLogin(event) {
        const messageDiv = document.getElementById('messages');

        if (!messageDiv) {
            console.error("Element with ID 'messages' not found.");
            return false;
        }

        let valid = true;
        const username = document.getElementById("username").value.trim();
        const password = document.getElementById("password").value.trim();

        console.log("Username:", username);
        console.log("Password:", password);

        if (username === "" || password === "") {
            valid = false;
        }

        if (!valid) {
            messageDiv.textContent = 'You must input both username and password!';
            event.preventDefault();
            return false;
        } else {
            return true;
        }
    }
</script>
</body>
</html>
