package dao.factory;

import dao.UserDao;
import dao.UserDaoHibernateImpl;
import org.hibernate.SessionFactory;

public class UserDaoFactory {

    public UserDao getUserDaoImpl() {
        return getUserDaoHibernateInstance();
    }


    private UserDao getUserDaoHibernateInstance() {
        SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactory();
        return new UserDaoHibernateImpl(sessionFactory);
    }
}
