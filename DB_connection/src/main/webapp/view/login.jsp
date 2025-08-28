<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28/08/2025
  Time: 9:28 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login" method="post">
    <h2>Tạo tài khoản mới</h2>

    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>

    <section>
        <label class="input login-input">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" placeholder="Tài khoản"
                       name="username"
                       class="form-control">
            </div>
        </label>
    </section>
</form>
</body>
</html>
