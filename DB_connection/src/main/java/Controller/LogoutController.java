package Controller;

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
        }
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("JSESSIONID".equals(c.getName())) {
                    c.setValue(null);
                    c.setMaxAge(0);
                    c.setPath(req.getContextPath());
                    resp.addCookie(c);
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
