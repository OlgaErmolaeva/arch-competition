package arch_competition.server;


import arch_competition.shared.DesignProject;

import java.util.ArrayList;


public interface MyDAO {

    ArrayList<DesignProject> read();

    void create(DesignProject designProject);

    void delete(String id);

    void update (DesignProject designProject);

    }
