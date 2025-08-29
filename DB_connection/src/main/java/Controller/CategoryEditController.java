package Controller;

import Models.Category;
import Service.CategoryService;
import Service.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@WebServlet("/admin/category/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 5 * 1024 * 1024,   // 5MB
        maxRequestSize = 10 * 1024 * 1024) // 10MB
public class CategoryEditController extends HttpServlet {
    private final CategoryService cateService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        Category category = cateService.get(Integer.parseInt(id));
        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
        dispatcher.forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        int cateId = Integer.parseInt(req.getParameter("cateid"));
        String catename = req.getParameter("catename");
        Category category = new Category();
        category.setCateid(cateId);
        category.setCatename(catename);
        // Xử lý upload file (icon)
        Part filePart = req.getPart("icon"); // input name="icon"
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();
            // Lưu vào thư mục uploads/category trong webapp
            String uploadDir = getServletContext().getRealPath("/uploads/category/");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();
            // Xóa file cũ nếu tồn tại
            Category oldCategory = cateService.get(cateId);
            if (oldCategory.getIcon() != null && !oldCategory.getIcon().isEmpty()) {
                File oldFile = new File(getServletContext().getRealPath(oldCategory.getIcon()));
                if (oldFile.exists()) oldFile.delete();
            }
            // Lưu file mới
            String newFilePath = uploadDir + File.separator + fileName;
            filePart.write(newFilePath);
            category.setIcon("/uploads/category/" + fileName); // lưu path tương đối cho DB
        }
        // Cập nhật vào DB
        cateService.edit(category);
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}
