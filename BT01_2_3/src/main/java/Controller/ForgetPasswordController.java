package Controller;

import Models.User;
import Service.UserService;
import Service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forgetPassword")
public class ForgetPasswordController extends HttpServlet {
    private UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/forgetPassword.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String identifier = req.getParameter("identifier"); // email hoặc phone
        User user = userService.findByEmailOrPhone(identifier);
        if (user == null) {
            req.setAttribute("alert", "Không tìm thấy tài khoản với thông tin này!");
            req.getRequestDispatcher("/view/forgetPassword.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("resetUserId", user.getId());
            resp.sendRedirect(req.getContextPath() + "/resetPassword");
        }
    }

}
