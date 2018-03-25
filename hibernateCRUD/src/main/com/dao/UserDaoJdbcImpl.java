package dao;

import entitie.User;
import helper.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    private DBHelper dbHelper = DBHelper.getInstance();
    private Connection connection;

    public UserDaoJdbcImpl(){
        connection = dbHelper.getConnection();
    }


    public List<User> getAllUsers(){

        String sql = "select * from users";
        List<User> list = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
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
        User user = new User(id);

        String sql = "select * from users where id="+id;
        try {
            connection.setAutoCommit(false);

            ResultSet resultSet = connection.prepareStatement(sql).executeQuery();
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
        try {
            connection.setAutoCommit(false);
            connection.prepareStatement(sql).executeUpdate();

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

}
