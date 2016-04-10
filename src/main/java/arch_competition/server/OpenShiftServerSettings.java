package arch_competition.server;

/**
 */
public class OpenShiftServerSettings implements ServerSettings {

    @Override
    public String getJdbcUrl() {
        return "jdbc:mysql://" + System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":"
                + System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/archcompetition";
    }

    @Override
    public String getUser() {
        return System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    }

    @Override
    public String getPassword() {
        return System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    }

    @Override
    public String getDataDir() {
        return System.getenv("OPENSHIFT_DATA_DIR");
    }
}
