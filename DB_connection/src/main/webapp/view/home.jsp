<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28/08/2025
  Time: 2:39 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Trang chủ người dùng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        .hero { background: linear-gradient(135deg,#12b886,#15aabf); color:#fff; border-radius:1rem; }
        .feature-icon { width:48px;height:48px; display:grid; place-items:center; border-radius:12px; background:#f1fff6; }
        .card { border:0; box-shadow:0 8px 24px rgba(0,0,0,.08); border-radius:1rem; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold text-success" href="#"><i class="fa-solid fa-leaf me-2"></i>MyApp</a>
        <div class="ms-auto">
            <span class="me-3 text-secondary">Xin chào, <strong>${sessionScope.account.fullName}</strong></span>
            <a class="btn btn-outline-danger btn-sm" href="<c:url value='/logout'/>">
                <i class="fa-solid fa-right-from-bracket me-1"></i> Đăng xuất
            </a>
        </div>
    </div>
</nav>

<div class="container my-4">
    <section class="hero p-4 p-md-5 mb-4">
        <h2 class="fw-bold mb-2">Chào mừng trở lại!</h2>
        <p class="mb-0">Khám phá sản phẩm mới, theo dõi đơn hàng và cập nhật ưu đãi dành riêng cho bạn.</p>
    </section>

    <div class="row g-4">
        <div class="col-md-4">
            <div class="card p-4 h-100">
                <div class="d-flex align-items-center gap-3">
                    <div class="feature-icon"><i class="fa-solid fa-truck-fast text-success"></i></div>
                    <div>
                        <h5 class="mb-1">Đơn hàng gần đây</h5>
                        <small class="text-secondary">Xem trạng thái và lịch sử mua</small>
                    </div>
                </div>
                <a href="#" class="mt-3 btn btn-success">Xem ngay</a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-4 h-100">
                <div class="d-flex align-items-center gap-3">
                    <div class="feature-icon"><i class="fa-solid fa-heart text-danger"></i></div>
                    <div>
                        <h5 class="mb-1">Yêu thích</h5>
                        <small class="text-secondary">Sản phẩm bạn đã lưu</small>
                    </div>
                </div>
                <a href="#" class="mt-3 btn btn-outline-success">Mở danh sách</a>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-4 h-100">
                <div class="d-flex align-items-center gap-3">
                    <div class="feature-icon"><i class="fa-solid fa-user-gear text-primary"></i></div>
                    <div>
                        <h5 class="mb-1">Tài khoản</h5>
                        <small class="text-secondary">Cập nhật thông tin cá nhân</small>
                    </div>
                </div>
                <a href="<c:url value='/member/myaccount'/>" class="mt-3 btn btn-outline-primary">Cài đặt</a>
            </div>
        </div>
    </div>

    <div class="card p-4 mt-4">
        <h5 class="mb-3"><i class="fa-solid fa-gift me-2"></i>Ưu đãi cho bạn</h5>
        <div class="row g-3">
            <div class="col-md-3"><div class="p-3 rounded border">Miễn phí vận chuyển</div></div>
            <div class="col-md-3"><div class="p-3 rounded border">Giảm 20% đơn đầu tiên</div></div>
            <div class="col-md-3"><div class="p-3 rounded border">Voucher sinh nhật</div></div>
            <div class="col-md-3"><div class="p-3 rounded border">Quà tặng bí mật</div></div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

