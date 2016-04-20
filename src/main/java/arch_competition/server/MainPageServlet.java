package arch_competition.server;

import arch_competition.shared.DesignProject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class MainPageServlet extends HttpServlet {

    private DatabaseDAO databaseDAO = new DatabaseDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<DesignProject> designProjectArrayList = databaseDAO.read();
        req.setAttribute("designProjects", designProjectArrayList);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
