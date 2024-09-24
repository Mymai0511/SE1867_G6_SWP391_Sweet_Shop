<%--
  Created by IntelliJ IDEA.
  User: Hưng
  Date: 9/14/2024
  Time: 2:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        * {
            box-sizing: border-box;
        }

        .container {
            position: relative;
        }

        .mySlides {
            display: none;
        }

        .cursor {
            cursor: pointer;
        }

        .prev, .next {
            cursor: pointer;
            position: absolute;
            top: 40%;
            padding: 16px;
            color: white;
            font-weight: bold;
            font-size: 20px;
            user-select: none;
        }

        .next {
            right: 0;
        }

        .prev:hover, .next:hover {
            background-color: rgba(0, 0, 0, 0.8);
        }

        .numbertext {
            color: #f2f2f2;
            font-size: 12px;
            padding: 8px 12px;
            position: absolute;
            top: 0;
        }

        .thumbnail-row {
            display: flex;
            overflow-x: auto; /* Tạo thanh trượt ngang */
            white-space: nowrap; /* Các hình thu nhỏ không xuống dòng */
        }

        .column {
            flex: 0 0 auto; /* Đảm bảo cột không co lại */
            width: 16.66%;
        }

        .demo {
            opacity: 0.6;
        }

        .active, .demo:hover {
            opacity: 1;
        }
    </style>

    <script>
        let slideIndex = 1;
        window.onload = function () {
            currentSlide(1); // Tự động hiển thị ảnh đầu tiên
        };

        function plusSlides(n) {
            showSlides(slideIndex += n);
        }

        function currentSlide(n) {
            showSlides(slideIndex = n);
        }

        function showSlides(n) {
            let i;
            let slides = document.getElementsByClassName("mySlides");
            let dots = document.getElementsByClassName("demo");
            let captionText = document.getElementById("caption");
            if (n > slides.length) {
                slideIndex = 1
            }
            if (n < 1) {
                slideIndex = slides.length
            }
            for (i = 0; i < slides.length; i++) {
                slides[i].style.display = "none";
            }
            for (i = 0; i < dots.length; i++) {
                dots[i].className = dots[i].className.replace(" active", "");
            }
            slides[slideIndex - 1].style.display = "block";
            dots[slideIndex - 1].className += " active";
            if (captionText) {
                captionText.innerHTML = dots[slideIndex - 1].alt;
            }
        }

        document.getElementById('myInput').addEventListener('input', function () {
            const minValue = parseInt(this.min);
            if (this.value < minValue) {
                this.value = minValue;
            }
        });
    </script>

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
            <a href="#" class="search-switch"><img src="assets/img/icon/search.png" alt=""></a>
            <a href="#"><img src="assets/img/icon/heart.png" alt=""></a>
        </div>
        <div class="offcanvas__cart__item">
            <a href="#"><img src="assets/img/icon/cart.png" alt=""> <span>0</span></a>
            <div class="cart__price">Cart: <span>$0.00</span></div>
        </div>
    </div>
    <div class="offcanvas__logo">
        <a href="./index.html"><img src="assets/img/logo.png" alt=""></a>
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
                            <a href="./index.html"><img src="assets/img/logo.png" alt=""></a>
                        </div>
                        <div class="header__top__right">
                            <div class="header__top__right__links">
                                <a href="#" class="search-switch"><img src="assets/img/icon/search.png" alt=""></a>
                                <a href="#"><img src="assets/img/icon/heart.png" alt=""></a>
                            </div>
                            <div class="header__top__right__cart">
                                <a href="#"><img src="assets/img/icon/cart.png" alt=""> <span>0</span></a>
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
                    <h2>Product detail</h2>
                </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6">
                <div class="breadcrumb__links">
                    <a href="./home">Home</a>
                    <a href="#">Shop</a>
                    <span>Sweet autumn leaves</span>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Breadcrumb End -->

