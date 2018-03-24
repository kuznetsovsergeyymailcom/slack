package services;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import entities.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoHibernateImpl();
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
