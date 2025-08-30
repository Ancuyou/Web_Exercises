package Controller;

import Models.Category;
import Models.Constant;
import Service.CategoryService;
import Service.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// OLD:
// import org.apache.commons.fileupload.FileItem;
// import org.apache.commons.fileupload.FileUploadException;
// import org.apache.commons.fileupload.RequestContext;
// import org.apache.commons.fileupload.disk.DiskFileItemFactory;
// import org.apache.commons.fileupload.servlet.ServletFileUpload;

// NEW:
import org.apache.commons.fileupload2.core.DiskFileItemFactory;  // NEW
import org.apache.commons.fileupload2.core.FileItem;           // NEW
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload; // NEW

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets; // NEW
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category/add" })
public class CategoryAddController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/view/admin/add-category.jsp");
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
        fileUpload.setHeaderCharset(StandardCharsets.UTF_8); // NEW

        try {
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");

            // OLD:
            // List<FileItem> items = servletFileUpload.parseRequest(req);

            // NEW:
            List<FileItem> items = fileUpload.parseRequest(req); // NEW

            for (FileItem item : items) {
                // OLD:
                // if (item.getFieldName().equals("name")) {
                //     category.setCatename(item.getString("UTF-8"));
                // } else if (item.getFieldName().equals("icon")) {
                //     String originalFileName = item.getName();
                //     int index = originalFileName.lastIndexOf(".");
                //     String ext = originalFileName.substring(index + 1);
                //     String fileName = System.currentTimeMillis() + "." + ext;
                //     File file = new File(Constant.DIR + "/category/" + fileName);
                //     item.write(file);
                //     category.setIcon("category/" + fileName);
                // }

                // NEW:
                if (item.isFormField()) {
                    if ("name".equals(item.getFieldName())) {
                        category.setCatename(item.getString(StandardCharsets.UTF_8));
                    }
                } else {
                    if ("icon".equals(item.getFieldName()) && item.getSize() > 0) {
                        String originalFileName = item.getName();
                        int index = originalFileName.lastIndexOf(".");
                        String ext = originalFileName.substring(index + 1);
                        String fileName = System.currentTimeMillis() + "." + ext;
                        File uploadDir = new File(Constant.DIR + "/category");
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }
                        File file = new File(uploadDir, fileName);
                        item.write(file.toPath()); // NEW: dùng Path thay vì File
                        category.setIcon("category/" + fileName);
                    }
                }
            }

            cateService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

            // OLD catch:
            // } catch (FileUploadException e) {
            //     e.printStackTrace();
            // } catch (Exception e) {
            //     e.printStackTrace();
            // }

        } catch (Exception e) { // NEW: gom chung vì parseRequest và write() đều throw Exception
            e.printStackTrace();
        }
    }
}
