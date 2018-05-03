package service;

import model.User;
import role.Role;

import java.util.Set;

public interface RoleService {
    Role getRoleByName(String name);
    Set<User> getUsersByRole(String name);
}
