<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/28/2024
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Cart | E-Shopper</title>

    <script src="../assets/js/html5shiv.js"></script>


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
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="image">Item</td>
                    <td class="description"></td>
                    <td class="price">Price</td>
                    <td class="quantity">Quantity</td>
                    <td class="total">Total</td>
                    <td></td>
                </tr>
                </thead>
                <!--Cart container-->
                <tbody>
                <c:forEach var="item" items="${cartItems}">
                    <tr>
                        <td class="cart_product">
                            <a href="viewdetail?id=${item.productDetailID}"><img src="../assets/image/cart/one.png" alt="Product Image"></a>
                        </td>
                        <td class="cart_description">
                            <h4><a href="viewdetail?id=${item.productDetailID}">${item.product.name}</a></h4>
                            <p>Size: ${item.productDetail.size}</p>
                        </td>
                        <td class="cart_price">
                            <p>${item.productDetail.price}$</p>
                        </td>
                        <td class="cart_quantity">
                            <div class="cart_quantity_button">
                                <button><a href="cartcontroller?action=increase&productDetailID=${item.productDetailID}"> + </button>
                                <input class="cart_quantity_input" type="text" name="quantity" value="${item.quantity}" autocomplete="off" size="2">
                                <button><a href="cartcontroller?action=decrease&productDetailID=${item.productDetailID}"> - </button>

                            </div>
                        </td>
                        <td class="cart_total">
                            <p class="cart_total_price">${Math.round( (item.productDetail.price * item.quantity) * 100.0 ) / 100.0}$</p>
                        </td>
                        <td class="cart_delete">
                            <a class="cart_quantity_delete" href="cartcontroller?action=remove&productDetailID=${item.productDetailID}"> remove </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section> <!--/#cart_items-->

<section id="do_action">
    <div class="container">
        <div class="heading">
            <h3>What would you like to do next?</h3>
            <p>Choose if you have a discount code or reward points you want to use or would like to estimate your delivery cost.</p>
        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="chose_area">
                    <ul class="user_option">
                        <li>
                            <input type="checkbox">
                            <label>Use Coupon Code</label>
                        </li>
                        <li>
                            <input type="checkbox">
                            <label>Use Gift Voucher</label>
                        </li>
                        <li>
                            <input type="checkbox">
                            <label>Estimate Shipping & Taxes</label>
                        </li>
                    </ul>
                    <ul class="user_info">
                        <li class="single_field">
                            <label>Country:</label>
                            <select>
                                <option>United States</option>
                                <option>Bangladesh</option>
                                <option>UK</option>
                                <option>India</option>
                                <option>Pakistan</option>
                                <option>Ucrane</option>
                                <option>Canada</option>
                                <option>Dubai</option>
                            </select>

                        </li>
                        <li class="single_field">
                            <label>Region / State:</label>
                            <select>
                                <option>Select</option>
                                <option>Dhaka</option>
                                <option>London</option>
                                <option>Dillih</option>
                                <option>Lahore</option>
                                <option>Alaska</option>
                                <option>Canada</option>
                                <option>Dubai</option>
                            </select>

                        </li>
                        <li class="single_field zip-field">
                            <label>Zip Code:</label>
                            <input type="text">
                        </li>
                    </ul>
                    <a class="btn btn-default update" href="">Get Quotes</a>
                    <a class="btn btn-default check_out" href="">Continue</a>
                </div>
            </div>
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
    </div>
</section><!--/#do_action-->

<jsp:include page="footer.jsp" flush="true" />

<script src="../assets/js/jquery.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script src="../assets/js/jquery.scrollUp.min.js"></script>
<script src="../assets/js/jquery.prettyPhoto.js"></script>
<script src="../assets/js/main.js"></script>
</body>
</html>