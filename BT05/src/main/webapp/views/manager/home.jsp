<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="vn.iotstar.models.UserDTO" %>
<%
    UserDTO u = (UserDTO) session.getAttribute("account");
    if (u == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>

<div class="sidebar">
    <div class="mb-4">
        <h5 class="fw-bold">Manager</h5>
        <small>Bàn làm việc</small>
    </div>
    <a href="#"><i class="fa-solid fa-briefcase me-2"></i> Tổng quan</a>
    <a href="<c:url value='/category?action=list'/>"><i class="fa-solid fa-box me-2"></i> Danh mục</a>
    <a href="<c:url value='/profile'/>"><i class="fa-solid fa-user-gear me-2"></i> Profile </a>
    <a href="#"><i class="fa-solid fa-receipt me-2"></i> Đơn hàng</a>
    <a href="#"><i class="fa-solid fa-warehouse me-2"></i> Kho hàng</a>
</div>

<div class="content">
    <section class="hero p-4 p-md-5 mb-4 bg-gradient rounded text-white"
             style="background: linear-gradient(135deg,#845ef7,#228be6);">
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
            <li class="list-group-item d-flex justify-content-between">
                Kiểm tra đơn hàng tồn <span class="badge text-bg-primary">Ưu tiên</span>
            </li>
            <li class="list-group-item d-flex justify-content-between">
                Liên hệ NCC về lô hàng #XK-2025-08
                <span class="badge text-bg-secondary">Bình thường</span>
            </li>
            <li class="list-group-item d-flex justify-content-between">
                Duyệt yêu cầu hoàn tiền #RF-10023
                <span class="badge text-bg-warning">Chờ duyệt</span>
            </li>
        </ul>
    </div>
</div>
