<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 29/08/2025
  Time: 8:29 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Đặt lại mật khẩu</title>
</head>
<body>
<h2>Đặt lại mật khẩu</h2>
<form action="resetPassword" method="post">
  <label>Mật khẩu mới:</label>
  <input type="password" name="newPassword" required><br>
  <label>Nhập lại mật khẩu:</label>
  <input type="password" name="confirmPassword" required><br>
  <button type="submit">Đặt lại mật khẩu</button>
</form>
<c:if test="${alert != null}">
  <p style="color:red">${alert}</p>
</c:if>
</body>
</html>

