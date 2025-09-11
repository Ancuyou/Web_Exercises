package Controller;

import Service.UserService;
import Service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/resetPassword")
public class ResetPasswordController extends HttpServlet {
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Khi user truy cập /resetPassword qua redirect, ta forward tới form JSP
        req.getRequestDispatcher("/view/resetPassword.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String newPassword = req.getParameter("newPassword");
        String confirmPassword = req.getParameter("confirmPassword");

        // Kiểm tra mật khẩu nhập lại
        if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            req.setAttribute("alert", "Mật khẩu không khớp!");
            req.getRequestDispatcher("/view/resetPassword.jsp").forward(req, resp);
            return;
        }

        // Lấy userId từ session (set ở ForgetPasswordController)
        Integer userId = (Integer) req.getSession().getAttribute("resetUserId");
        if (userId == null) {
            // Nếu không có session thì quay về login
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        userService.updatePassword(userId, newPassword);

        // Xóa attribute reset để tránh reuse
        req.getSession().removeAttribute("resetUserId");

        // Sau khi reset thành công → về login.jsp với thông báo
        req.setAttribute("alert", "Đổi mật khẩu thành công! Hãy đăng nhập lại.");
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }
}
