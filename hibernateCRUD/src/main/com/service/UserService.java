package service;

import model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> getAllUsers();

    void addUser(String userName, String userPassword, String userLogin, String[] roles);

    void updateUser(int id, String userName, String userPassword, String userLogin, String[] roles);

    User getUser(int id);

    void removeUser(int id);

    User getUser(String name);

    Set<User> getUsersByRole(String name);
}
