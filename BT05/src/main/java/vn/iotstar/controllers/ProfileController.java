package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.dao.UserDaoImpl;
import vn.iotstar.entities.User;
import vn.iotstar.models.UserDTO;
import vn.iotstar.utils.UserMapper;

import java.io.File;
import java.io.IOException;

@WebServlet("/profile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
        maxFileSize = 1024 * 1024 * 10,       // 10MB
        maxRequestSize = 1024 * 1024 * 50     // 50MB
)
public class ProfileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserDaoImpl userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("account");

        if (userDTO == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Lấy entity từ DB (có phone, avatar)
        User user = userDao.findById(userDTO.getId());
        req.setAttribute("user", user);

        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute("account");

        if (userDTO == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        User user = userDao.findById(userDTO.getId());
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Lấy dữ liệu từ form
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");

        // Upload avatar
        Part filePart = req.getPart("avatar");
        if (filePart != null && filePart.getSize() > 0) {
            String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();
            String uploadDir = getServletContext().getRealPath("/uploads");
            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            filePart.write(uploadDir + File.separator + fileName);
            user.setAvatar("uploads/" + fileName);
        }

        // Cập nhật thông tin
        user.setFullName(fullName);
        user.setPhone(phone);

        userDao.update(user);

        // Cập nhật lại session DTO
        session.setAttribute("account", UserMapper.toDTO(user));

        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
