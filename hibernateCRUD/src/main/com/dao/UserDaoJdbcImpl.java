package dao;

import entities.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserDaoJdbcImpl implements UserDao {
    private static final Properties properties = new Properties();
    Statement statement = null;

    static {
        try {
            properties.load(new FileInputStream("..\\webapps\\ROOT\\WEB-INF\\classes\\hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public UserDaoJdbcImpl(){

        getStatement();
    }

    private Statement getStatement() {
        try {
            String connectionString = this.buildConnectionString();
            Connection connection =
                    DriverManager.getConnection(connectionString,properties.getProperty("connection.username"),
                            properties.getProperty("connection.password"));
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public List<User> getAllUsers(){

        Connection connection = null;

        String sql = "select * from users";
        List<User> list = null;
        try {
            ResultSet resultSet = this.statement.executeQuery(sql);
            list = new ArrayList<User>();
            connection = this.statement.getConnection();
            connection.setAutoCommit(false);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                String login = resultSet.getString("login");
                list.add(new User(id, name, password, login));
            }

            connection.commit();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    public void addUser(String user_name, String user_password, String user_login){

        String sql = "insert into users (name,password,login) values('"+user_name+"','"+user_password+"','"+user_login+"')";
        updateSql(sql);
    }

    public void editUser(int id, User user){
        String sql = "update users set name='" + user.getName() + "',password='" +
                user.getPassword() + "',login='" + user.getLogin() + "' where id=" + id;
        updateSql(sql);
    }


    public User getUser(int id){
        Connection connection = null;
        User user = new User(id);

        String sql = "select * from users where id="+id;
        try {
            connection = this.statement.getConnection();
            connection.setAutoCommit(false);

            ResultSet resultSet = this.statement.executeQuery(sql);
            resultSet.next();
            String name = resultSet.getString("name");
            String password = resultSet.getString("password");
            String login = resultSet.getString("login");

            user.setName(name);
            user.setPassword(password);
            user.setLogin(login);

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }

    public void removeUser(int id){
        String sql = "delete from users where id=" + id;
        updateSql(sql);
    }

    private void updateSql(String sql) {
        Connection connection = null;
        try {
            connection = this.statement.getConnection();
            connection.setAutoCommit(false);
            this.statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String buildConnectionString(){

        try {
            Class.forName(properties.getProperty("connection.driver_class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(properties.getProperty("connection.url"));
        buffer.append("?useUnicode=");
        buffer.append(properties.getProperty("useUnicode"));
        buffer.append("&useJDBCCompliantTimezoneShift=");
        buffer.append(properties.getProperty("useJDBCCompliantTimezoneShift"));
        buffer.append("&useLegacyDatetimeCode=");
        buffer.append(properties.getProperty("useLegacyDatetimeCode"));
        buffer.append("&serverTimezone=");
        buffer.append(properties.getProperty("serverTimezone"));
        return buffer.toString();
    }
}
