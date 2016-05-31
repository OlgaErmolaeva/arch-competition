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
public class LoginServlet extends HttpServlet {

    private ServerSettings serverSettings = ServerSettingsFactory.getServerSettings();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password;
        String name;
        String userVote;
        String login;
        try (final Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());
             final PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login = ?")) {

            statement.setString(1, req.getParameter("login"));

            final ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                password = resultSet.getString("password");
                name = resultSet.getString("name");
                login = resultSet.getString("login");
                userVote = resultSet.getString("id");
                if (password.equals(req.getParameter("password"))) {
                    HttpSession session = req.getSession();

                    User user = new User();
                    user.setName(name);
                    user.setLogin(login);
                    user.setSelectedProjectId(userVote);

                    session.setAttribute("user",user);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            resp.sendRedirect("/");
        }


    }
}
