package utils;

import model.User;
import org.hibernate.cfg.Configuration;
import model.Role;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBHelper {
    private static Properties properties = new Properties();

    private DBHelper() { }

    private static void initHibernateProperties() {
        try {
            InputStream resourceAsStream = DBHelper.class.getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Configuration getConfiguration() {
        initHibernateProperties();

        Configuration configuration = new Configuration();
        configuration.setProperties(properties);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);

        return configuration;
    }

}
