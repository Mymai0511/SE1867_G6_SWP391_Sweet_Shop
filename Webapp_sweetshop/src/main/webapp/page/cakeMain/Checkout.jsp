<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hưng
  Date: 9/14/2024
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="Cake Template">
    <meta name="keywords" content="Cake, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Cake | Template</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="../../assets/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/flaticon.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/barfiller.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/magnific-popup.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="../../assets/css/style.css" type="text/css">

    <style>
        select {
            all: unset; /* Xóa tất cả các thuộc tính CSS */
            appearance: none; /* Loại bỏ kiểu mặc định của trình duyệt */
            width: auto; /* Đặt chiều rộng về mặc định */
        }
    </style>
</head>

<body>
<!-- Page Preloder -->
<div id="preloder">
    <div class="loader"></div>
</div>

<!-- Offcanvas Menu Begin -->
<div class="offcanvas-menu-overlay"></div>
<div class="offcanvas-menu-wrapper">
    <div class="offcanvas__cart">
        <div class="offcanvas__cart__links">
            <a href="#" class="search-switch"><img src="./img/icon/search.png" alt=""></a>
            <a href="#"><img src="./img/icon/heart.png" alt=""></a>
        </div>
        <div class="offcanvas__cart__item">
            <a href="#"><img src="./img/icon/cart.png" alt=""> <span>0</span></a>
            <div class="cart__price">Cart: <span>$0.00</span></div>
        </div>
    </div>
    <div class="offcanvas__logo">
        <a href="./index.html"><img src="./img/logo.png" alt=""></a>
    </div>
    <div id="mobile-menu-wrap"></div>
    <div class="offcanvas__option">
        <ul>
            <li>USD <span class="arrow_carrot-down"></span>
                <ul>
                    <li>EUR</li>
                    <li>USD</li>
                </ul>
            </li>
            <li>ENG <span class="arrow_carrot-down"></span>
                <ul>
                    <li>Spanish</li>
                    <li>ENG</li>
                </ul>
            </li>
            <li><a href="#">Sign in</a> <span class="arrow_carrot-down"></span></li>
        </ul>
    </div>
</div>
<!-- Offcanvas Menu End -->

<!-- Header Section Begin -->
<header class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="header__top__inner">
                        <div class="header__top__left">
                            <ul>
                                <li>USD <span class="arrow_carrot-down"></span>
                                    <ul>
                                        <li>EUR</li>
                                        <li>USD</li>
                                    </ul>
                                </li>
                                <li>ENG <span class="arrow_carrot-down"></span>
                                    <ul>
                                        <li>Spanish</li>
                                        <li>ENG</li>
                                    </ul>
                                </li>
                                <li><a href="#">Sign in</a> <span class="arrow_carrot-down"></span></li>
                            </ul>
                        </div>
                        <div class="header__logo">
                            <a href="./index.html"><img src="./img/logo.png" alt=""></a>
                        </div>
                        <div class="header__top__right">
                            <div class="header__top__right__links">
                                <a href="#" class="search-switch"><img src="./img/icon/search.png" alt=""></a>
                                <a href="#"><img src="./img/icon/heart.png" alt=""></a>
                            </div>
                            <div class="header__top__right__cart">
                                <a href="#"><img src="./img/icon/cart.png" alt=""> <span>0</span></a>
                                <div class="cart__price">Cart: <span>$0.00</span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="canvas__open"><i class="fa fa-bars"></i></div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <nav class="header__menu mobile-menu">
                    <ul>
                        <li><a href="./index.html">Home</a></li>
                        <li><a href="./about.html">About</a></li>
                        <li class="active"><a href="./shop.html">Shop</a></li>
                        <li><a href="#">Pages</a>
                            <ul class="dropdown">
                                <li><a href="./shop-details.html">Shop Details</a></li>
                                <li><a href="./shoping-cart.html">Shoping Cart</a></li>
                                <li><a href="./checkout.html">Check Out</a></li>
                                <li><a href="./wisslist.html">Wisslist</a></li>
                                <li><a href="./Class.html">Class</a></li>
                                <li><a href="./blog-details.html">Blog Details</a></li>
                            </ul>
                        </li>
                        <li><a href="./blog.html">Blog</a></li>
                        <li><a href="./contact.html">Contact</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
