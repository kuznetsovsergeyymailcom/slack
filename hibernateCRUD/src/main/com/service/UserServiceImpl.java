package service;

import dao.UserDao;
import model.User;

import model.Role;
import java.util.List;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private static UserDao userDao;
    private static volatile UserService userService = null;
    private static volatile RoleService roleService = null;

    private UserServiceImpl() { }

    public static UserService getInstance() {
        if (userService == null) {
            synchronized (UserServiceImpl.class) {
                if (userService == null) {
                    userService = new UserServiceImpl();
                    roleService = RoleServiceImpl.getInstance();
                    userDao = new dao.factory.UserDaoFactory().getUserDaoImpl();

                }
            }
        }
        return userService;
    }


    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(String userName, String userPassword, String userLogin, String[] array) {
        Set<Role> roles = roleService.getUserRoles(array);

        userDao.addUser(userName, userPassword, userLogin, roles);
    }

    public void updateUser(int id, String userName, String userPassword, String userLogin, String[] array) {
        User user = new User(id, userName, userPassword, userLogin);
        Set<Role> roles = roleService.getUserRoles(array);

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
    public Set<User> getUsersByRole(String name) {
        return userDao.getUsersByRole(name);
    }


}
