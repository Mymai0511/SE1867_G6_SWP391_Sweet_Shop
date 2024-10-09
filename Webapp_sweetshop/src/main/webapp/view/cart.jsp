<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/28/2024
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | E-Shopper</title>
    <script src="../assets/js/html5shiv.js"></script>
    <style>
        td{
            text-align: center;
            vertical-align: middle;
        }
        /* Cart Css */
        .cart_menu {
            background-color: #f4f4f4;
            color: #555;
            font-weight: bold;
            text-align: center;
        }
        .cart_product{
            align-items: center;
        }
        .cart_product img {
            width: 100px;
            height: 100px;
        }

        .cart_quantity_button {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .cart_quantity_button button {
            background-color: #bebebf;
            border: none;
            border-radius: 4px;
            padding: 5px 10px;
            cursor: pointer;
            width: 30px;
            height: 30px;
            font-size: 16px;
            font-weight: bold;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .cart_quantity_button button:hover {
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.5);
        }

        .cart_quantity_input {
            text-align: center;
            width: 40px;
            margin: 0 5px;
            border: 1px solid black;
            border-radius: 4px;
        }

        .cart_quantity_input:hover {
            border-color: blue;
            border-width: 2px;
        }

        .cart_delete a {
            color: #d9534f;
            text-decoration: none;
        }

        .cart_delete a:hover {
            color: #c9302c;
        }

        .bold-price {
            font-weight: bold;
        }

        .cart-checkbox {
            width: 18px;
            height: 18px;
            border: 1px solid gray;
            margin: 0 auto;
        }

        .cart_actions .cart_quantity_delete {
            display: inline-flex;
            align-items: center;
        }


        /*Popup Screen Css*/
        .modal-content {
            background-color: white;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 50%;
        }


        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .btn {
            padding: 10px 20px;
            margin: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        .btn.cancel {
            background-color: red;
        }

        .btn:hover {
            opacity: 0.8;
        }

        label, select, input, textarea {
            display: block;
            margin-bottom: 10px;
        }

        img {
            max-width: 100px;
            margin-bottom: 10px;
        }
    </style>
</head>

<body>

<jsp:include page="header.jsp"/>

<section id="cart_items">
    <div class="container">
        <div class="breadcrumbs">
            <ol class="breadcrumb">
                <li><a href="#">Home</a></li>
                <li class="active">Shopping Cart</li>
            </ol>
        </div>
        <div class="table-responsive cart_info">
            <table class="table table-condensed cart-table">
                <thead>
                <tr class="cart_menu">
                    <td class="checkbox">Select</td>
                    <td class="image">Item</td>
                    <td class="description">Description</td>
                    <td class="price">Price</td>
                    <td class="quantity">Quantity</td>
                    <td class="actions">Actions</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td class="cart_select">
                                <input type="checkbox" name="selectedItems" value="${item.productDetailID}" class="cart-checkbox">
                        </td>
                        <td class="cart_product">
                            <a href="viewdetail?id=${item.productDetailID}"><img src="../assets/image/cart/one.png" alt="Product Image"></a>
                        </td>
                        <td class="cart_description">
                            <h4><a href="viewdetail?id=${item.productDetailID}">${item.product.name}</a></h4>
                            <p>${item.product.description}</p>
                        </td>
                        <td class="cart_price">
                            <p class="bold-price">${item.productDetail.price}$</p>
                        </td>
                        <td class="cart_quantity">
                            <div class="cart_quantity_button">
                                <button class="quantity_minus"> - </button>
                                <input class="cart_quantity_input" type="text" name="quantity" value="${item.quantity}" autocomplete="off" size="2">
                                <button class="quantity_plus"> + </button>
                            </div>
                        </td>
                        <td class="cart_actions">
                            <button class="cart_edit"><i class="fa fa-pencil"></i></button>
                            <a class="cart_quantity_delete" href="cartcontroller?action=remove&productDetailID=${item.productDetailID}">
                                <i class="fa fa-trash"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<section id="do_action">
    <div class="container">
        <div class="col-sm-6">
            <div class="total_area">
                <ul>
                    <li>Cart Sub Total <span>${Math.round(subtotal * 100.0) / 100.0}$</span></li>
                    <li>Discount <span>${Math.round(discount * 100.0) / 100.0}$</span></li>
                    <li>Total <span>${Math.round(total * 100.0) / 100.0}$</span></li>
                </ul>
                <a class="btn btn-default update" href="">Update</a>
                <a class="btn btn-default check_out" href="bill.jsp">Check Out</a>
            </div>
        </div>
    </div>
</section>
<!--Popup Edit Screen-->
<div id="editModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h4>Edit Product</h4>
        <img src="../assets/image/cart/one.png" alt="Product Image">
        <p>Product: ${item.product.name}</p>
        <p>Price: ${item.productDetail.price}$</p>
        <label for="candle">Candle:</label>
        <select id="candle">
            <option value="small">Small (Height: 3-5cm)</option>
            <option value="big">Big (Height: 5-7cm)</option>
            <option value="number">Number</option>
        </select>
        <input type="text" placeholder="Number you want to" id="candleNumber">
        <br>
        <label><input type="checkbox" name="flare" value="birthdayFlare"> Birthday Flare</label>
        <br>
        <label for="messageOption">Message Option:</label>
        <select id="messageOption">
            <option value="cake">Write on cake</option>
            <option value="card">Write on card</option>
        </select>
        <input type="text" placeholder="Write your message here" id="message">
        <br>
        <label for="note">Your note:</label>
        <textarea id="note" placeholder="Any special instructions"></textarea>
        <br>
        <button class="btn save" id="saveEdit">Save</button>
        <button class="btn cancel" id="cancelEdit">Cancel</button>
    </div>
</div>

<jsp:include page="footer.jsp" flush="true" />

<script>
    document.querySelectorAll('.quantity_plus').forEach(button => {
        button.addEventListener('click', function() {
            let quantityInput = this.previousElementSibling; // Lấy input ở trước nút
            quantityInput.value = parseInt(quantityInput.value) + 1; // Tăng số lượng
        });
    });

    document.querySelectorAll('.quantity_minus').forEach(button => {
        button.addEventListener('click', function() {
            let quantityInput = this.nextElementSibling; // Lấy input ở sau nút
            if (parseInt(quantityInput.value) > 1) {
                quantityInput.value = parseInt(quantityInput.value) - 1; // Giảm số lượng
            }
        });
    });

    // Handle the edit popup modal
    let editModal = document.getElementById("editModal");
    let editButtons = document.querySelectorAll(".cart_edit");
    let closeModalButton = document.getElementsByClassName("close")[0];

    editButtons.forEach(button => {
        button.onclick = function() {
            editModal.style.display = "block";
        };
    });

    closeModalButton.onclick = function() {
        if (confirm("You did not save your option! Do you want to exit?")) {
            editModal.style.display = "none";
        }
    };

    document.getElementById("cancelEdit").onclick = function() {
        editModal.style.display = "none";
    };

    window.onclick = function(event) {
        if (event.target == editModal) {
            if (confirm("You did not save your option! Do you want to exit?")) {
                editModal.style.display = "none";
            }
        }
    };

    function showModal() {
        editModal.style.display = "block";
    }

    function closeModal() {
        editModal.style.display = "none";
    }

    document.querySelector(".close").onclick = function() {
        closeModal();
    };

    // Assign event to the Cancel button
    document.getElementById("cancelEdit").onclick = function() {
        closeModal();
    };
</script>
<script src="../assets/js/jquery.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/jquery.scrollUp.min.js"></script>
<script src="../assets/js/jquery.prettyPhoto.js"></script>
</body>
</html>