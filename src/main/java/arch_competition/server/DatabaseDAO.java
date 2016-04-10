package arch_competition.server;


import arch_competition.shared.DesignProject;

import java.sql.*;
import java.util.ArrayList;

/**
 */
public class DatabaseDAO implements MyDAO {

    private ServerSettings serverSettings = ServerSettingsFactory.getServerSettings();

    @Override
    public ArrayList<DesignProject> read() {
        try {
            final Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM design_projects");

            ArrayList<DesignProject> result = new ArrayList<>();
            while (resultSet.next()) {

                DesignProject designProject = new DesignProject();
                designProject.setName(resultSet.getString("name"));
                designProject.setDescription(resultSet.getString("description"));
                designProject.setCreationDate(resultSet.getDate("creation_date"));
                designProject.setPicture(resultSet.getString("picture"));

                result.add(designProject);

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(DesignProject designProject) {

        try (Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());
             PreparedStatement statement = connection.prepareStatement("INSERT INTO design_projects (name, description, picture,creation_date) VALUES(?,?,?,?)")) {

            statement.setString(1, designProject.getName());
            statement.setString(2, designProject.getDescription());
            statement.setString(3, designProject.getPicture());
            statement.setDate(4, new java.sql.Date(designProject.getCreationDate().getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {

        try (Connection connection = DriverManager.getConnection(serverSettings.getJdbcUrl(), serverSettings.getUser(), serverSettings.getPassword());
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM design_projects WHERE id =?")) {
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
