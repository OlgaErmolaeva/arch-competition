package arch_competition.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.*;

/**
 */
public class InitDBServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        try (final Connection connection = DriverManager.getConnection(ServerConstants.JDBC_URL, ServerConstants.USER, ServerConstants.PASSWORD);
             final Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE if not exists 'design_projects' " +
                            "('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR(255), 'description' VARCHAR(3000), " +
                            "'creation_date' DATE, 'picture' VARCHAR(255))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //no-op
    }
}
