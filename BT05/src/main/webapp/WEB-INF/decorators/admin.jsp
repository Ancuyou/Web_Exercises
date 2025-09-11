<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 11/09/2025
  Time: 8:42 SA
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <%@ include file="/commons/header.jsp"%>--%>
<%--    <hr>--%>
<%--    <sitemesh:write property="body"/>--%>
<%--</head>--%>
<%--<body>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title">Admin Dashboard</sitemesh:write></title>

    <!-- Bootstrap & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/admin.css" rel="stylesheet">
    <!-- Giữ lại các thẻ <style> hoặc <script> trong từng page -->
    <sitemesh:write property="head"/>
</head>
<body style="background:#f5f7fb;">
<%-- Header chung --%>
<div><%@ include file="/commons/header.jsp" %></div>
<div class="layout d-flex flex-grow-1">
    <%-- Nội dung page (vd: home.jsp của admin) --%>
    <aside class="sidebar">
        <sitemesh:write property="sidebar"/>
    </aside>
    <div class="container-fluid py-3">
        <sitemesh:write property="body"/>
    </div>
</div>
<!-- Bootstrap Bundle + Chart.js -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@4.4.1/dist/chart.umd.min.js"></script>
</body>
</html>
