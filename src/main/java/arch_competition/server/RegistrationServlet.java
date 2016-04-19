package arch_competition.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 */
public class RegistrationServlet extends HttpServlet {

    private ServerSettings serverSettings = ServerSettingsFactory.getServerSettings();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try (Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());

                 final Statement statementValidation = connection.createStatement();
                 final ResultSet resultSet = statementValidation.executeQuery("SELECT * FROM users WHERE login ="+ "'"+req.getParameter("login")+"'");


                 PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, login, password) VALUES(?,?,?)")) {

                if(!resultSet.next()) {

                    statement.setString(1, req.getParameter("name"));
                    statement.setString(2, req.getParameter("login"));
                    statement.setString(3, req.getParameter("password"));


                    statement.executeUpdate();

                    resp.sendRedirect("index.jsp");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }






















    }
}
