package factory;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import dao.UserDaoJdbcImpl;
import entitie.User;
import exception.UnknownDaoType;
import helper.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class UserDaoFactory {
    private static Properties properties = new Properties();


    public UserDaoFactory() {
        try {
            InputStream resourceAsStream = UserDaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public UserDao getUserDaoImpl() throws UnknownDaoType {
        String dao = properties.getProperty("dao");
        switch (dao) {
            case "hibernate":
                return getUserDaoHibernateInstance();
            case "jdbc":
                return getUserDaoJdbcInstance();
            default:
                throw new UnknownDaoType("Unknown type of dao excess object");
        }
    }

    private UserDao getUserDaoHibernateInstance() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        SessionFactory sessionFactory = configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());

        return new UserDaoHibernateImpl(sessionFactory);
    }

    private UserDao getUserDaoJdbcInstance() {
        Connection connection = DBHelper.getInstance().getConnection();
        return new UserDaoJdbcImpl(connection);
    }
}
