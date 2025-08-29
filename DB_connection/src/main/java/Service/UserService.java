package Service;

import Models.User;

public interface UserService {
    User login(String username, String password);
    User findByUserName(String username);

    void insert(User user);
    boolean register(String email, String password, String username, String
            fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    User findByEmailOrPhone(String identifier);
    void updatePassword(int userId, String newPassword);
}
