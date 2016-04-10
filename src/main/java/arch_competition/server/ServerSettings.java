package arch_competition.server;

/**
 */
public interface ServerSettings {

    String getJdbcUrl();
    String getUser();
    String getPassword();
    String getDataDir();
}
