package helper;

import entitie.User;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelper {
    private static Properties properties = new Properties();
    private static Configuration configuration = null;
    private static Connection connection = null;

    static {
        try {
            properties.load(new FileInputStream("..\\webapps\\ROOT\\WEB-INF\\classes\\hibernate.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        configuration = getConfiguration();
        connection = getConnection();

    }
    private DBHelper(){

    }

    public static Connection getConnection(){
        if(connection == null){
            try {
                String connectionString = buildConnectionString();
                connection =
                        DriverManager.getConnection(connectionString, properties.getProperty("connection.username"),
                                properties.getProperty("connection.password"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static Configuration getConfiguration(){
        if(configuration == null){
            configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml").addProperties(properties);
            configuration.addAnnotatedClass(User.class);
        }

        return configuration;
    }


    private static String buildConnectionString(){
        String driver = properties.getProperty("connection.driver_class");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        StringBuffer buffer = new StringBuffer();
        buffer.append(properties.getProperty("connection.url"));
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
