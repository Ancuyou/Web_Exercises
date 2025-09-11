
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.iotstar.models.CategoryDTO" %>
<%@ page import="vn.iotstar.models.UserDTO" %>
<%
    List<CategoryDTO> cateList = (List<CategoryDTO>) request.getAttribute("cateList");
    if (cateList == null) {
        cateList = new java.util.ArrayList<>();
    }
    String error = (String) request.getAttribute("error");
    UserDTO user = (UserDTO) session.getAttribute("account");
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>DANH SÁCH CATEGORY</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f8;
            margin: 0;
            padding: 0;
        }
        h2 {
            text-align: center;
            margin: 20px 0;
            color: #2c3e50;
        }
        .container {
            width: 90%;
            max-width: 1200px;
            margin: 0 auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }
        .table-container {
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }
        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #4CAF50;
            color: #fff;
            font-size: 16px;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        img {
            max-width: 120px;
            max-height: 80px;
            object-fit: contain;
            border-radius: 6px;
            cursor: pointer;
            transition: transform 0.2s;
        }
        img:hover {
            transform: scale(1.05);
        }
        .btn {
            display: inline-block;
            padding: 10px 18px;
            margin: 5px;
            text-decoration: none;
            color: #fff;
            background-color: #4CAF50;
            border-radius: 4px;
            transition: 0.3s;
            font-weight: bold;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .action-links a {
            margin: 0 8px;
            text-decoration: none;
            color: #3498db;
            font-weight: 500;
        }
        .action-links a:hover {
            color: #2980b9;
        }
        .add-btn-container {
            text-align: center;
            margin-top: 20px;
        }
        /* Search bar */
        .search-box {
            text-align: right;
            margin-bottom: 15px;
        }
        .search-box input {
            padding: 8px;
            width: 250px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        /* Modal for image preview */
        .modal {
            display: none;
            position: fixed;
            z-index: 999;
            padding-top: 50px;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0,0,0,0.7);
        }
        .modal-content {
            margin: auto;
            display: block;
            max-width: 80%;
            max-height: 80%;
        }
        .close {
            position: absolute;
            top: 30px;
            right: 40px;
            color: #fff;
            font-size: 40px;
            font-weight: bold;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h2>Danh sách Category</h2>
<div class="container">
    <div class="search-box">
        <input type="text" id="searchInput" placeholder="Tìm kiếm danh mục...">
    </div>
    <div class="table-container">
        <table id="categoryTable">
            <thead>
            <tr>
                <th>STT</th>
                <th>Icon</th>
                <th>Tên danh mục</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <%
                int index = 1;
                for (CategoryDTO cate : cateList) {
            %>
            <tr>
                <td><%= index++ %></td>
                <td>
                    <img src="<%= request.getContextPath() + "/image?fname=" + cate.getImages() %>"
                         alt="Icon" onclick="showModal(this)">
                </td>
                <td><%= cate.getCategoryName() %></td>
                <td class="action-links">
                    <a href="<%= request.getContextPath() + "/category?action=edit&id=" + cate.getId() %>">Sửa</a> |
                    <a href="<%= request.getContextPath() + "/category?action=delete&id=" + cate.getId()%>"
                       onclick="return confirm('Bạn có chắc chắn muốn xóa danh mục này?');">Xóa</a>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>

    <div class="add-btn-container">
        <a href="<%= request.getContextPath() + "/category?action=add" %>" class="btn">+ Thêm Category</a>
    </div>
</div>

<!-- Modal for image preview -->
<div id="imgModal" class="modal">
    <span class="close" onclick="closeModal()">&times;</span>
    <img class="modal-content" id="modalImg">
</div>

<script>
    // Tìm kiếm category
    document.getElementById("searchInput").addEventListener("keyup", function () {
        let filter = this.value.toLowerCase();
        let rows = document.querySelectorAll("#categoryTable tbody tr");
        rows.forEach(row => {
            let text = row.textContent.toLowerCase();
            row.style.display = text.includes(filter) ? "" : "none";
        });
    });

    // Xem ảnh lớn trong modal
    function showModal(img) {
        document.getElementById("imgModal").style.display = "block";
        document.getElementById("modalImg").src = img.src;
    }
    function closeModal() {
        document.getElementById("imgModal").style.display = "none";
    }
</script>
</body>
</html>

