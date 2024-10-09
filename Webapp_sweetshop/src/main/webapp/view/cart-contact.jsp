<%--
  Created by IntelliJ IDEA.
  User: hoang
  Date: 10/4/2024
  Time: 3:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:include page="header.jsp"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart Contact | E-Shopper</title>
    <script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <script src="../assets/js/jquery.scrollUp.min.js"></script>
    <script src="../assets/js/jquery.prettyPhoto.js"></script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            padding: 20px;
        }
        .container {
            max-width: 900px;
            margin: auto;
            background: #fff;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h2, h3 {
            color: #333;
            border-bottom: 2px solid #eaeaea;
            padding-bottom: 10px;
        }
        .cart-items table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        .cart-items th, .cart-items td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        .cart-items th {
            background-color: #f2f2f2;
        }
        .cart-items td {
            background-color: #fcfcfc;
        }
        form {
            margin-top: 20px;
        }
        form label {
            display: block;
            margin-bottom: 10px;
        }
        form input[type="text"], form input[type="email"], form textarea, form select {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        form button {
            padding: 10px 20px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        form button:hover {
            background-color: #4cae4c;
        }
        button[type="button"] {
            background-color: #d9534f;
            margin-left: 10px;
        }
        button[type="button"]:hover {
            background-color: #c9302c;
        }
        .total-price {
            font-weight: bold;
            font-size: 1.2em;
            text-align: right;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Cart Contact</h2>
    <div class="cart-items">
        <table>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
            </tr>

            <tr>
                <td>1</td>
                <td>Product 1</td>
                <td>$100</td>
                <td>2</td>
                <td>$200</td>
            </tr>
        </table>
    </div>
    <h3>Total Price: $200</h3>

    <h3>Recipient Information</h3>
    <form method="POST" action="SubmitOrder">
        <label>Name: <input type="text" name="name" /></label><br />
        <label>Gender:
            <select name="gender">
                <option value="male">Male</option>
                <option value="female">Female</option>
            </select>
        </label><br />
        <label>Email: <input type="email" name="email" /></label><br />
        <label>Phone: <input type="text" name="phone" /></label><br />
        <label>Address: <textarea name="address"></textarea></label><br />
        <label>Note: <textarea name="note"></textarea></label><br />
        <button type="submit">Submit</button>
        <button type="button" onclick="location.href='view/cart.jsp'">Change</button>
    </form>
</div>
</body>
</html>
