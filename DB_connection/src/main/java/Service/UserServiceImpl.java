package Service;

import Dao.UserDao;
import Dao.UserDaoImpl;
import Models.User;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User login(String username, String password) {
        User user = this.findByUserName(username);
        if (user != null && password.equals(user.getPassWord())) {
            return user;
        }
        return null;
    }
    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }
    @Override
    public boolean register(String username, String password, String email, String
            fullname, String phone ) {
        if (userDao.checkExistUsername(username)) {
            return false;
        }
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        userDao.insert(new User(email, username, fullname,password, 3,null,phone,date));
        return true;
    }
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }
    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }
    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

}
