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
    <title>Find account</title>
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

        #email:focus {
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
            <h2>Find your account</h2>
            <p class="text-muted">Please enter your email to find account!</p>
        </div>
        <div class="messages text-center ${mess != null ? "text-success" : (message != null ? "text-danger" : "")} mb-3" id="messages">
            ${mess != null ? mess : message}
        </div>

        <form action="forget_password" method="post" class="login-form" onsubmit="return checkEmailForget(event)" novalidate>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <div class="input-group">
                    <span class="input-group-text"><i class="fas fa-envelope"></i></span>
                    <input type="email" class="form-control" value="${email == null ? "" : email}" id="email" name="email"
                           placeholder="Enter your email">
                </div>
                <!-- Thông báo lỗi cho Email -->
                <span class="error-message text-danger" id="emailError"></span>
            </div>
            <button type="submit" name="btnForget" value="btnForget"
                    class="btn-login w-100 p-2" style="border-radius: 5px">Submit
            </button>
        </form>
        <div class="login-footer">
            <p class="mt-4">Already have an account? <a href="/login">Login</a></p>
        </div>
    </div>
</section>

<jsp:include page="footer.jsp" flush="true"/>

<!-- Bootstrap JS and dependencies Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-w76A28Yd8uWAtIcH4n9t+K37CRvW0OZZ9N4L9M+0KfE11cMFA9hB7UwPbmdLM/5d" crossorigin="anonymous">
</script>
<script>
    function checkEmailForget(event) {
        let isValid = true;

        const email = document.getElementById("email");
        const emailError = document.getElementById("emailError");

        function showError(inputElement, errorElement, message) {
            inputElement.classList.add("is-invalid");
            errorElement.textContent = message;
        }

        function clearError(inputElement, errorElement) {
            inputElement.classList.remove("is-invalid");
            errorElement.textContent = "";
        }

        clearError(email, emailError);

        if (email.value.trim() === "") {
            showError(email, emailError, "You must enter the email!");
            isValid = false;
        } else {
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email.value.trim())) {
                showError(email, emailError, "Invalid email format!");
                isValid = false;
            }
        }

        if (!isValid) {
            event.preventDefault();
            return false;
        }

        return true;
    }
</script>
</body>
</html>
