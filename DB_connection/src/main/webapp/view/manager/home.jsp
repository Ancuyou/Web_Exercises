<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 28/08/2025
  Time: 2:38 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Manager Workspace</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet">
    <style>
        body { background:#f8fafc; }
        .hero { background: linear-gradient(135deg,#845ef7,#228be6); color:#fff; border-radius: 1rem; }
        .card { border:0; box-shadow:0 8px 24px rgba(0,0,0,.08); border-radius:1rem; }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-white shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold text-primary" href="#"><i class="fa-solid fa-briefcase me-2"></i>Manager</a>
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
        <h2 class="fw-bold mb-2">Bàn làm việc của quản lý</h2>
        <p class="mb-0">Theo dõi tiến độ đơn hàng, tồn kho, phân công nhiệm vụ theo thời gian thực.</p>
    </section>

    <div class="row g-4">
        <div class="col-md-4">
            <div class="card p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <div class="text-secondary">Đơn chờ xử lý</div>
                        <div class="fs-3 fw-bold">148</div>
                    </div>
                    <i class="fa-solid fa-hourglass-half fs-3 text-secondary"></i>
                </div>
                <small class="text-danger"><i class="fa-solid fa-arrow-up"></i> +12 hôm nay</small>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <div class="text-secondary">Tồn kho thấp</div>
                        <div class="fs-3 fw-bold">27</div>
                    </div>
                    <i class="fa-solid fa-triangle-exclamation fs-3 text-secondary"></i>
                </div>
                <small class="text-warning">Cần nhập bổ sung</small>
            </div>
        </div>
        <div class="col-md-4">
            <div class="card p-3">
                <div class="d-flex justify-content-between">
                    <div>
                        <div class="text-secondary">Yêu cầu hỗ trợ</div>
                        <div class="fs-3 fw-bold">9</div>
                    </div>
                    <i class="fa-solid fa-headset fs-3 text-secondary"></i>
                </div>
                <small class="text-success">Đáp ứng trong 2h</small>
            </div>
        </div>
    </div>

    <div class="card p-4 mt-4">
        <h5 class="mb-3"><i class="fa-solid fa-list-check me-2"></i>Nhiệm vụ hôm nay</h5>
        <ul class="list-group list-group-flush">
            <li class="list-group-item d-flex align-items-center justify-content-between">
                Kiểm tra đơn hàng tồn &nbsp;<span class="badge text-bg-primary">Ưu tiên</span>
            </li>
            <li class="list-group-item d-flex align-items-center justify-content-between">
                Liên hệ NCC về lô hàng #XK-2025-08
                <span class="badge text-bg-secondary">Bình thường</span>
            </li>
            <li class="list-group-item d-flex align-items-center justify-content-between">
                Duyệt yêu cầu hoàn tiền #RF-10023
                <span class="badge text-bg-warning">Chờ duyệt</span>
            </li>
        </ul>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

