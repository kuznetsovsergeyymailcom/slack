package dao;

import model.Role;

import java.util.Set;

public interface RoleDao {
    Role getRoleByName(String name);
    Set<Role> getUserRoles(String[] array);
}

