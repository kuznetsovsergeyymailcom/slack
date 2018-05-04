package service;

import dao.RoleDao;
import dao.factory.RoleDaoFactory;
import model.User;
import model.Role;

import java.util.HashSet;
import java.util.Set;

public class RoleServiceImpl implements RoleService {
    private static RoleDao roleDao;
    private static volatile RoleService roleService = null;

    private RoleServiceImpl() {
    }

    public static RoleService getInstance() {
        if (roleService == null) {
            synchronized (UserServiceImpl.class) {
                if (roleService == null) {
                    roleService = new RoleServiceImpl();
                    roleDao = new RoleDaoFactory().getRoleDaoImpl();
                }
            }
        }
        return roleService;
    }

    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public Set<Role> getUserRoles(String[] array){
        return roleDao.getUserRoles(array);
    }
}
