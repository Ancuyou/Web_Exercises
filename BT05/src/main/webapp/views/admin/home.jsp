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

<%-- Sidebar --%>
<sitemesh:page property="sidebar">
    <aside class="sidebar p-3">
        <div class="mb-4">
            <div class="d-flex align-items-center gap-3">
                <i class="fa-solid fa-user-shield fa-2x text-white"></i>
                <div>
                    <div class="fw-semibold text-white"><%= u.getFullName() %></div>
                    <small class="text-light">Quyền: Admin</small>
                </div>
            </div>
        </div>
        <nav class="d-grid gap-2">
            <a class="active" href="#"><i class="fa-solid fa-chart-line me-2"></i>Tổng quan</a>
            <a href="#"><i class="fa-solid fa-users me-2"></i>Người dùng</a>
            <a href="<%= request.getContextPath() %>/category?action=list">
                <i class="fa-solid fa-box me-2"></i>Danh mục
            </a>
            <a href="<%= request.getContextPath() %>/profile">
                <i class="fa-solid fa-user-gear me-2"></i> Profile
            </a>
            <a href="#"><i class="fa-solid fa-file-invoice-dollar me-2"></i>Đơn hàng</a>
            <a href="#"><i class="fa-solid fa-gear me-2"></i>Cấu hình</a>
        </nav>
    </aside>
</sitemesh:page>

<main class="content p-4">
    <div class="container-fluid">

        <%-- Stats --%>
        <div class="row g-4">
            <div class="col-md-3">
                <div class="card stat-card">
                    <div class="d-flex justify-content-between">
                        <div>
                            <div class="text-secondary">Người dùng</div>
                            <div class="fs-3 fw-bold">12,480</div>
                        </div>
                        <i class="fa-solid fa-users stat-icon"></i>
                    </div>
                    <small class="text-success"><i class="fa-solid fa-arrow-up"></i> +4.2% tuần này</small>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card stat-card">
                    <div class="d-flex justify-content-between">
                        <div>
                            <div class="text-secondary">Doanh thu</div>
                            <div class="fs-3 fw-bold">₫ 958,200,000</div>
                        </div>
                        <i class="fa-solid fa-sack-dollar stat-icon"></i>
                    </div>
                    <small class="text-success"><i class="fa-solid fa-arrow-up"></i> +7.8%</small>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card stat-card">
                    <div class="d-flex justify-content-between">
                        <div>
                            <div class="text-secondary">Đơn hàng</div>
                            <div class="fs-3 fw-bold">2,134</div>
                        </div>
                        <i class="fa-solid fa-receipt stat-icon"></i>
                    </div>
                    <small class="text-danger"><i class="fa-solid fa-arrow-down"></i> -1.1%</small>
                </div>
            </div>
            <div class="col-md-3">
                <div class="card stat-card">
                    <div class="d-flex justify-content-between">
                        <div>
                            <div class="text-secondary">Tỷ lệ chuyển đổi</div>
                            <div class="fs-3 fw-bold">3.82%</div>
                        </div>
                        <i class="fa-solid fa-bullseye stat-icon"></i>
                    </div>
                    <small class="text-success"><i class="fa-solid fa-arrow-up"></i> +0.3%</small>
                </div>
            </div>
        </div>

        <%-- Chart + Table --%>
        <div class="row g-4 mt-1">
            <div class="col-lg-7">
                <div class="card p-4">
                    <h5 class="mb-3"><i class="fa-solid fa-chart-column me-2"></i>Doanh thu 12 tháng</h5>
                    <canvas id="revenueChart" height="120"></canvas>
                </div>
            </div>
            <div class="col-lg-5">
                <div class="card p-4">
                    <h5 class="mb-3"><i class="fa-solid fa-bell me-2"></i>Hoạt động mới</h5>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">User <strong>an.nguyen</strong> vừa đăng ký.</li>
                        <li class="list-group-item">Đơn #SO-10234 đã được xác nhận.</li>
                        <li class="list-group-item">Sản phẩm <em>AirPods Pro</em> hết hàng.</li>
                        <li class="list-group-item">Tạo role <strong>Manager</strong> mới.</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="card p-4 mt-4">
            <h5 class="mb-3"><i class="fa-solid fa-table me-2"></i>Top người dùng</h5>
            <div class="table-responsive">
                <table class="table align-middle table-hover">
                    <thead class="table-primary">
                    <tr>
                        <th>#</th><th>Tên</th><th>Email</th><th>Role</th><th>Trạng thái</th><th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td><td>Nguyễn Văn A</td><td>a@example.com</td><td>Admin</td>
                        <td><span class="badge bg-success">Hoạt động</span></td>
                        <td><button class="btn btn-sm btn-outline-primary">Chi tiết</button></td>
                    </tr>
                    <tr>
                        <td>2</td><td>Trần Thị B</td><td>b@example.com</td><td>Manager</td>
                        <td><span class="badge bg-warning">Tạm khóa</span></td>
                        <td><button class="btn btn-sm btn-outline-primary">Chi tiết</button></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>

    </div>
</main>

<script>
    const ctx = document.getElementById('revenueChart');
    new Chart(ctx, {
        type: 'line',
        data: {
            labels: ['1','2','3','4','5','6','7','8','9','10','11','12'],
            datasets: [{
                label: 'Doanh thu (triệu ₫)',
                data: [120,98,110,140,180,210,190,220,260,300,280,340],
                tension: .35,
                fill: true,
                borderColor: '#0d6efd',
                backgroundColor: 'rgba(13,110,253,.1)'
            }]
        },
        options: { plugins: { legend: { display: true }}, responsive: true }
    });
</script>
