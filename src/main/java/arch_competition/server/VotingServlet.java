package arch_competition.server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VotingServlet extends HttpServlet {
    private ServerSettings serverSettings = ServerSettingsFactory.getServerSettings();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idButton = req.getParameter("button");
        User user = (User) req.getSession().getAttribute("user");
        String login = user.getLogin();


        try (Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword())) {

            connection.setAutoCommit(false);

            // insert id of elected project
            PreparedStatement statementToUsers = connection.prepareStatement("UPDATE users SET id = ? WHERE login = ?");
            statementToUsers.setString(1, idButton);
            statementToUsers.setString(2, login);

            statementToUsers.executeUpdate();


            // add votes to projects
            PreparedStatement statementToProjects = connection.prepareStatement("UPDATE design_projects SET votes = votes+1 WHERE id = ?");

            statementToProjects.setString(1, idButton);
            statementToProjects.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        user.setSelectedProjectId(idButton);
        resp.sendRedirect("/");
    }
}
