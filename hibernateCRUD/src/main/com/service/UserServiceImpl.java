package service;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import entitie.User;
import exception.UnknownDaoType;
import factory.UserDaoFactory;
import helper.DBHelper;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static UserDao userDao;
    private static UserService userService = null;

    static {
        try {
            userDao = new UserDaoFactory().getUserDaoImpl();
        } catch (UnknownDaoType unknownDaoType) {
            unknownDaoType.printStackTrace();
        }
    }

    private UserServiceImpl(){}

    public static UserService getInstance(){
        if(userService == null){
            userService = new UserServiceImpl();
        }
        return userService;
    }


    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public void addUser(String user_name, String user_password, String user_login){
        this.userDao.addUser(user_name, user_password, user_login);
    }
    public void editUser(int id, User user){
        this.userDao.editUser(id, user);
    }
    public User getUser(int id){
        return this.userDao.getUser(id);
    }
    public void removeUser(int id){
        this.userDao.removeUser(id);
    }
}
