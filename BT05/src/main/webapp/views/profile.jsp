<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="vn.iotstar.entities.User" %>

<%
    User user = (User) request.getAttribute("user");
%>

<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-lg rounded-4">
                <div class="card-header bg-primary text-white">
                    <h4 class="mb-0"><i class="fa-solid fa-user-gear me-2"></i> Cập nhật hồ sơ</h4>
                </div>
                <div class="card-body p-4">
                    <c:if test="${param.success == 1}">
                        <div class="alert alert-success">Cập nhật thành công!</div>
                    </c:if>

                    <form action="<c:url value='/profile'/>" method="post" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label class="form-label">Họ tên</label>
                            <input type="text" name="fullName" class="form-control"
                                   value="<%= user != null ? user.getFullName() : "" %>" required>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Số điện thoại</label>
                            <input type="text" name="phone" class="form-control"
                                   value="<%= user != null ? user.getPhone() : "" %>">
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Ảnh đại diện</label>
                            <input type="file" name="avatar" class="form-control">
                            <c:if test="${not empty user.avatar}">
                                <div class="mt-2">
                                    <img src="<c:url value='/${user.avatar}'/>" alt="Avatar"
                                         class="rounded-circle" width="80" height="80">
                                </div>
                            </c:if>
                        </div>

                        <button type="submit" class="btn btn-primary w-100">
                            <i class="fa-solid fa-save me-1"></i> Lưu thay đổi
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

