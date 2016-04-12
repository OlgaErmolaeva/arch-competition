package arch_competition.server;



import arch_competition.shared.DesignProject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 */
public class DatabaseDAO implements MyDAO {

    private static final String JDBC_URL = "jdbc:mysql://" + System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":"
            + System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/archcompetition";

    private static final String USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    private static final String PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

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
            Class.forName("com.mysql.jdbc.Driver");
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
