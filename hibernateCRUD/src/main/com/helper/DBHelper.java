package helper;

import entitie.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static volatile DBHelper dbHelper = null;
    private static Properties properties = new Properties();


    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            synchronized (DBHelper.class) {
                if (dbHelper == null) {
                    dbHelper = new DBHelper();
                }
            }
        }
        return dbHelper;
    }

    private void initHibernateProperties() {
        try {
            InputStream resourceAsStream = DBHelper.class.getClassLoader().getResourceAsStream("hibernate.properties");
            properties.load(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initJdbcProperties() {
        try {
            InputStream resourceAsStream = DBHelper.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(resourceAsStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        initJdbcProperties();
        Connection connection = null;
        try {
            String connectionString = buildConnectionString();
            connection =
                    DriverManager.getConnection(connectionString, properties.getProperty("name"),
                            properties.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public Configuration getConfiguration() {
        initHibernateProperties();

        Configuration configuration = new Configuration();
        configuration.configure().addProperties(properties);
        configuration.addAnnotatedClass(User.class);


        return configuration;
    }

    private String buildConnectionString() {
        String driver = properties.getProperty("jdbcDriver");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(properties.getProperty("url"));
        buffer.append("?useUnicode=");
        buffer.append(properties.getProperty("useUnicode"));
        buffer.append("&useJDBCCompliantTimezoneShift=");
        buffer.append(properties.getProperty("useJDBCCompliantTimezoneShift"));
        buffer.append("&useLegacyDatetimeCode=");
        buffer.append(properties.getProperty("useLegacyDatetimeCode"));
        buffer.append("&serverTimezone=");
        buffer.append(properties.getProperty("serverTimezone"));
        return buffer.toString();
    }
}
