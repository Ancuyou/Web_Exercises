package vn.iotstar.services;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.UserDaoImpl;
import vn.iotstar.entities.User;
import vn.iotstar.models.UserDTO;
import vn.iotstar.utils.UserMapper;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();

    /** Đăng nhập */
    @Override
    public UserDTO login(String username, String password) {
        User user = userDao.login(username, password);
        return UserMapper.toDTO(user);
    }

    /** Tìm User theo username */
    @Override
    public UserDTO findByUserName(String username) {
        User user = userDao.findByUsername(username);
        return UserMapper.toDTO(user);
    }

    /** Thêm mới user */
    @Override
    public void insert(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        userDao.create(user);
    }

    /** Đăng ký tài khoản mới */
    @Override
    public boolean register(String email, String password, String username, String fullname, String phone) {
        // Kiểm tra trùng dữ liệu
        if (checkExistEmail(email) || checkExistUsername(username) || checkExistPhone(phone)) {
            return false;
        }

        // Tạo User mới
        User user = new User();
        user.setEmail(email);
        user.setPassWord(password); // nên hash password trước khi lưu
        user.setUserName(username);
        user.setFullName(fullname);
        user.setPhone(phone);
        user.setRoleId(1); // default role là User

        userDao.create(user);
        return true;
    }

    /** Kiểm tra email đã tồn tại chưa */
    @Override
    public boolean checkExistEmail(String email) {
        List<User> allUsers = userDao.findAll();
        return allUsers.stream().anyMatch(u -> email.equalsIgnoreCase(u.getEmail()));
    }

    /** Kiểm tra username đã tồn tại chưa */
    @Override
    public boolean checkExistUsername(String username) {
        List<User> allUsers = userDao.findAll();
        return allUsers.stream().anyMatch(u -> username.equalsIgnoreCase(u.getUserName()));
    }

    /** Kiểm tra số điện thoại đã tồn tại chưa */
    @Override
    public boolean checkExistPhone(String phone) {
        List<User> allUsers = userDao.findAll();
        return allUsers.stream().anyMatch(u -> phone.equals(u.getPhone()));
    }

    /** Tìm User theo email hoặc số điện thoại */
    @Override
    public UserDTO findByEmailOrPhone(String identifier) {
        List<User> allUsers = userDao.findAll();
        for (User user : allUsers) {
            if (identifier.equalsIgnoreCase(user.getEmail()) || identifier.equals(user.getPhone())) {
                return UserMapper.toDTO(user);
            }
        }
        return null;
    }

    /** Cập nhật mật khẩu */
    @Override
    public void updatePassword(int userId, String newPassword) {
        User user = userDao.findById(userId);
        if (user != null) {
            user.setPassWord(newPassword); // nên hash password
            userDao.update(user);
        }
    }
}
