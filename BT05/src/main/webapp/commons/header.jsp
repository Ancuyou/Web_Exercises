<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="vn.iotstar.models.UserDTO" %>
<%
    UserDTO u = (UserDTO) session.getAttribute("account");
    request.setAttribute("u", u);
%>


<nav class="navbar navbar-expand-lg bg-white shadow-sm sticky-top">
    <div class="container-fluid">
        <a class="navbar-brand fw-bold text-primary" href="#">
            <i class="fa-solid fa-gauge-high me-2"></i>Admin
        </a>
        <div class="d-flex align-items-center">
            <c:if test="${not empty u}">
                <span class="me-3 text-secondary">
                    Xin chào, <strong><%= u != null ? u.getUsername() : "" %></strong>
                </span>
                <a class="btn btn-outline-danger btn-sm" href="<c:url value='/logout'/>">
                    <i class="fa-solid fa-right-from-bracket me-1"></i> Đăng xuất
                </a>
            </c:if>
        </div>
    </div>
</nav>
