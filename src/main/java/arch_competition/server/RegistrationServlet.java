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

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (name != "" && login != "" && password != "") {

            try (Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());
                 final Statement statementValidation = connection.createStatement()) {

                final ResultSet resultSet = statementValidation.executeQuery("SELECT * FROM users WHERE login =" + "'" + login + "'");
                PreparedStatement statement = connection.prepareStatement("INSERT INTO users(name, login,id, password) VALUES(?,?,?,?)");

                if (!resultSet.next()) {

                    statement.setString(1, name);
                    statement.setString(2, login);
                    statement.setString(3, "0");
                    statement.setString(4, password);

                    statement.executeUpdate();

                    resp.sendRedirect("/registrationSuccessful.html");

                } else {
                    resp.sendRedirect("/registrationFailedWrongLogin.html");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }

        } else {
            resp.sendRedirect("/registrationFailedEmpty.html");
        }
    }
}
