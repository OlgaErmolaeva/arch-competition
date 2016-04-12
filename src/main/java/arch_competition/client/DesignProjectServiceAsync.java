package arch_competition.client;

import arch_competition.shared.DesignProject;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

/**
 * The async counterpart of <code>DesignProjectService</code>.
 */
public interface DesignProjectServiceAsync {

    void getAllProjects(AsyncCallback<List<DesignProject>> async);
}
