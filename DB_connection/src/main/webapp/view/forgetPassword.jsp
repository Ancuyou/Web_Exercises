<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 29/08/2025
  Time: 8:16 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quên mật khẩu</title>
</head>
<body>
    <h2> Quên mật khẩu </h2>
    <form action="forgetPassword" method="post">
        <label>Email hoặc SDT:</label>
        <input type="text" name="identifier" required>
        <button type="submit">Tiếp tục</button>
    </form>
    <c:if test="${alert != null}">
        <p style="color:red">${alert}</p>
    </c:if>
</body>
</html>
