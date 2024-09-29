<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Responsive Admin &amp; Dashboard Template based on Bootstrap 5">
    <meta name="author" content="AdminKit">
    <meta name="keywords" content="bootstrap, bootstrap 5, admin, dashboard, template, responsive, css, sass, html, theme, front-end, ui kit, web">

    <link rel="preconnect" href="https://fonts.gstatic.com">

    <link rel="shortcut icon" href="../../assets/image/icons/icon-48x48.png" />

    <link rel="canonical" href="/pages-clients.html" />

    <title>Staff List</title>

    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">

    <!-- Choose your prefered color scheme -->
    <!-- <link href="css/light.css" rel="stylesheet"> -->
    <!-- <link href="css/dark.css" rel="stylesheet"> -->

    <!-- BEGIN SETTINGS -->
    <!-- Remove this after purchasing -->
    <link class="js-stylesheet" href="../../assets/css/light.css" rel="stylesheet">
    <!-- <script src="../../assets/js/settings.js"></script>  -->

    <style>body {
        opacity: 0;
    }
    .pagination .page-item .page-link {
        color: #6c757d;
        border-radius: 50%;
        margin: 0 5px;
        width: 40px;
        height: 40px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1rem;
        border: 1px solid #dee2e6;
        background-color: #f8f9fa;
    }

    .pagination .page-item.active .page-link {
        background-color: #007bff;
        color: white;
        border-color: #007bff;
    }

    .pagination .page-item .page-link:hover {
        background-color: #e9ecef;
        border-color: #dee2e6;
    }

    .pagination .page-item.disabled .page-link {
        background-color: #e9ecef;
        color: #6c757d;
        cursor: not-allowed;
    }

    .pagination .page-item .page-link i {
        font-size: 0.875rem;
    }
    </style>
    <!-- END SETTINGS -->
    <script async src="https://www.googletagmanager.com/gtag/js?id=UA-120946860-10"></script>
    <script>
        window.dataLayer = window.dataLayer || [];
        function gtag(){dataLayer.push(arguments);}
        gtag('js', new Date());

        gtag('config', 'UA-120946860-10', { 'anonymize_ip': true });
    </script>
</head>
<!--
  HOW TO USE:
  data-theme: default (default), dark, light, colored
  data-layout: fluid (default), boxed
  data-sidebar-position: left (default), right
  data-sidebar-layout: default (default), compact
-->

<body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
<div class="wrapper">

    <jsp:include page = "../common/sidebar.jsp" ></jsp:include>

    <div class="main">

        <jsp:include page = "../common/navbar.jsp" ></jsp:include>

        <main class="content">
            <div class="container-fluid p-0">

                <div class="mb-3">
                    <h1 class="h3 d-inline align-middle">Clients</h1><a class="badge bg-primary ms-2" href="#"
                                                                        target="_blank"></a>
                    <i class="fas fa-fw fa-external-link-alt"></i>
                    </a>
                </div>

                <div class="row">
                    <div class="col-xl-12">
                        <div class="card">
                            <div class="card-header pb-0 d-flex align-items-center justify-content-between">
                                <!-- Left side: Search button -->
                                <div class="left-side d-flex align-items-center w-45">
                                    <form class="d-none d-sm-inline-block flex-grow-1">
                                        <div class="input-group input-group-navbar">
                                            <input type="text" class="form-control" placeholder="Search…" aria-label="Search">
                                            <button class="btn" type="button">
                                                <i class="align-middle" data-feather="search"></i>
                                            </button>
                                        </div>
                                    </form>
                                </div>

                                <!-- Right side: Buttons and Dropdown -->
                                <div class="right-side d-flex align-items-center justify-content-end w-55">
                                    <!-- 3 buttons: All, Active, Disable -->
                                    <div class="btn-group me-3" role="group" aria-label="Status buttons">
                                        <button type="button" class="btn btn-primary">All</button>
                                        <button type="button" class="btn btn-outline-primary">Active</button>
                                        <button type="button" class="btn btn-outline-primary">Disable</button>
                                    </div>

                                    <!-- Dropdown for sorting -->
                                    <div class="dropdown me-3">
                                        <button class="btn btn-secondary dropdown-toggle" type="button" id="sortDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                            Sort
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="sortDropdown">
                                            <li><a class="dropdown-item" href="#">Name Ascending</a></li>
                                            <li><a class="dropdown-item" href="#">Name Descending</a></li>
                                            <li><a class="dropdown-item" href="#">Creation Date Ascending</a></li>
                                            <li><a class="dropdown-item" href="#">Creation Date Descending</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>

                            <div class="card-body">
                                <!-- bang Staff -->
                                <table class="table table-striped" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Date Of Birth</th>
                                        <th>Email</th>
                                        <th>Status</th>
                                        <th>Action</th> <!-- Thêm cột Action -->
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="staff" items="${requestScope.staffs}" varStatus="loopStatus">
                                    <tr>
                                        <td><img src="../../assets/image/avatars/avatar.jpg" width="32" height="32" class="rounded-circle my-n1" alt="Avatar"></td>
                                        <td>${staff.fullName}</td>
                                        <td>${staff.dob}</td>
                                        <td>${staff.email}</td>
                                        <td>
                                             <span class="badge ${staff.status == 1 ? 'bg-primary' : 'bg-danger'}">
                                                ${staff.status == 1 ? 'Active' : 'Disable'}
                                            </span>
                                        </td>
                                        <td>
                                            <!-- Action icons -->
                                            <a href="#" class="text-primary me-2" title="Edit">
                                                <i class="align-middle" data-feather="edit"></i>
                                            </a>
                                            <a href="#" class="text-danger" title="Delete">
                                                <i class="align-middle" data-feather="trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    <!-- You can add more rows similarly -->
                                    </tbody>
                                </table>
                            </div>

                            <!-- phân trang -->

                            <div class="card-footer d-flex align-items-center justify-content-between">
                                <span class="text-muted">Showing 1 to 10</span>
                                <nav aria-label="Page navigation">
                                    <ul class="pagination mb-0">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" aria-label="Previous">
                                                <i class="fas fa-chevron-left"></i> <!-- Biểu tượng mũi tên trái -->
                                            </a>
                                        </li>
                                        <li class="page-item active">
                                            <a class="page-link" href="#">1</a>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link" href="#">2</a>
                                        </li>
                                        <li class="page-item disabled">
                                            <span class="page-link">...</span>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link" href="#" aria-label="Next">
                                                <i class="fas fa-chevron-right"></i> <!-- Biểu tượng mũi tên phải -->
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <jsp:include page = "../common/footer.jsp" ></jsp:include>

    </div>
</div>
<script src="../../assets/js/app.js"></script>
</body>
</html>
