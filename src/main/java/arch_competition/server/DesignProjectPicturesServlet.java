package arch_competition.server;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 */
public class DesignProjectPicturesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String pictureFileName = req.getPathInfo().replaceAll("/", "");
        final Path picturesDirectory = Paths.get(ServerConstants.DATA_DIR, "pictures");
        final Path picturePath = picturesDirectory.resolve(pictureFileName);
        Files.copy(picturePath, resp.getOutputStream());
    }
}
