package dao;

import entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.TimeZone;

public class UserDaoHibernateImpl implements UserDao{
    private SessionFactory sessionFactory = null;

    public UserDaoHibernateImpl(){
        sessionFactory = new Configuration().
                addAnnotatedClass(User.class).configure()
                .buildSessionFactory();
        sessionFactory.withOptions().jdbcTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> from_user;
        try{
            session.beginTransaction();
            from_user = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        }finally {
            session.close();
        }

        return from_user;
    }

    @Override
    public void addUser(String user_name, String user_password, String user_login) {
        Session session = sessionFactory.openSession();
        User user = new User(user_name, user_password, user_login);

        try{
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }

    @Override
    public void editUser(int id, User user) {
        Session session = sessionFactory.openSession();
        try{
            session.beginTransaction();
            User user1 = session.get(User.class, id);
            user1.setName(user.getName());
            user1.setLogin(user.getLogin());
            user1.setPassword(user.getPassword());
            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.openSession();
        User user;
        try{
            session.beginTransaction();
            user = session.get(User.class, id);
            session.getTransaction().commit();
        }finally {
            session.close();
        }

        return user;
    }

    @Override
    public void removeUser(int id) {
        Session session = sessionFactory.openSession();
        User user;
        try{
            user = session.get(User.class, id);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();

        }finally {
            session.close();
        }


    }
}
