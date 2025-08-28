<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28/08/2025
  Time: 3:35 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng ký tài khoản</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<form action="register" method="post">
    <h2>Tạo tài khoản mới</h2>

    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>

    <!-- Username -->
    <section>
        <label class="input login-input">
            <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-user"></i>
                    </span>
                <input type="text" name="username"
                       placeholder="Tài khoản"
                       class="form-control" required>
            </div>
        </label>
    </section>

    <!-- Full name -->
    <section>
        <label class="input login-input">
            <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-user-circle"></i>
                    </span>
                <input type="text" name="fullname"
                       placeholder="Họ tên"
                       class="form-control" required>
            </div>
        </label>
    </section>

    <!-- Email -->
    <section>
        <label class="input login-input">
            <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-envelope"></i>
                    </span>
                <input type="email" name="email"
                       placeholder="Nhập Email"
                       class="form-control" required>
            </div>
        </label>
    </section>

    <!-- Phone -->
    <section>
        <label class="input login-input">
            <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-phone"></i>
                    </span>
                <input type="text" name="phone"
                       placeholder="Số điện thoại"
                       class="form-control">
            </div>
        </label>
    </section>

    <!-- Password -->
    <section>
        <label class="input login-input">
            <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-lock"></i>
                    </span>
                <input type="password" name="password"
                       placeholder="Mật khẩu"
                       class="form-control" required>
            </div>
        </label>
    </section>

    <!-- Confirm password -->
    <section>
        <label class="input login-input">
            <div class="input-group">
                    <span class="input-group-addon">
                        <i class="fa fa-lock"></i>
                    </span>
                <input type="password" name="confirmPassword"
                       placeholder="Nhập lại mật khẩu"
                       class="form-control" required>
            </div>
        </label>
    </section>

    <button type="submit" class="btn btn-primary">Tạo tài khoản</button>

    <p>Nếu bạn đã có tài khoản?
        <a href="${pageContext.request.contextPath}/login">Đăng nhập</a>
    </p>
</form>
</body>
</html>

