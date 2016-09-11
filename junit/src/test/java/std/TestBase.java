package std;

import org.junit.Rule;

import java.io.File;

/**
 * Created by Alex on 04.09.2016.
 */
public class TestBase {

    public static String directoryPathStr = "";


//    @BeforeClass
//    public static void setUp() {
//        String tmp_dir_prefix = "test_";
//        try {
//            Path tmp = Files.createTempDirectory(tmp_dir_prefix);
//            directoryPathStr = tmp.toString()+"\\";
//            direcoryPath = tmp;
//
//        } catch (IOException e) {
//            System.err.println(e);
//        }
//    }
//
//    @AfterClass
//    public static void cleanUp() throws IOException {
//
//        deleteAllFilesFolder(direcoryPath.toString());
//        Files.delete(direcoryPath);
//
//    }

    @Rule
    public RerunRule testRerunRule = new RerunRule();

    protected static void deleteAllFilesFolder(String path) {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile()) myFile.delete();
    }

    protected boolean isFileExistsInFolder(String path, String fileName)
    {
        for (File myFile : new File(path).listFiles())
            if (myFile.isFile() && myFile.getName().equals(fileName))
                return true;
        return false;
    }






}
