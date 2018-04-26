package dao;

import model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(String userName, String userPassword, String userLogin, String[] roles);

    void updateUser(User user);

    User getUser(int id);

    void removeUser(int id);

    User getUser(String name);
}