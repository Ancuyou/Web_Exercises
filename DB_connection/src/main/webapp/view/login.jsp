<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Đăng nhập</title>
</head>
<body>
<form action="login" method="post">
    <h2>Đăng nhập tài khoản</h2>
    <c:if test="${alert != null}">
        <h3 class="alert alert-danger">${alert}</h3>
    </c:if>
    <section>
        <label class="input login-input">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" placeholder="Tài khoản"
                       name="username"
                       class="form-control" required>
            </div>
        </label>
    </section>
    <section>
        <label class="input login-input">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                <input type="password" placeholder="Mật khẩu"
                       name="password"
                       class="form-control" required>
            </div>
        </label>
    </section>
    <section>
        <label>
            <input type="checkbox" name="remember"> Ghi nhớ đăng nhập
        </label>
    </section>
    <section>
        <button type="submit" class="btn btn-primary">Đăng nhập</button>
    </section>
</form>
</body>
</html>
