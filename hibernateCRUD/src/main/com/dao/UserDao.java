package dao;

import model.User;
import role.Role;

import java.util.List;
import java.util.Set;

public interface UserDao {
    List<User> getAllUsers();

    void addUser(String userName, String userPassword, String userLogin, Set<Role> roles);

    void updateUser(User user);

    User getUser(int id);

    void removeUser(int id);

    User getUser(String name);
}