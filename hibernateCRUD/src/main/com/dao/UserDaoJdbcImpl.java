package dao;

import entitie.User;
import helper.DBHelper;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);
    private Connection connection;

    public UserDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() {

        String sql = "select * from users";
        List<User> list = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            connection.setAutoCommit(false);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String login = resultSet.getString("login");
                String role = resultSet.getString("role");
                list.add(new User(id, name, password, login, role));
            }

            connection.commit();
            logger.info("User operation, method get all user invocation ");

        } catch (SQLException e) {
            logger.error("Sql exception on method get all users: " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (Exception e) {
            logger.error("Exception on method get all users: " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Sql exception on method get all users, final block: " + e.getMessage());

            }
        }

        return list;
    }

    public void addUser(String userName, String userPassword, String userLogin, String role) {

        String sql = "insert into users (name,password,login,role) values('" + userName + "','" + userPassword + "','"
                + userLogin + "', '"+role+"')";
        updateSql(sql);
    }


    @Override
    public void updateUser(User user) {
        String sql = "update users set name='" + user.getName() + "',password='" +
                user.getPassword() + "',login='" + user.getLogin() + "',role='" + user.getRole() + "' where id=" + user.getId();
        updateSql(sql);
    }

    public User getUser(int id) {
        User user = new User(id);

        String sql = "select * from users where id=" + id;
        try {
            connection.setAutoCommit(false);

            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            resultSet.next();
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String login = resultSet.getString("login");
            String role = resultSet.getString("role");

            user.setName(name);
            user.setPassword(password);
            user.setLogin(login);
            user.setRole(role);

            connection.commit();

        } catch (SQLException e) {
            logger.error("Sql exception on method get user " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error("Sql exception on method get user, in try block " + e.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Sql exception on method get user, in final block " + e.getMessage());
            }
        }

        return user;
    }

    public void removeUser(int id) {
        String sql = "delete from users where id=" + id;
        updateSql(sql);
    }

    private void updateSql(String sql) {
        try {
            connection.setAutoCommit(false);
            connection.prepareStatement(sql).executeUpdate();

            connection.commit();
            logger.info("User operation, method update sql user invocation, sql " + sql);
        } catch (SQLException e) {
            logger.error("Sql exception on method update sql " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (Exception e) {
            logger.error("Exception on method update sql " + e.getMessage());
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Exception on final block of update sql method" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public User getUser(String name) {
        String sql = "select * from users where name='" + name + "'";
        User user = new User();

        try {
            connection.setAutoCommit(false);

            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
            resultSet.next();

            String id = resultSet.getString("id");
            String password = resultSet.getString("password");
            String login = resultSet.getString("login");
            String role = resultSet.getString("role");

            int i = Integer.parseInt(id);

            user.setId(i);
            user.setName(name);
            user.setPassword(password);
            user.setLogin(login);
            user.setRole(role);

            connection.commit();

        } catch (SQLException e) {
            logger.error("Sql exception on method get user " + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                logger.error("Sql exception on method get user, in try block " + e.getMessage());
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.error("Sql exception on method get user, in final block " + e.getMessage());
            }
        }

        return user;
    }
}
