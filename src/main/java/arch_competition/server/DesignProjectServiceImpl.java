package arch_competition.server;

import arch_competition.client.DesignProjectService;
import arch_competition.shared.DesignProject;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.List;

/**
 * The server-side implementation of the RPC service.
 */
public class DesignProjectServiceImpl extends RemoteServiceServlet implements DesignProjectService {

    @Override
    public List<DesignProject> getAllProjects() {
        MyDAO dao = new DatabaseDAO();

        return dao.read();
    }
}
