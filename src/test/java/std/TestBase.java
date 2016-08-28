package std;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Alex on 27.08.2016.
 */

public class TestBase {


    protected String directoryPathStr = "";
    protected Path direcoryPath;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        String tmp_dir_prefix = "test_";
        try {
            Path tmp = Files.createTempDirectory(tmp_dir_prefix);
            directoryPathStr = tmp.toString()+"\\";
            direcoryPath = tmp;

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void cleanUp() throws IOException {

        deleteAllFilesFolder(direcoryPath.toString());
        Files.delete(direcoryPath);

    }

    private void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }

}
