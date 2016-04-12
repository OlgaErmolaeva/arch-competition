package arch_competition.server;



import arch_competition.shared.DesignProject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 */
public class DatabaseDAO implements MyDAO {

    private static final String JDBC_URL = "jdbc:mysql//localhost:3306/archcompetition";
    private static final String USER = "adminBTJPYhI";
    private static final String PASSWORD = "aSsS7M-vh-V1";

    @Override
    public ArrayList<DesignProject> read() {
        try {
            //TODO This is to make Tomcat happy
//            Class.forName("com.mysql.jdbc.Driver");
            final Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            final Statement statement = connection.createStatement();
            final ResultSet resultSet = statement.executeQuery("SELECT * FROM arch_competition.design_projects");

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
        try {
            final Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            final Statement statement = connection.createStatement();

            String projectName = designProject.getName();
            String description = designProject.getDescription();
            String picture = designProject.getPicture();
            String creationDateAsString = new SimpleDateFormat("YYYY-MM-DD HH:MM:SS").format(designProject.getCreationDate());

            statement.executeUpdate("INSERT INTO arch_competition.design_projects (name, description, picture,creation_date)" + "VALUES('" + projectName + "','" + description + "','"+picture+"','"+creationDateAsString+"') ");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String id) {
        try {
            final Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM arch_competition.design_projects WHERE id =?");

            preparedStatement.setString(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
