<%-- Created by IntelliJ IDEA. User: ADMIN Date: 28/08/2025 Time: 10:22 SA --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.account == null}">
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li><a href="${pageContext.request.contextPath }/login">Đăng nhập</a></li>
                <li><a href="${pageContext.request.contextPath }/register">Đăng ký</a></li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:when>
    <c:otherwise>
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    <a href="${pageContext.request.contextPath }/member/myaccount">
                            ${sessionScope.account.fullName}
                    </a>
                </li>
                <li><a href="${pageContext.request.contextPath }/logout">Đăng xuất</a></li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    </c:otherwise>
</c:choose>
</body>
</html>
