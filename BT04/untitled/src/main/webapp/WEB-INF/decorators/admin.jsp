<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/09/2025
  Time: 8:42 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <title><sitemesh:write property="title" default="Admin"/></title>

    <!-- đặt Bootstrap + FontAwesome global ở đây -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet"/>

    <!-- phần head của từng view (nếu view có) sẽ được chèn vào đây -->
    <sitemesh:write property="head"/>
</head>
<body>
    <%@ include file="/commons/header.jsp"%>
    <hr/>

    <!-- nội dung view -->
    <sitemesh:write property="body"/>

    <!-- global scripts: đặt sau body để DOM đã có -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>

    <!-- cho phép view chèn script vào cuối trang nếu cần (bằng request attr hoặc property footer) -->
    <sitemesh:write property="footer"/>
</body>
</html>

