package Controller;

import Models.Category;
import Service.CategoryService;
import Service.CategoryServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/category/list" })
public class CategoryController extends HttpServlet {
    CategoryService cateService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        List<Category> cateList = cateService.getAll();
        req.setAttribute("cateList", cateList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/admin/list-category.jsp");
                dispatcher.forward(req, resp);
    }
}
