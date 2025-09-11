package Controller;
import Models.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session= req.getSession();
        if(session != null && session.getAttribute("account") != null) {
            User u = (User) session.getAttribute("account");
            int role = u.getRoleId();
            if(role == 1) {
                resp.sendRedirect(req.getContextPath() + "/view/admin/home.jsp");
            } else if(role == 2) {
                resp.sendRedirect(req.getContextPath() + "/view/manager/home.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/view/home.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
