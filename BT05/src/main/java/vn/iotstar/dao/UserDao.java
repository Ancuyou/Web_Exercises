package vn.iotstar.dao;

import vn.iotstar.entities.User;
import java.util.List;

public interface UserDao {
    User findById(int id);

    User findByUsername(String username);

    User login(String username, String password);

    List<User> findAll();

    void create(User user);

    void update(User user);

    void delete(int id);
}
