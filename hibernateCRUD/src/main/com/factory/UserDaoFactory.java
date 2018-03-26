package factory;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import dao.UserDaoJdbcImpl;
import entitie.User;
import exception.UnknownDaoType;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

public class UserDaoFactory {
    private static Properties properties = new Properties();

    public UserDaoFactory(){
        try {
            properties.load(new FileInputStream("..\\webapps\\ROOT\\WEB-INF\\classes\\dao.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserDao getUserDaoImpl() throws UnknownDaoType {
        String dao = properties.getProperty("dao");
        switch(dao){
            case "hibernate": return getUserDaoHibernateInstance();
            case "jdbc": return getUserDaoJdbcInstance();
            default: throw new UnknownDaoType("Unknown type of dao excess object");
        }
    }

    private UserDao getUserDaoHibernateInstance(){
        return new UserDaoHibernateImpl();
    }
    private UserDao getUserDaoJdbcInstance(){
        return new UserDaoJdbcImpl();
    }
}
