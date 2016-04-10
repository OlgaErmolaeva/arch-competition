package arch_competition.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;


/**
 *
 */
public class AuthorizationServlet extends HttpServlet {

    private ServerSettings serverSettings = ServerSettingsFactory.getServerSettings();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password;
        String name;

        try {
            final Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());
            final PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?");
            statement.setString(1, req.getParameter("login"));

            final ResultSet resultSet = statement.executeQuery();

            password = resultSet.getString("password");
            name = resultSet.getString("name");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        if (password == req.getParameter("password")) {
            HttpSession session = req.getSession();
            session.setAttribute("name", name);
        }
    }
}
