<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="vn.iotstar.models.UserDTO" %>
<%
    UserDTO u = (UserDTO) session.getAttribute("account");
    if (u == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
    request.setAttribute("u", u);
%>

<div class="sidebar">
    <div class="mb-4">
        <h5 class="fw-bold">User</h5>
        <small>Xin chào, <%= u.getFullName() %></small>
    </div>
    <a href="#"><i class="fa-solid fa-house me-2"></i> Trang chủ</a>
    <a href="<c:url value='/category?action=list'/>"><i class="fa-solid fa-box-open me-2"></i> Danh mục của tôi</a>
    <a href="#"><i class="fa-solid fa-heart me-2"></i> Yêu thích</a>
    <a href="<c:url value='/profile'/>"><i class="fa-solid fa-user-gear me-2"></i> Profile </a>
</div>

<div class="content">
    <section class="hero p-4 p-md-5 mb-4 bg-gradient rounded text-white"
             style="background: linear-gradient(135deg,#12b886,#15aabf);">
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
                <a href="<c:url value='/order/list'/>" class="btn btn-success mt-3">Xem đơn hàng</a>
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
