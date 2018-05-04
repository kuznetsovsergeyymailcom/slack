package dao;

import model.User;
import model.Role;

import java.util.Set;

public interface RoleDao {
    Role getRoleByName(String name);

}