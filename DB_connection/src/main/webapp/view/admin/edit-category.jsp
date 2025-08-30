<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Models.Category" %>

<%
  Category category = (Category) request.getAttribute("category");
  if (category == null) {
    category = new Category(); // tránh NullPointerException
  }
  String error = (String) request.getAttribute("error");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Chỉnh sửa danh mục</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    body {
      background-color: #f8f9fa;
    }
    .edit-container {
      max-width: 600px;
      margin: 50px auto;
      background: #fff;
      border-radius: 12px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      padding: 30px;
    }
    .form-label {
      font-weight: 600;
    }
    .img-preview {
      display: block;
      margin: 10px 0;
      max-height: 120px;
      border: 2px solid #ddd;
      border-radius: 8px;
    }
    .btn-custom {
      width: 48%;
    }
  </style>
</head>
<body>

<div class="edit-container">
  <h3 class="text-center mb-4">CHỈNH SỬA DANH MỤC</h3>

  <form action="<%= request.getContextPath() + "/admin/category/edit" %>" method="post" enctype="multipart/form-data">
    <!-- Hidden ID -->
    <input type="hidden" name="id" value="<%= category.getCateid() %>" />

    <!-- Tên danh mục -->
    <div class="mb-3">
      <label for="cateName" class="form-label">Tên danh mục</label>
      <input type="text" class="form-control" id="cateName" name="name"
             value="<%= category.getCatename() == null ? "" : category.getCatename() %>"
             placeholder="Nhập tên danh mục" required>
    </div>

    <!-- Ảnh hiện tại -->
    <div class="mb-3">
      <label class="form-label">Ảnh hiện tại</label><br>
      <% if (category.getIcon() != null && !category.getIcon().isEmpty()) { %>
      <img src="<%= request.getContextPath() + "/image?fname=" + category.getIcon() %>"
           alt="Icon hiện tại" class="img-preview">
      <% } %>
    </div>

    <!-- Upload ảnh mới -->
    <div class="mb-3">
      <label for="icon" class="form-label">Chọn ảnh mới</label>
      <input type="file" name="icon" id="icon" class="form-control">
    </div>

    <!-- Nút bấm -->
    <div class="d-flex justify-content-between">
      <button type="submit" class="btn btn-success btn-custom">Cập nhật</button>
      <button type="reset" class="btn btn-secondary btn-custom">Làm mới</button>
    </div>
  </form>

  <!-- Hiển thị lỗi -->
  <% if (error != null && !error.isEmpty()) { %>
  <div class="alert alert-danger mt-3"><%= error %></div>
  <% } %>
</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