<!-- Shop Details Section Begin -->
<section class="product-details spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <!-- Container for the image gallery -->
                <div class="container">
                    <!-- Full-width images with number text -->
                    <c:forEach items="${mediaList}" var="img">
                        <div class="mySlides">
                            <img src="assets/image/product/${img.image}" style="width:100%">
                        </div>
                    </c:forEach>
                    <!-- Next and previous buttons -->
                    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
                    <a class="next" onclick="plusSlides(1)">&#10095;</a>

                    <!-- Thumbnail images -->
                    <div class="thumbnail-row">
                        <c:forEach items="${mediaList}" var="img">
                            <div class="column"
                                 style="display: flex; justify-content: center; align-items: center; padding: 10px">
                                <img class="demo cursor" src="assets/image/product/${img.image}" style="width:100%"
                                     onclick="currentSlide(1)" alt="The Woods">
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <form action="viewdetail" method="post" class="product__details__text">
                    <div class="product__label">${category.name}</div>
                    <h4>${product.name}</h4>
                    <h5 id="price">0 vnd</h5>
                    <h5 style="border-bottom: 0px; padding-bottom: 0px;">
                        Size:
                    </h5>
                    <c:forEach var="pd" items="${productDetailList}">
                        <button type="button"
                                name="btnSize"
                                id="size"
                                class="btn btn-outline-info mb-3"
                                value='${pd.id}", "${pd.price}", "${pd.size}", "${pd.productID}'
                                onclick="changeSize(${pd.price})">
                                ${pd.size}
                        </button>
                    </c:forEach>

                    <div class="product__details__option row">
                        <div class="quantity col-12 mb-3">
                            <h5 style="border-bottom: 0px; padding-bottom: 0px;">Quantity:</h5>
                            <div class="pro-qty">
                                <input type="number" name="quantity" id="myInput" value="1" min="1"/>
                            </div>
                        </div>
                        <div class="col-12">
                            <h5 style="border-bottom: 0px; padding-bottom: 0px;">Description:</h5>
                            <p>${product.description}</p>
                        </div>
                        <button type="submit" name="btnBuy" class="primary-btn col-4" style="margin-left: 12px">
                            Buy now
                        </button>
                        <c:if test="${user != null}">
                            <button type="submit" name="btnAdd" class="primary-btn col-4">Add to cart</button>
                        </c:if>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
<!-- Shop Details Section End -->

<!-- Footer Section Begin -->
<footer class="footer set-bg" data-setbg="assets/img/footer-bg.jpg">
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
                        <a href="#"><img src="./assets/img/footer-logo.png" alt=""></a>
                    </div>
                    <p>Lorem ipsum dolor amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
                        labore dolore magna aliqua.</p>
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
                    <p class="copyright__text text-white"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | Team 6 - HE1867
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
    document.addEventListener("DOMContentLoaded", function () {
        const firstButton = document.querySelector("button[name='btnSize']");
        if (firstButton) {
            const price = extractPriceFromValue(firstButton.value);
            changeSize(price, firstButton); // Gọi hàm và truyền cả button
        }
    });

    // Hàm lấy giá trị price từ thuộc tính value
    function extractPriceFromValue(value) {
        const parts = value.split(",");
        let price = parts[1].trim().replace('"', '');
        return parseFloat(price);
    }

    // Hàm cập nhật giá và thay đổi class của nút bấm
    function changeSize(price, button) {
        // Thay đổi nội dung giá sản phẩm
        let priceChange = document.getElementById("price");
        priceChange.innerHTML = price + " vnd";

        // Lấy tất cả các nút để đổi class
        const buttons = document.querySelectorAll("button[name='btnSize']");
        buttons.forEach(btn => {
            btn.classList.remove('btn-info'); // Xóa class 'btn-info'
            btn.classList.add('btn-outline-info'); // Đảm bảo là 'btn-outline-info'
        });

        // Thêm class 'btn-info' cho nút đã bấm
        button.classList.remove('btn-outline-info');  // Xóa class 'btn-outline-info'
        button.classList.add('btn-info');  // Thêm class 'btn-info'
    }

    // Xử lý sự kiện click của từng nút
    const buttons = document.querySelectorAll("button[name='btnSize']");
    buttons.forEach(button => {
        button.addEventListener("click", function () {
            const price = extractPriceFromValue(this.value);
            changeSize(price, this); // Gọi hàm changeSize với giá trị và chính nút đã bấm
        });
    });
</script>
</body>
</html>
