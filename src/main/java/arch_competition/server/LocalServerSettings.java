package arch_competition.server;

/**
 */
public class LocalServerSettings implements ServerSettings {

    @Override
    public String getJdbcUrl() {
        return "jdbc:mysql://localhost:3306/archcompetition";
    }

    @Override
    public String getUser() {
        return "db_admin";
    }

    @Override
    public String getPassword() {
        return "root";
    }

    @Override
    public String getDataDir() {
        return "";
    }
}
