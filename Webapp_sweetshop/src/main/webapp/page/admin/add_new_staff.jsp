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
    <title>Add New Staff</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="../../assets/image/favicon.ico" />
    <link rel="stylesheet" href="../../assets/css/backend-plugin.min.css">
    <link rel="stylesheet" href="../../assets/css/backend.css?v=1.0.0">
    <link rel="stylesheet" href="../../assets/vendor/line-awesome/dist/line-awesome/css/line-awesome.min.css">
    <link rel="stylesheet" href="../../assets/vendor/remixicon/fonts/remixicon.css">

    <link rel="stylesheet" href="../../assets/vendor/tui-calendar/tui-calendar/dist/tui-calendar.css">
    <link rel="stylesheet" href="../../assets/vendor/tui-calendar/tui-date-picker/dist/tui-date-picker.css">
    <link rel="stylesheet" href="../../assets/vendor/tui-calendar/tui-time-picker/dist/tui-time-picker.css">

</head>
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
                <div class="col-xl-12 col-lg-12">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between">
                            <div class="header-title">
                                <h4 class="card-title">New User Information</h4>
                            </div>
                        </div>
                        <div class="card-body">
                            <div class="new-user-info">
                                <!-- Hiển thị thông báo lỗi nếu có -->
                                <c:if test="${not empty message}">
                                    <div class="alert alert-info">${message}</div>
                                </c:if>

                                <form id="addStaffForm" action="/addstaff" method="POST" enctype="multipart/form-data" onsubmit="return validateForm()">
                                    <div class="row">
                                        <!-- Profile Image Section -->
                                        <div class="form-group col-md-12">
                                            <div class="crm-profile-img-edit position-relative">
                                                <img class="crm-profile-pic rounded avatar-100" id="profileImage"
                                                     src="${profileImage != null ? profileImage : '../../assets/avatar/11.png'}" alt="profile-pic">
                                                <div class="crm-p-image bg-primary">
                                                    <i class="las la-pen upload-button"></i>
                                                    <input class="file-upload" id="profilePic" name="profilePic" type="file" accept="image/jpeg" onchange="previewImage(event);">
                                                </div>
                                            </div>
                                            <div class="img-extension mt-3">
                                                <div class="d-inline-block align-items-center">
                                                    <span>Only</span>
                                                    <a href="javascript:void('#');">.jpg</a>
                                                    <span>allowed</span>
                                                </div>
                                                <div id="fileError" class="text-danger" style="display: none;"></div>
                                            </div>
                                        </div>

                                        <!-- Full Name -->
                                        <div class="form-group col-md-6">
                                            <label for="fullname">Full Name:</label>
                                            <input type="text" class="form-control" id="fullname" name="fullname" placeholder="Full Name"
                                                   value="${fullName != null ? fullName : ''}" oninput="validateField('fullname')">
                                            <div id="fullnameError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Date Of Birth -->
                                        <div class="form-group col-md-6">
                                            <label for="dob">Date Of Birth:</label>
                                            <input type="date" class="form-control" id="dob" name="dob" placeholder="Date Of Birth"
                                                   value="${dobParam != null ? dobParam : ''}" oninput="validateField('dob')">
                                            <div id="dobError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Gender -->
                                        <div class="form-group col-md-6">
                                            <label>Gender:</label>
                                            <select id="gender" name="gender" class="selectpicker form-control" data-style="py-0" onchange="validateField('gender')">
                                                <option value="" ${genderParam == '' ? 'selected' : ''}>Select Gender</option>
                                                <option value="Female" ${genderParam == 'Female' ? 'selected' : ''}>Female</option>
                                                <option value="Male" ${genderParam == 'Male' ? 'selected' : ''}>Male</option>
                                            </select>
                                            <div id="genderError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Status -->
                                        <div class="form-group col-md-6">
                                            <label>Status:</label>
                                            <select id="status" name="status" class="selectpicker form-control" data-style="py-0" onchange="validateField('status')">
                                                <option value="" ${statusParam == '' ? 'selected' : ''}>Select Status</option>
                                                <option value="Active" ${statusParam == 'Active' ? 'selected' : ''}>Active</option>
                                                <option value="Disable" ${statusParam == 'Disable' ? 'selected' : ''}>Disable</option>
                                            </select>
                                            <div id="statusError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Address -->
                                        <div class="form-group col-md-12">
                                            <label for="address">Address:</label>
                                            <input type="text" class="form-control" id="address" name="address" placeholder="Address"
                                                   value="${address != null ? address : ''}" oninput="validateField('address')">
                                            <div id="addressError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Mobile Number -->
                                        <div class="form-group col-md-12">
                                            <label for="mobno">Mobile Number:</label>
                                            <input type="text" class="form-control" id="mobno" name="mobno" placeholder="Mobile Number"
                                                   value="${phone != null ? phone : ''}" oninput="validateField('mobno')">
                                            <div id="mobnoError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Email -->
                                        <div class="form-group col-md-12">
                                            <label for="email">Email:</label>
                                            <input type="email" class="form-control" id="email" name="email" placeholder="Email"
                                                   value="${email != null ? email : ''}" oninput="validateField('email')">
                                            <div id="emailError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <hr>
                                        <h5 class="mb-3">Security</h5>

                                        <!-- Username -->
                                        <div class="form-group col-md-12">
                                            <label for="uname">User Name:</label>
                                            <input type="text" class="form-control" id="uname" name="uname" placeholder="User Name"
                                                   value="${username != null ? username : ''}" oninput="validateField('uname')">
                                            <div id="unameError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Password -->
                                        <div class="form-group col-md-6">
                                            <label for="pass">Password:</label>
                                            <input type="password" class="form-control" id="pass" name="pass" placeholder="Password"
                                                   value="${password != null ? password : ''}" oninput="validateField('pass')">
                                            <div id="passError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Repeat Password -->
                                        <div class="form-group col-md-6">
                                            <label for="rpass">Repeat Password:</label>
                                            <input type="password" class="form-control" id="rpass" name="rpass" placeholder="Repeat Password"
                                                   value="${repeatPassword != null ? repeatPassword : ''}" oninput="validateField('rpass')">
                                            <div id="rpassError" class="text-danger" style="display: none;"></div>
                                        </div>

                                        <!-- Toggle Password Visibility -->
                                        <div class="form-group col-md-12">
                                            <input type="checkbox" id="togglePassword" onclick="togglePasswordVisibility()"> Show Password
                                        </div>

                                        <button type="submit" class="btn btn-primary">Add New User</button>
                                    </div>
                                </form>
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

<%--// check inpit--%>
<script src="../../assets/app/js/addstaff.js"></script>

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
