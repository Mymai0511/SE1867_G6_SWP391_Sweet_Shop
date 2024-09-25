<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 9/19/2024
  Time: 8:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Staff List</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="../../assets/image/favicon.ico" />
    <link rel="stylesheet" href="../../assets/css/backend-plugin.min.css">
    <link rel="stylesheet" href="../../assets/css/backend.css?v=1.0.0">
    <link rel="stylesheet" href="../../assets/vendor/line-awesome/dist/line-awesome/css/line-awesome.min.css">
    <link rel="stylesheet" href="../../assets/vendor/remixicon/fonts/remixicon.css">

    <link rel="stylesheet" href="../../assets/vendor/tui-calendar/tui-calendar/dist/tui-calendar.css">
    <link rel="stylesheet" href="../../assets/vendor/tui-calendar/tui-date-picker/dist/tui-date-picker.css">
    <link rel="stylesheet" href="../../assets/vendor/tui-calendar/tui-time-picker/dist/tui-time-picker.css">  </head>
<body class="  ">
<!-- loader Start -->
<div id="loading">
    <div id="loading-center">
    </div>
</div>
<!-- loader END -->

<!-- Wrapper Start -->
<div class="wrapper">
    <!-- sidebar -->
    <jsp:include page = "../common/sidebar.jsp" ></jsp:include>

    <!-- navbar -->
    <jsp:include page = "../common/navbar.jsp" ></jsp:include>

    <div class="content-page">
    <div class="container-fluid">
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header d-flex justify-content-between">
                        <div class="header-title">
                            <h4 class="card-title">Staff List</h4>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <div class="row justify-content-between">
                                <div class="col-sm-6 col-md-6">
                                    <div id="user_list_datatable_info" class="dataTables_filter">
                                        <form class="mr-3 position-relative" action="/getstaffsearch" method="post">
                                            <div class="form-group mb-0">
                                                <input type="search" name="search" class="form-control" id="exampleInputSearch" placeholder="Search"
                                                       aria-controls="user-list-table">
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-6">
                                    <div class="d-flex flex-wrap align-items-center">
                                        <div class="btn-group btn-group-toggle btn-group-edges mr-2 btn-group1">
                                            <a class="button btn button-icon bg-primary" target="_blank" href="/getstaff">All</a>
                                            <a class="button btn button-icon bg-primary" target="_blank" href="/staffactive">Active</a>
                                            <a class="button btn button-icon bg-primary" target="_blank" href="/staffdisable">Disable</a>
                                        </div>
                                        <div class="pl-3 btn-new border-left">
                                            <a href="/addstaff" class="btn btn-primary" data-toggle="modal">Add New Staff</a>
                                        </div>
                                    </div>
                                    <!-- <div class="user-list-files d-flex">
                                       <a class="bg-primary" href="javascript:void();">
                                          Print
                                       </a>
                                       <a class="bg-primary" href="javascript:void();">
                                          Excel
                                       </a>
                                       <a class="bg-primary" href="javascript:void();">
                                          Pdf
                                       </a>
                                    </div> -->
                                </div>
                            </div>
                            <table id="user-list-table" class="table table-striped dataTable mt-4" role="grid"
                                   aria-describedby="user-list-page-info">
                                <thead>
                                <tr class="ligth">
                                    <th>#</th>
                                    <th>Profile</th>
                                    <th onclick="sortTable(2)">Name</th>
                                    <th onclick="sortTable(3)">Gender</th>
                                    <th onclick="sortTable(4)">Date of Birth</th>
                                    <th onclick="sortTable(5)">Status</th>
                                    <th style="min-width: 100px">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="staff" items="${requestScope.staffs}" varStatus="loopStatus">
                                    <tr>
                                        <td>${loopStatus.count}</td>
                                        <td class="text-center"><img class="rounded img-fluid avatar-40"
                                                                     src="${staff.avatar}"
                                                                     alt="profile"></td>
