package arch_competition.server;


import arch_competition.shared.DesignProject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 */
@MultipartConfig
public class DesignProjectCreationServlet extends HttpServlet {

    private DatabaseDAO databaseDAO = new DatabaseDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("/console.html");

        if (req.getPathInfo().contains("delete")) {
            String id = req.getParameter("id");
            databaseDAO.delete(id);
        }
        if (req.getPathInfo().contains("create")) {


            DesignProject designProject = new DesignProject();

            designProject.setName(req.getParameter("name"));
            designProject.setDescription(req.getParameter("description"));

            Part part = req.getPart("picture");
            InputStream pictureInputStream = part.getInputStream();

            final String fileName = req.getParameter("name");

            Path picturesDirectory = Paths.get(ServerConstants.DATA_DIR, "pictures");
            if (!Files.exists(picturesDirectory)) {
                Files.createDirectory(picturesDirectory);
            }
            Path path = picturesDirectory.resolve(fileName);
            Files.copy(pictureInputStream, path);

            designProject.setPicture("/pictures/" + fileName);

            designProject.setCreationDate(new Date());

            databaseDAO.write(designProject);
        }

    }
}
