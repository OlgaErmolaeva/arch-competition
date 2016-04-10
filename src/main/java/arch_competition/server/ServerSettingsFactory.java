package arch_competition.server;

/**
 */
public final class ServerSettingsFactory {

    private static ServerSettings serverSettings;

    public static ServerSettings getServerSettings() {

        if (serverSettings == null) {
            OpenShiftServerSettings openShiftServerSettings = new OpenShiftServerSettings();

            if (openShiftServerSettings.getUser() != null) {
                serverSettings = openShiftServerSettings;
            } else {
                serverSettings = new LocalServerSettings();
            }
        }

        return serverSettings;
    }
}
