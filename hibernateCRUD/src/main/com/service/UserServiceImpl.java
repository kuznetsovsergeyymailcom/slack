package service;

import dao.UserDao;
import model.User;
import exception.UnknownDaoType;
import factory.UserDaoFactory;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDao;
    private static volatile UserService userService = null;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                    try {
                        userDao = new UserDaoFactory().getUserDaoImpl();
                    } catch (UnknownDaoType unknownDaoType) {
                        unknownDaoType.printStackTrace();
                    }
                }
            }
        }
        return userService;
    }


    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(String userName, String userPassword, String userLogin, String[] roles) {
        userDao.addUser(userName, userPassword, userLogin, roles);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public User getUser(int id) {
        return userDao.getUser(id);
    }

    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    public User getUser(String name) {
        return userDao.getUser(name);
    }
}
