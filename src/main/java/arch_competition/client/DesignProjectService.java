package arch_competition.client;

import arch_competition.shared.DesignProject;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


import java.util.List;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface DesignProjectService extends RemoteService {

    List<DesignProject> getAllProjects();

}
