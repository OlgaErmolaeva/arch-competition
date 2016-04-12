package arch_competition.server;


import arch_competition.shared.DesignProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class FileReadWriteDAO implements MyDAO {

    private static final String FILE_PROJECT_STORE_SER = "D:/install/gwt-2.7.0/gwt-2.7.0/fileProjectStore.ser";

    public void write(DesignProject designProject) {
        /*try (ObjectOutputStream objectWriteInStore = new ObjectOutputStream(new FileOutputStream(FILE_PROJECT_STORE_SER))) {
            objectWriteInStore.writeObject(projectStore);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Override
    public void delete(String id) {

    }

    public ArrayList<DesignProject> read() {

        File file = new File(FILE_PROJECT_STORE_SER);
        ArrayList<DesignProject> projectStore = null;
        if (file.length() == 0) {
            projectStore = new ArrayList<>();
        } else {
            try (ObjectInputStream objectReadFromStore = new ObjectInputStream(new FileInputStream(file))) {
                Object arrayFromFile = objectReadFromStore.readObject();
                projectStore = (ArrayList<DesignProject>) arrayFromFile;
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        }

        return projectStore;
    }

}
