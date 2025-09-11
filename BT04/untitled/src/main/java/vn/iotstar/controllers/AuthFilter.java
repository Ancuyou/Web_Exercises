package vn.iotstar.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.models.UserDTO;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String uri = req.getRequestURI();

        // Các URL public không cần login
        if (uri.endsWith("/login") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/")) {
            chain.doFilter(request, response);
            return;
        }

        // Kiểm tra đăng nhập
        UserDTO currentUser = (session != null) ? (UserDTO) session.getAttribute("account") : null;
        if (currentUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        int roleId = currentUser.getRoleId();

        // Phân quyền theo roleId
        if (uri.startsWith(req.getContextPath() + "/admin")) {
            // Chỉ Manager và Admin
            if (roleId == 1) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/error/403.jsp");
            }
        } else if (uri.startsWith(req.getContextPath() + "/manager")) {
            // Chỉ Manager
            if (roleId == 2) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/error/403.jsp");
            }
        } else if (uri.startsWith(req.getContextPath() + "/user")) {
            // Chỉ User
            if (roleId == 3) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/views/error/403.jsp");
            }
        } else {
            // Các URL khác cho phép truy cập
            chain.doFilter(request, response);
        }
    }
}
