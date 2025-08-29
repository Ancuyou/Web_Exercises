package Dao;

import Models.User;

public interface UserDao {

    User findByUserName(String username);

    void insert(User user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    User findByEmailOrPhone(String identifier);
    void updatePassword(int userId, String newPassword);
}
