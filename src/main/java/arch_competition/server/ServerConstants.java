package arch_competition.server;

/**
 */
public interface ServerConstants {

    String JDBC_URL = "jdbc:mysql://" + System.getenv("OPENSHIFT_MYSQL_DB_HOST") + ":"
            + System.getenv("OPENSHIFT_MYSQL_DB_PORT") + "/archcompetition";

    String USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    String PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
}