</header>
<!-- Header Section End -->

<!-- Breadcrumb Begin -->
<div class="breadcrumb-option">
    <div class="container">
        <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
                <div class="breadcrumb__text">
                    <h2>Checkout</h2>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
                <div class="breadcrumb__links">
                    <a href="./index.html">Home</a>
                    <span>Checkout</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Checkout Section Begin -->
<section class="checkout spad">
    <div class="container">
        <div class="checkout__form">
            <form action="checkout" method="post">
                <div class="row">
                    <div class="col-lg-8 col-md-6">
                        <h6 class="coupon__code"><span class="icon_tag_alt"></span> Have a coupon? <a href="#">Click
                            here</a> to enter your code</h6>
                        <h6 class="checkout__title">Billing Details</h6>
                        <div class="checkout__input">
                            <p>Consignee Name<span>*</span></p>
                            <input type="text" name="name" placeholder="Enter consignee name..."/>
                        </div>

                        <div class="row">
                            <div class="col-lg-6">
                                <div class="checkout__input">
                                    <p>Phone<span>*</span></p>
                                    <input type="text" name="phone" id="phone" placeholder="Enter phone number..."/>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="checkout__input">
                                    <p>Email<span>*</span></p>
                                    <input type="email" name="email" id="email" placeholder="Enter email...">
                                </div>
                            </div>
                        </div>

                        <div class="checkout__input row mb-3">
                            <div class="col-sm-12 col-md-4">
                                <p>Xã<span>*</span></p>
                                <select name="location" size="10">
                                    <c:set var="index" value="0"/>
                                    <c:forEach var="l" items="${location}">
                                        <option value="${l}" <c:if test="${index == 0}">selected</c:if>>${l}</option>
                                        <c:set var="index" value="${index + 1}"/>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-sm-12 col-md-4">
                                <p>Huyện<span>*</span></p>
                                <select name="district">
                                    <option value="Thạch Thất" style="width: 100%">Thạch Thất</option>
                                </select>
                            </div>
                            <div class="col-sm-12 col-md-4">
                                <p>Tỉnh<span>*</span></p>
                                <select name="country" style="width: 100% !important; box-sizing: border-box !important;">
                                    <option value="Hà Nội">Hà Nội</option>
                                </select>
                            </div>
                        </div>

                        <div class="checkout__input">
                            <p>Voucher</p>
                            <input type="text" name="voucher" id="voucher" placeholder="Add code voucher ..."/>
                        </div>
                        <input type="hidden" id="voucherList" name="voucherList" value="${codeVoucher}" />
                    </div>
                    <div class="col-lg-4 col-md-6">
                        <div class="checkout__order">
                            <h6 class="order__title">Your order</h6>
                            <div class="checkout__order__products">Product <span>Total</span></div>
                            <ul class="checkout__total__products">
                                <c:set var="index" value="0" />
                                <c:set var="total" value="0" />
                                <c:forEach var="pd" items="${listPD}">
                                    <c:set var="total" value="${total + pd.price}" />
                                    <li>
                                        <samp>${index + 1}. </samp>
                                        ${product.getProductById(pd.productID).name}
                                        <span>${pd.price} vnd</span>
                                    </li>
                                </c:forEach>
                            </ul>
                            <ul class="checkout__total__all">
                                <li>Total <span>${total} vnd</span></li>
                            </ul>
                            <button type="submit" class="site-btn">PLACE ORDER</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<!-- Checkout Section End -->

<!-- Footer Section Begin -->
<footer class="footer set-bg" data-setbg="img/footer-bg.jpg">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="footer__widget">
                    <h6>WORKING HOURS</h6>
                    <ul>
                        <li>Monday - Friday: 08:00 am – 08:30 pm</li>
                        <li>Saturday: 10:00 am – 16:30 pm</li>
                        <li>Sunday: 10:00 am – 16:30 pm</li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="footer__about">
                    <div class="footer__logo">
                        <a href="#"><img src="./img/footer-logo.png" alt=""></a>
                    </div>
                    <p>Lorem ipsum dolor amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore dolore magna aliqua.</p>
                    <div class="footer__social">
                        <a href="#"><i class="fa fa-facebook"></i></a>
                        <a href="#"><i class="fa fa-twitter"></i></a>
                        <a href="#"><i class="fa fa-instagram"></i></a>
                        <a href="#"><i class="fa fa-youtube-play"></i></a>
                    </div>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 col-sm-6">
                <div class="footer__newslatter">
                    <h6>Subscribe</h6>
                    <p>Get latest updates and offers.</p>
                    <form action="#">
                        <input type="text" placeholder="Email">
                        <button type="submit"><i class="fa fa-send-o"></i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-lg-7">
                    <p class="copyright__text text-white">
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script>
                        All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i>
                        by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                        <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    </p>
                </div>
                <div class="col-lg-5">
                    <div class="copyright__widget">
                        <ul>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Terms & Conditions</a></li>
                            <li><a href="#">Site Map</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