<%--                                        <td>${staff.fName}</td>--%>
                                        <td>${staff.fullName}</td>
                                        <td>${staff.isGender() ? "Male" : "Female"}</td>
                                        <td>${staff.dob}</td>
                                        <td>
                                <span class="badge ${staff.status == 1 ? 'bg-primary' : 'bg-danger'}">
                                        ${staff.status == 1 ? 'Active' : 'Disable'}
                                </span>
                                        </td>
                                        <td>
                                            <div class="flex align-items-center list-user-action">
                                                <a class="btn btn-sm bg-primary" data-toggle="tooltip" data-placement="top" title=""
                                                   data-original-title="Add" href="#"><i class="ri-user-add-line mr-0"></i></a>
                                                <a class="btn btn-sm bg-primary" data-toggle="tooltip" data-placement="top" title=""
                                                   data-original-title="Edit" href="#"><i class="ri-pencil-line mr-0"></i></a>
                                                <a class="btn btn-sm bg-primary" data-toggle="tooltip" data-placement="top" title=""
                                                   data-original-title="Delete" href="#"><i class="ri-delete-bin-line mr-0"></i></a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="row justify-content-between mt-3">
                            <div id="user-list-page-info" class="col-md-6">
                                <span>Showing 1 to 5 of 5 entries</span>
                            </div>
                            <div class="col-md-6">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination justify-content-end mb-0">
                                        <li class="page-item disabled">
                                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                                        </li>
                                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                                        <li class="page-item">
                                            <a class="page-link" href="#">Next</a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<!-- Wrapper End-->

<!-- Modal list start -->
<jsp:include page = "../common/modal-list.jsp" ></jsp:include>

<!-- footer start -->
<footer class="iq-footer">
    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-6">
                <ul class="list-inline mb-0">
                    <li class="list-inline-item"><a href="../backend/privacy-policy.html">Privacy Policy</a></li>
                    <li class="list-inline-item"><a href="../backend/terms-of-service.html">Terms of Use</a></li>
                </ul>
            </div>
            <div class="col-lg-6 text-right">
                <span class="mr-1"><script>document.write(new Date().getFullYear())</script>©</span> <a href="#" class="">Webkit</a>.
            </div>
        </div>
    </div>
</footer>
<!-- footer end -->
<script>
    var dir = "asc"; // Hướng sắp xếp mặc định

    function sortTable(n) {
        var table, rows, switching, i, x, y, shouldSwitch, switchcount = 0;
        table = document.getElementById("user-list-table");
        switching = true;

        while (switching) {
            switching = false;
            rows = table.rows;

            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];

                // So sánh dựa trên cột
                if (n === 2) { // Name
                    if (dir === "asc") {
                        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    } else {
                        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                } else if (n === 3) { // Gender
                    if (dir === "asc") {
                        if (x.innerHTML > y.innerHTML) {
                            shouldSwitch = true;
                            break;
                        }
                    } else {
                        if (x.innerHTML < y.innerHTML) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                } else if (n === 4) { // Date of Birth
                    var dateX = new Date(x.innerHTML);
                    var dateY = new Date(y.innerHTML);
                    if (dir === "asc") {
                        if (dateX > dateY) {
                            shouldSwitch = true;
                            break;
                        }
                    } else {
                        if (dateX < dateY) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                } else if (n === 5) { // Status
                    if (dir === "asc") {
                        if (x.innerHTML > y.innerHTML) {
                            shouldSwitch = true;
                            break;
                        }
                    } else {
                        if (x.innerHTML < y.innerHTML) {
                            shouldSwitch = true;
                            break;
                        }
                    }
                }
            }

            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchcount++;
            } else {
                if (switchcount === 0 && dir === "asc") {
                    dir = "desc"; // Đổi hướng sắp xếp
                } else {
                    dir = "asc";
                }
            }
        }
    }
</script>



<!-- Backend Bundle JavaScript -->
<script src="../../assets/js/backend-bundle.min.js"></script>

<!-- Table Treeview JavaScript -->
<script src="../../assets/js/table-treeview.js"></script>

<!-- Chart Custom JavaScript -->
<script src="../../assets/js/customizer.js"></script>

<!-- Chart Custom JavaScript -->
<script async src="../../assets/js/chart-custom.js"></script>
<!-- Chart Custom JavaScript -->
<script async src="../../assets/js/slider.js"></script>

<!-- app JavaScript -->
<script src="../../assets/js/app.js"></script>

<script src="../../assets/vendor/moment.min.js"></script>
</body>
</html>
