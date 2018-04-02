package dao;

import entitie.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);
    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> fromUser = null;
        Transaction transaction = session.beginTransaction();
        try {
            fromUser = session.createQuery("from User").getResultList();
            transaction.commit();
            logger.info("showing all users");
        } catch (Exception e) {
            logger.error("Error on method getAllUsers: " + e.getMessage());
            transaction.rollback();

        } finally {
            session.close();
        }

        return fromUser;
    }

    @Override
    public void addUser(String userName, String userPassword, String userLogin, String role) {
        Session session = sessionFactory.openSession();
        User user = new User(userName, userPassword, userLogin, role);
        Transaction transaction = session.beginTransaction();

        try {
            session.save(user);
            transaction.commit();
            logger.info("Saving new user: " + user);

        } catch (Exception e) {
            logger.error("Error on method add new user: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        logger.info("User operation edit user: " + user);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);

            transaction.commit();
            logger.info("User operation edit user, updated info: " + user);

        } catch (Exception e) {
            logger.error("Error on method edit user: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        try {
            user = session.get(User.class, id);
            transaction.commit();
            logger.info("User operation, get user" + user);
        } catch (Exception e) {
            logger.error("Error on method get user: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return user;
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user;
        try {
            user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            logger.info("User operation, remove user" + user);
        } catch (Exception e) {
            logger.error("Error on method remove user: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public User getUser(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = null;
        try {
            Query query = session.createQuery("from User where name = :name");
            query.setParameter("name", name);
            user = (User) query.uniqueResult();

            transaction.commit();
            logger.info("User operation, get user" + user);
        } catch (Exception e) {
            logger.error("Error on method get user: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
        }

        return user;
    }
}
