package arch_competition.server;


import arch_competition.shared.DesignProject;

import java.util.ArrayList;


public class MemoryReadWriteDAO implements MyDAO {


    private ArrayList<DesignProject> projectStore = new ArrayList<>();

    public ArrayList<DesignProject> read() {
        return projectStore;
    }

    public void write(DesignProject designProject) {

    }

    @Override
    public void delete(String id) {

    }

}
