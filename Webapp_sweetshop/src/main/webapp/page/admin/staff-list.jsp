<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <link rel="canonical" href="/staff-list.jsp" />
    <title>Staff List</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600&amp;display=swap" rel="stylesheet">
    <link class="js-stylesheet" href="../../assets/css/light.css" rel="stylesheet">

    <style>
        body {
            opacity: 0;
        }

        .pagination {
            margin-top: 20px; /* Space above pagination */
        }

        .pagination .btn {
            background-color: #007bff; /* Primary button color */
            border: none;
            color: white;
            transition: background-color 0.2s;
        }

        .pagination .btn:hover {
            background-color: #0056b3; /* Darker shade on hover */
        }

        .pagination .page-info {
            font-weight: bold;
            font-size: 1rem; /* Adjust font size */
            color: #333; /* Text color for better visibility */
        }

        .pagination .page-link {
            border: 1px solid #007bff;
            border-radius: 5px; /* Rounded corners for page numbers */
            width: 36px; /* Fixed width for buttons */
            height: 36px; /* Fixed height for buttons */
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 4px; /* Space between page number buttons */
            color: #007bff; /* Color for page numbers */
            background-color: white; /* Background color for buttons */
            transition: background-color 0.2s, color 0.2s;
        }

        .pagination .page-link:hover {
            background-color: #007bff; /* Background color on hover */
            color: white; /* Text color on hover */
        }

        .pagination .page-link.active {
            background-color: #007bff; /* Active page button color */
            color: white; /* Active text color */
        }

        .pagination .page-link.disabled {
            background-color: #e9ecef; /* Disabled button background */
            color: #6c757d; /* Disabled text color */
            cursor: not-allowed; /* Change cursor for disabled */
        }

    </style>
</head>

<body data-theme="default" data-layout="fluid" data-sidebar-position="left" data-sidebar-layout="default">
<div class="wrapper">
    <jsp:include page="../common/sidebar.jsp"></jsp:include>

    <div class="main">
        <jsp:include page="../common/navbar.jsp"></jsp:include>

        <main class="content">
            <div class="container-fluid p-0">
                <div class="mb-3">
                    <h1 class="h3 d-inline align-middle">List Staff</h1>
                    <a class="badge bg-primary ms-2" href="/getstaff" target="_blank"></a>
                </div>

                <div class="row">
                    <div class="col-xl-12">
                        <div class="card">
                            <div class="card-header pb-0 d-flex align-items-center justify-content-between">
                                <!-- Left side: Search button -->
                                <div class="left-side d-flex align-items-center w-45">
                                    <form class="d-none d-sm-inline-block flex-grow-1" action="/getstaffsearch" method="post">
                                        <div class="input-group input-group-navbar">
                                            <input type="text" name="search" class="form-control" placeholder="Search…" aria-label="Search"
                                                   value="${search != null ? search : ''}">
                                            <button class="btn" type="button">
                                                <i class="align-middle" data-feather="search"></i>
                                            </button>
                                        </div>
                                    </form>
                                </div>

                                <!-- Right side: Buttons and Dropdown -->
                                <div class="right-side d-flex align-items-center justify-content-end w-55">
                                    <div class="btn-group me-3" role="group" aria-label="Status buttons">
                                        <a type="button" class="btn btn-primary" href="/getstaff">All</a>
                                        <a type="button" class="btn btn-outline-primary" href="/staffactive">Active</a>
                                        <a type="button" class="btn btn-outline-primary" href="/staffdisable">Disable</a>
                                    </div>

                                    <div class="dropdown me-3">
                                        <button class="btn btn-secondary dropdown-toggle" type="button" id="sortDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                            Sort
                                        </button>
                                        <ul class="dropdown-menu" aria-labelledby="sortDropdown">
                                            <li><a class="dropdown-item" href="#">Name Ascending</a></li>
                                            <li><a class="dropdown-item" href="#">Name Descending</a></li>
                                            <li><a class="dropdown-item" href="#">Date of Birth (Oldest First)</a></li>
                                            <li><a class="dropdown-item" href="#">Date of Birth (Youngest First)</a></li>
                                        </ul>
                                    </div>

                                    <!-- Add New Staff Button -->
                                    <a class="btn btn-success me-3" href="/addstaff">Add New Staff</a>
                                </div>
                            </div>

                            <div class="card-body">
                                <table id="staffTable" class="table table-striped" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Date Of Birth</th>
                                        <th>Email</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody id="staffBody">
                                    <c:forEach var="staff" items="${requestScope.staffs}" varStatus="loopStatus">
                                        <tr class="staffRow">
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
                                                <a href="#" class="text-primary me-2" title="Edit">
                                                    <i class="align-middle" data-feather="edit"></i>
                                                </a>
                                                <a href="#" class="text-danger" title="Delete">
                                                    <i class="align-middle" data-feather="trash"></i>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <!-- Pagination Controls -->
                                <div id="paginationControls" class="pagination mt-4 d-flex justify-content-end align-items-center">
                                    <button id="prevButton" class="btn btn-secondary me-2" onclick="changePage(-1)">Previous</button>
<%--                                    <span id="pageInfo" class="page-info me-3">1</span>--%>
                                    <div id="pageNumbers" class="d-flex me-2">
                                        <!-- Page numbers will be generated here -->
                                    </div>
                                    <button id="nextButton" class="btn btn-secondary" onclick="changePage(1)">Next</button>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>

        <jsp:include page="../common/footer.jsp"></jsp:include>
    </div>
</div>
<script>
    window.onload = function() {
        // Lấy đường dẫn hiện tại của trang
        const currentPath = window.location.pathname;

        // Lấy tất cả các phần tử 'a' trong nhóm nút
        const buttons = document.querySelectorAll('.btn-group .btn');

        // Lặp qua từng nút để kiểm tra URL và áp dụng lớp active
        buttons.forEach(button => {
            if (button.getAttribute('href') === currentPath) {
                // Thêm lớp btn-primary cho nút đang được chọn
                button.classList.remove('btn-outline-primary');
                button.classList.add('btn-primary');
            } else {
                // Các nút khác giữ lớp btn-outline-primary
                button.classList.remove('btn-primary');
                button.classList.add('btn-outline-primary');
            }
        });
    };

</script>
<script src="../../assets/js/user/sidebar.js"></script>
<script src="../../assets/js/user/paging.js"></script>
<script src="../../assets/js/user/sortuser.js"></script>
<script src="../../assets/js/app.js"></script>
</body>
</html>
