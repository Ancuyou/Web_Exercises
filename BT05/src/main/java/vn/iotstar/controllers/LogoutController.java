package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
            System.out.println("Session invalidated successfully.");
        } else {
            System.out.println("No session found.");
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                c.setValue("");
                c.setMaxAge(0);
                c.setPath(req.getContextPath());
                c.setHttpOnly(true);
                c.setSecure(req.isSecure());
                resp.addCookie(c);
                System.out.println("Cookie removed: " + c.getName());
            }
        } else {
            System.out.println("No cookies found.");
        }
        System.out.println("Redirecting to login page.");
        resp.sendRedirect(req.getContextPath() + "/login");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
