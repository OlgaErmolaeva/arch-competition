package arch_competition.server;


import java.io.Serializable;

public class User implements Serializable {

    String name;
    String login;

    public String getSelectedProjectId() {
        return selectedProjectId;
    }

    public void setSelectedProjectId(String selectedProjectId) {
        this.selectedProjectId = selectedProjectId;
    }

    String selectedProjectId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


}




