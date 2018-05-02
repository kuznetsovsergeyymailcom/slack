package dao;

import model.User;
import role.Role;

import java.util.Set;

public interface RoleDao {
    Role getRoleByName(String name);
    Set<User> getUsersByRole(String name);
}
