package vn.iotstar.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import vn.iotstar.models.CategoryDTO;
import vn.iotstar.models.UserDTO;
import vn.iotstar.services.CategoryService;
import vn.iotstar.services.CategoryServiceImpl;
import vn.iotstar.utils.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

    private final CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                showAddForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteCategory(req, resp);
                break;
            default:
                listCategories(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                insertCategory(req, resp);
                break;
            case "edit":
                updateCategory(req, resp);
                break;
            default:
                listCategories(req, resp);
                break;
        }
    }

    /* ==========================
       Các method hỗ trợ
       ========================== */

    /** Hiển thị danh sách category */
//    private void listCategories(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//
//        String userIdParam = req.getParameter("userId");
//        List<CategoryDTO> cateList;
//
//        if (userIdParam != null && !userIdParam.isEmpty()) {
//            int userId = Integer.parseInt(userIdParam);
//            cateList = cateService.findByUserId(userId);
//        } else {
//            cateList = cateService.findAll();
//        }
//
//        req.setAttribute("cateList", cateList);
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/list-category.jsp");
//        dispatcher.forward(req, resp);
//    }
    private void listCategories(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        UserDTO user = (UserDTO)req.getSession().getAttribute("account");
        List<CategoryDTO> cateList;
        if (user!= null && user.getRoleId()==3){
            cateList = cateService.findByUserId(user.getId());
        }
        else{
            cateList = cateService.findAll();
        }
        req.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/list-category.jsp");
        dispatcher.forward(req, resp);
    }

    /** Hiển thị form thêm mới */
    private void showAddForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/add-category.jsp");
        dispatcher.forward(req, resp);
    }

    /** Hiển thị form chỉnh sửa */
    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null || id.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/category?action=list");
            return;
        }

        CategoryDTO category = cateService.findById(Integer.parseInt(id));
        req.setAttribute("category", category);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/edit-category.jsp");
        dispatcher.forward(req, resp);
    }

    /** Xử lý thêm mới category */
    private void insertCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        CategoryDTO categoryDTO = new CategoryDTO();

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload fileUpload = new JakartaServletFileUpload(factory);
        fileUpload.setHeaderCharset(StandardCharsets.UTF_8);

        try {
            List<FileItem> items = fileUpload.parseRequest(req);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    if ("categoryName".equals(item.getFieldName())) {
                        categoryDTO.setCategoryName(item.getString(StandardCharsets.UTF_8));
                    }
                    if ("userId".equals(item.getFieldName())) {
                        categoryDTO.setUserId(Integer.parseInt(item.getString()));
                    }
                } else if ("images".equals(item.getFieldName()) && item.getSize() > 0) {
                    String originalFileName = item.getName();
                    int index = originalFileName.lastIndexOf(".");
                    String ext = originalFileName.substring(index + 1);
                    String fileName = System.currentTimeMillis() + "." + ext;

                    File uploadDir = new File(Constant.DIR + "/category");
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }
                    File file = new File(uploadDir, fileName);
                    item.write(file.toPath());

                    categoryDTO.setImages("category/" + fileName);
                }
            }
            UserDTO sessionUser = (UserDTO) req.getSession().getAttribute("account");
            if (sessionUser == null) {
                req.setAttribute("error", "Bạn chưa đăng nhập");
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
                return;
            }
            categoryDTO.setUserId(sessionUser.getId());

            cateService.insert(categoryDTO);
            resp.sendRedirect(req.getContextPath() + "/category?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi khi thêm category: " + e.getMessage());
            req.getRequestDispatcher("/views/add-category.jsp").forward(req, resp);
        }
    }

    /** Xử lý cập nhật category */
    private void updateCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        CategoryDTO categoryDTO = new CategoryDTO();

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload fileUpload = new JakartaServletFileUpload(factory);
        fileUpload.setHeaderCharset(StandardCharsets.UTF_8);

        try {
            List<FileItem> items = fileUpload.parseRequest(req);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    if ("id".equals(item.getFieldName())) {
                        categoryDTO.setId(Integer.parseInt(item.getString()));
                    } else if ("categoryName".equals(item.getFieldName())) {
                        categoryDTO.setCategoryName(item.getString(StandardCharsets.UTF_8));
                    } else if ("userId".equals(item.getFieldName())) {
                        categoryDTO.setUserId(Integer.parseInt(item.getString()));
                    }
                } else if (item.getSize() > 0) { // nếu có file
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

                    categoryDTO.setImages("category/" + fileName);
                } else {
                    CategoryDTO existing = cateService.findById(categoryDTO.getId());
                    categoryDTO.setImages(existing.getImages());
                }
            }

            if (categoryDTO.getId() == 0 || categoryDTO.getCategoryName() == null
                    || categoryDTO.getCategoryName().trim().isEmpty()) {
                req.setAttribute("error", "Thiếu dữ liệu: ID hoặc tên danh mục không hợp lệ.");
                req.setAttribute("category", categoryDTO);
                req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
                return;
            }
            UserDTO sessionUser = (UserDTO) req.getSession().getAttribute("account");
            if (sessionUser == null) {
                req.setAttribute("error", "Bạn chưa đăng nhập");
                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
                return;
            }
            categoryDTO.setUserId(sessionUser.getId());

            cateService.update(categoryDTO);
            resp.sendRedirect(req.getContextPath() + "/category?action=list");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Có lỗi xảy ra: " + e.getMessage());
            req.setAttribute("category", categoryDTO);
            req.getRequestDispatcher("/views/edit-category.jsp").forward(req, resp);
        }
    }

    /** Xử lý xóa category */
    private void deleteCategory(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        cateService.delete(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/category?action=list");
    }
}
