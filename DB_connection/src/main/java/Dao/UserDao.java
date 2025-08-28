package Dao;

import Models.User;

public interface UserDao {

    User findByUserName(String username);
}
