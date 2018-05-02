package service;

import dao.RoleDao;
import dao.UserDao;
import factory.RoleDaoFactory;
import model.User;
import factory.UserDaoFactory;
import role.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService, RoleService {
    private static UserDao userDao;
    private static RoleDao roleDao;
    private static volatile UserService userService = null;

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                    userDao = new UserDaoFactory().getUserDaoImpl();
                    roleDao = new RoleDaoFactory().getRoleDaoImpl();
                }
            }
        }
        return userService;
    }


    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(String userName, String userPassword, String userLogin, String[] array) {
        Set<Role> roles = getUserRoles(array);
        userDao.addUser(userName, userPassword, userLogin, roles);
    }

    public void updateUser(int id, String userName, String userPassword, String userLogin, String[] array) {
        User user = new User(id, userName, userPassword, userLogin);
        Set<Role> roles = getUserRoles(array);
        user.setRoles(roles);

        userDao.updateUser(user);
    }

    public User getUser(int id) {

        return userDao.getUser(id);
    }

    public void removeUser(int id) {

        userDao.removeUser(id);
    }

    public User getUser(String name) {
        return userDao.getUser(name);
    }

    @Override
    public Role getRoleByName(String name) {

        return roleDao.getRoleByName(name);
    }

    @Override
    public Set<User> getUsersByRole(String name) {

        return roleDao.getUsersByRole(name);
    }
    private Set<Role> getUserRoles(String[] array){
        Role role;
        Set<Role> roles = new HashSet<>();
        for (String str : array) {
            if (str.equalsIgnoreCase("admin")) {
                role = getRoleByName("admin");
            } else {
                role = getRoleByName("user");
            }
            roles.add(role);
        }
        return roles;
    }
}
