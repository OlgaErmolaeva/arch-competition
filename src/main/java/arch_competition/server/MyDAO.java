package arch_competition.server;


import arch_competition.shared.DesignProject;

import java.util.ArrayList;


public interface MyDAO {

    ArrayList<DesignProject> read();

    void write(DesignProject designProject);

    void delete(String id);

    }
