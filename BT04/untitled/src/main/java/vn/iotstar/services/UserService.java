package vn.iotstar.services;

import vn.iotstar.models.UserDTO;

public interface UserService {
    UserDTO login(String username, String password);
    UserDTO findByUserName(String username);

    void insert(UserDTO user);
    boolean register(String email, String password, String username, String
            fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    UserDTO findByEmailOrPhone(String identifier);
    void updatePassword(int userId, String newPassword);
}

