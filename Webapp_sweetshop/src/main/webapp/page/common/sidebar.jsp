<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--begin of sidebar-->
<nav id="sidebar" class="sidebar js-sidebar">
    <div class="sidebar-content js-simplebar">
        <a class="sidebar-brand" href="index.html">
					<span class="sidebar-brand-text align-middle">
						Sweet Shop
                    </span>
            <svg class="sidebar-brand-icon align-middle" width="32px" height="32px" viewBox="0 0 24 24" fill="none" stroke="#FFFFFF" stroke-width="1.5"
                 stroke-linecap="square" stroke-linejoin="miter" color="#FFFFFF" style="margin-left: -3px">
                <path d="M12 4L20 8.00004L12 12L4 8.00004L12 4Z"></path>
                <path d="M20 12L12 16L4 12"></path>
                <path d="M20 16L12 20L4 16"></path>
            </svg>
        </a>

        <div class="sidebar-user">
            <div class="d-flex justify-content-center">
                <div class="flex-shrink-0">
                    <img src="../../assets/image/avatars/avatar.jpg" class="avatar img-fluid rounded me-1" alt="Charles Hall" />
                </div>
                <div class="flex-grow-1 ps-2">
                    <a class="sidebar-user-title dropdown-toggle" href="#" data-bs-toggle="dropdown">
                        Charles Hall
                    </a>
                    <div class="dropdown-menu dropdown-menu-start">
                        <a class="dropdown-item" href="pages-profile.html"><i class="align-middle me-1" data-feather="user"></i> Profile</a>
                        <a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="pie-chart"></i> Analytics</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="pages-settings.html"><i class="align-middle me-1" data-feather="settings"></i> Settings &
                            Privacy</a>
                        <a class="dropdown-item" href="#"><i class="align-middle me-1" data-feather="help-circle"></i> Help Center</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="#">Log out</a>
                    </div>

                    <div class="sidebar-user-subtitle">Designer</div>
                </div>
            </div>
        </div>

        <ul class="sidebar-nav">
            <li class="sidebar-header">
                Pages
            </li>
            <li class="sidebar-item">
                <a class="sidebar-link" href="#">
                    <i class="align-middle" data-feather="sliders"></i> <span class="align-middle">Dashboards</span>
                </a>
            </li>

            <li class="sidebar-item ">
                <a data-bs-target="#pages" data-bs-toggle="collapse" class="sidebar-link">
                    <i class="align-middle" data-feather="users"></i> <span class="align-middle">Manager Customers</span>
                </a>
                <ul id="customer" class="sidebar-dropdown list-unstyled collapse show" data-bs-parent="#sidebar">
                    <li class="sidebar-item"><a class="sidebar-link" href="/getcustomer">List Customers</a></li>
                    <li class="sidebar-item"><a class="sidebar-link" href="/addcustomer">Add New Customer</a></li>
                </ul>
            </li>

            <li class="sidebar-item ">
                <a data-bs-target="#pages" data-bs-toggle="collapse" class="sidebar-link">
                    <i class="align-middle" data-feather="users"></i> <span class="align-middle">Manager Staffs</span>
                </a>
                <ul id="staff" class="sidebar-dropdown list-unstyled collapse show" data-bs-parent="#sidebar">
                    <li class="sidebar-item"><a class="sidebar-link" href="/getstaff">List Staffs</a></li>
                    <li class="sidebar-item"><a class="sidebar-link" href="/addstaff">Add New Staff</a></li>
                </ul>
            </li>
            <li class="sidebar-item ">
                <a data-bs-target="#pages" data-bs-toggle="collapse" class="sidebar-link">
                    <i class="align-middle" data-feather="users"></i> <span class="align-middle">Manager Shippers</span>
                </a>
                <ul id="shipper" class="sidebar-dropdown list-unstyled collapse show" data-bs-parent="#sidebar">
                    <li class="sidebar-item"><a class="sidebar-link" href="/getshipper">List Shippers</a></li>
                    <li class="sidebar-item"><a class="sidebar-link" href="/addshipper">Add New Shipper</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
