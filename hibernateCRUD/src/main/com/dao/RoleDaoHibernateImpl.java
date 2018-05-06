package dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import model.Role;

import java.util.HashSet;
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

        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery("from Role where role_desc =:name");
            query.setParameter("name", name);
            role = (Role) query.uniqueResult();
            transaction.commit();
            logger.info("RoleDao get role by name " + name + " success");
        } catch (Exception e) {
            transaction.rollback();
            logger.error("RoleDao error, message: " + e.getLocalizedMessage());
        } finally {
            session.close();
        }

        return role;
    }

    @Override
    public Set<Role> convertArrayOfRolesToSetOfRoles(String[] array) {
        Role role;
        Set<Role> roles = new HashSet<>();
        for (String str : array) {
            if (str.equalsIgnoreCase("admin")) {
                role = getRoleByName("admin");
            } else {
                role = getRoleByName("user");
            }
            roles.add(role);
        }
        return roles;
    }
}
