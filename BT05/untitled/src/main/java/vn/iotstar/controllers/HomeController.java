package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserDTO;

import java.io.IOException;

@SuppressWarnings("serial")
@WebServlet(urlPatterns="/home")
public class HomeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session= req.getSession();
        if(session != null && session.getAttribute("account") != null) {
            UserDTO u = (UserDTO) session.getAttribute("account");
            int role = u.getRoleId();
            if(role == 1) {
                resp.sendRedirect(req.getContextPath() + "/views/admin/home.jsp");
            } else if(role == 2) {
                resp.sendRedirect(req.getContextPath() + "/views/manager/home.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/user/home.jsp");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
