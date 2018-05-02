package dao;

import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import role.Role;

import java.util.Set;

public class RoleDaoHibernateImpl implements RoleDao {

    private static Logger logger = Logger.getLogger(UserDaoHibernateImpl.class);
    private SessionFactory sessionFactory;

    public RoleDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role getRoleByName(String name) {
        Session session = sessionFactory.openSession();
        Role role = null;
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Role where role_desc =:name");
            query.setParameter("name", name);
            role = (Role)query.uniqueResult();
            transaction.commit();

        }catch(Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }

        return role;
    }

    @Override
    public Set<User> getUsersByRole(String name) {
        Set<User> users = this.getRoleByName(name).getUsers();

        return users;
    }
}
