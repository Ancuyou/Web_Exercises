<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 04/09/2025
  Time: 3:02 CH
  To change this template use File | Settings | File Templates.
--%>

<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="jakarta.tags.core"%>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Categories</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--    <c:forEach items="${listCategory}" var="list">--%>
<%--        ${list.id}--%>
<%--        <br>--%>
<%--        ${list.categoryName}--%>
<%--    </c:forEach>--%>
<%--</body>--%>
<%--</html>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.iotstar.entities.Category" %>
<html>
<head>
    <title>Categories</title>
</head>
<body>
<%
    List<Category> listCategory = (List<Category>) request.getAttribute("listCategory");
    if (listCategory != null) {
        for (Category c : listCategory) {
%>
<p><%= c.getId() %> - <%= c.getCategoryName() %></p>
<%
    }
} else {
%>
<p>No category found</p>
<%
    }
%>
</body>
</html>

