<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title><sitemesh:write property="title">Website</sitemesh:write></title>

    <!-- Bootstrap & Font Awesome -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">

    <!-- CSS chung -->
    <link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet"/>

    <!-- CSS/JS riêng của từng page -->
    <sitemesh:write property="head"/>
</head>
<body>
<%-- Header chung --%>
<div><%@ include file="/commons/header.jsp" %></div>

<%-- Nội dung (body sẽ có sidebar riêng của mỗi trang) --%>
<div class="d-flex">
    <sitemesh:write property="body"/>
</div>

<%-- Footer chung --%>
<div><%@ include file="/commons/footer.jsp" %></div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
