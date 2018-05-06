package service;

import model.Role;

import java.util.Set;

public interface RoleService {
    Role getRoleByName(String name);

    Set<Role> getUserRoles(String[] array);
}
