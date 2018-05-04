package dao.factory;

import utils.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {
    private static SessionFactory sessionFactory = null;

    public SessionFactoryBuilder() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {

            synchronized (SessionFactoryBuilder.class) {
                if (sessionFactory == null) {

                    Configuration configuration = DBHelper.getConfiguration();
                    sessionFactory = configuration.buildSessionFactory(
                            new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
                }
            }
        }

        return sessionFactory;
    }


}
