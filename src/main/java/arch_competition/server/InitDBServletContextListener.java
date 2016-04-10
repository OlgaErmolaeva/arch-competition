package arch_competition.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

/**
 */
public class InitDBServletContextListener implements ServletContextListener {

    private ServerSettings serverSettings = ServerSettingsFactory.getServerSettings();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        //TODO This is to make Tomcat happy
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        try (final Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());
             final Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE if not exists design_projects " +
                            "(id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(255), " +
                            "description VARCHAR(3000), " +
                            "creation_date DATE, " +
                            "picture VARCHAR(255))");
// create table for users name and password
            statement.executeUpdate(
                    "CREATE TABLE if not exists users " +
                            "(name VARCHAR(255)PRIMARY KEY, " +
                            "login VARCHAR(50), " +
                            "password VARCHAR(50)) ");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //no-op
    }
}
