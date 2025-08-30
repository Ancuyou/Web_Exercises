package Controller;

import Models.Constant;
import Models.Category;
import Service.CategoryService;
import Service.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// OLD: import org.apache.commons.fileupload.disk.DiskFileItemFactory;
// OLD: import org.apache.commons.fileupload.servlet.ServletFileUpload;
// OLD: import org.apache.commons.fileupload.FileItem;

// NEW: imports cho commons-fileupload2-jakarta
import org.apache.commons.fileupload2.core.DiskFileItemFactory;  // NEW
import org.apache.commons.fileupload2.core.FileItem;           // NEW
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload; // NEW

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category/edit" })
public class CategoryEditController extends HttpServlet {
    private final CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
            return;
        }
        Category category = cateService.get(Integer.parseInt(id));

        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Category category = new Category();

        // OLD:
        // DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        // servletFileUpload.setHeaderEncoding("UTF-8");

        // NEW:
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get(); // NEW
        JakartaServletFileUpload fileUpload = new JakartaServletFileUpload(factory); // NEW
        fileUpload.setHeaderCharset(StandardCharsets.UTF_8); //NEW


        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");

            // OLD:
            // List<FileItem> items = servletFileUpload.parseRequest(req);

            // NEW:
            List<FileItem> items = fileUpload.parseRequest(req); // NEW

            for (FileItem item : items) {

                // OLD code xử lý không phân biệt form field:
                // if (item.getFieldName().equals("id")) {
                //     category.setCateid(Integer.parseInt(item.getString()));
                // } else if (item.getFieldName().equals("name")) {
                //     category.setCatename(item.getString("UTF-8"));
                // } else if (item.getFieldName().equals("icon")) {
                //     if (item.getSize() > 0) { ... }

                // NEW: dùng isFormField() để phân biệt text và file
                if (item.isFormField()) { // NEW
                    if ("id".equals(item.getFieldName())) {
                        category.setCateid(Integer.parseInt(item.getString()));
                    } else if ("name".equals(item.getFieldName())) {
                        category.setCatename(item.getString(StandardCharsets.UTF_8));
                    }
                } else {
                    if (item.getSize() > 0) { // nếu có file
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = System.currentTimeMillis() + "." + ext;

                        File uploadDir = new File(Constant.DIR + "/category");
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs(); // tạo cả cây thư mục nếu chưa tồn tại
                        }

                        File file = new File(uploadDir, fileName);

                        // OLD:
                        // item.write(file);

                        // NEW:
                        item.write(file.toPath()); // NEW: dùng Path thay vì File

                        category.setIcon("category/" + fileName);
                    } else {
                        category.setIcon(null);
                    }
                }
            }
            if (category.getCateid() == 0 || category.getCatename() == null || category.getCatename().trim().isEmpty()) {
                req.setAttribute("error", "Thiếu dữ liệu: ID hoặc tên danh mục không hợp lệ.");
                req.setAttribute("category", category);
                req.getRequestDispatcher("/view/admin/edit-category.jsp").forward(req, resp);
                return;
            }

            cateService.edit(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.setAttribute("category", category);
            req.getRequestDispatcher("/view/admin/edit-category.jsp").forward(req, resp);
        }
    }
}
