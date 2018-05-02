package factory;

import exception.UnknownDaoType;
import helper.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import service.UserServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SessionFactoryBuilder {
    private static Properties properties = new Properties();
    private static SessionFactory sessionFactory = null;

    public SessionFactoryBuilder(){}

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            System.out.println("Session factory is null .... init....");

            InputStream resourceAsStream = UserDaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
            try {
                properties.load(resourceAsStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            synchronized (SessionFactoryBuilder.class) {
                if (sessionFactory == null) {

                    Configuration configuration = DBHelper.getInstance().getConfiguration();
                    sessionFactory = configuration.buildSessionFactory(
                            new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
                }
            }
        }
        return sessionFactory;
    }


}
