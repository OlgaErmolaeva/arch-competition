package arch_competition.server;


import arch_competition.shared.DesignProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoryReadWriteDAO implements MyDAO {

    private Map<String, DesignProject> projectStore = new HashMap<>();

    public ArrayList<DesignProject> read() {
        return new ArrayList<>(projectStore.values());
    }

    public void write(DesignProject designProject) {
        projectStore.put(designProject.getId(), designProject);
    }

    @Override
    public void delete(String id) {
        projectStore.remove(id);
    }

}
