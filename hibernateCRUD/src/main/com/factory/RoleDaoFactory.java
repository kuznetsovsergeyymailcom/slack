package factory;

import dao.RoleDao;
import dao.RoleDaoHibernateImpl;
import org.hibernate.SessionFactory;

public class RoleDaoFactory {

    public RoleDao getRoleDaoImpl(){
        return getRoleDaoHibernateInstance();
    }

    private RoleDao getRoleDaoHibernateInstance() {
        System.out.println("Role dao factory ..... ");
        SessionFactory sessionFactory = SessionFactoryBuilder.getSessionFactory();
        return new RoleDaoHibernateImpl(sessionFactory);
    }
}
