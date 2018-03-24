package services;

import entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(String user_name, String user_password, String user_login);
    void editUser(int id, User user);
    User getUser(int id);
    void removeUser(int id);
}
