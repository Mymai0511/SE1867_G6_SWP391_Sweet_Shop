<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10/1/2024
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Bài Đăng</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Danh Sách Bài Đăng</h1>
<a href="posts?action=new">Tạo Bài Đăng Mới</a>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Tiêu Đề</th>
        <th>Tác Giả</th>
        <th>Trạng Thái</th>
        <th>Ngày Tạo</th>
        <th>Hành Động</th>
    </tr>
    <c:forEach var="post" items="${listPost}">
        <tr>
            <td>${post.id}</td>
            <td>${post.title}</td>
            <td>${post.userID}</td> <!-- Có thể hiển thị tên người dùng thay vì ID -->
            <td>
                <c:choose>
                    <c:when test="${post.status == 1}">Công khai</c:when>
                    <c:otherwise>Nháp</c:otherwise>
                </c:choose>
            </td>
            <td>${post.createdAt}</td>
            <td>
                <a href="posts?action=view&id=${post.id}">Xem</a>
                <a href="posts?action=edit&id=${post.id}">Sửa</a>
                <a href="posts?action=delete&id=${post.id}" onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