<!-- Footer Section End -->

<!-- Search Begin -->
<div class="search-model">
    <div class="h-100 d-flex align-items-center justify-content-center">
        <div class="search-close-switch">+</div>
        <form class="search-model-form">
            <input type="text" id="search-input" placeholder="Search here.....">
        </form>
    </div>
</div>
<!-- Search End -->

<!-- Js Plugins -->
<script src="../../assets/js/jquery-3.3.1.min.js"></script>
<script src="../../assets/js/bootstrap.min.js"></script>
<script src="../../assets/js/jquery.nice-select.min.js"></script>
<script src="../../assets/js/jquery.barfiller.js"></script>
<script src="../../assets/js/jquery.magnific-popup.min.js"></script>
<script src="../../assets/js/jquery.slicknav.js"></script>
<script src="../../assets/js/owl.carousel.min.js"></script>
<script src="../../assets/js/jquery.nicescroll.min.js"></script>
<script src="../../assets/js/main.js"></script>
<script>
    document.querySelector('form').addEventListener('submit', function(e) {
        // Dừng form không submit ngay lập tức
        e.preventDefault();

        // Lấy tất cả input fields
        const name = document.querySelector('input[name="name"]');
        const phone = document.querySelector('input[name="phone"]');
        const email = document.querySelector('input[name="email"]');
        const voucher = document.querySelector('input[name="voucher"]');
        const voucherList = document.getElementById('voucherList').value; // Danh sách mã voucher

        // Xóa các thông báo lỗi cũ
        document.querySelectorAll('.error-message').forEach(el => el.remove());

        // Hàm loại bỏ khoảng trắng đầu cuối và nhiều khoảng trắng chuyển thành 1
        function cleanInput(input) {
            return input.value.trim().replace(/\s+/g, ' ');
        }

        // Hiển thị thông báo lỗi
        function showError(field, message) {
            const errorMessage = document.createElement('span');
            errorMessage.classList.add('error-message');
            errorMessage.style.color = 'red';
            errorMessage.innerText = message;
            field.parentNode.appendChild(errorMessage);
        }

        // Validate các trường không được để trống
        function validateField(field, fieldName) {
            const value = cleanInput(field);
            if (value === '') {
                showError(field, `${fieldName} không được để trống`);
                return false;
            }
            field.value = value; // Cập nhật lại giá trị sau khi clean
            return true;
        }

        // Kiểm tra voucher
        function validateVoucher(voucher, voucherList) {
            const voucherValue = cleanInput(voucher); // Lấy mã voucher người dùng nhập
            const voucherArray = voucherList.split('|').filter(v => v); // Tách chuỗi mã voucher
            if (!voucherArray.includes(voucherValue)) {
                showError(voucher, 'Mã voucher không tồn tại');
                return false;
            }
            return true;
        }

        // Biến để kiểm tra xem tất cả các trường có hợp lệ không
        let isValid = true;

        // Kiểm tra các trường
        if (!validateField(name, 'Tên người nhận')) isValid = false;
        if (!validateField(phone, 'Số điện thoại')) isValid = false;
        if (!validateField(email, 'Email')) isValid = false;

        // Voucher có thể bỏ trống, nhưng nếu nhập phải kiểm tra hợp lệ
        if (voucher.value.trim() !== '' && !validateVoucher(voucher, voucherList)) isValid = false;

        // Nếu tất cả các trường đều hợp lệ thì submit form
        if (isValid) {
            e.target.submit();
        }
    });
</script>
</body>
</html>
