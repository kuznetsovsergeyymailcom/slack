package factory;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import exception.UnknownDaoType;
import helper.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;

import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public UserDao getUserDaoImpl() {
        return getUserDaoHibernateInstance();
    }


    private UserDao getUserDaoHibernateInstance() {
        SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactory();
        return new UserDaoHibernateImpl(sessionFactory);
    }
}
