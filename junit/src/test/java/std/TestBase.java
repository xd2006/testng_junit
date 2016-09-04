package std;

import com.tngtech.java.junit.dataprovider.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 04.09.2016.
 */
public class TestBase {
    public static String directoryPathStr = "";
    public static Path direcoryPath;

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

    private static String getExtension(){
        String[] extensions = new String[]{".txt",".xml",".doc",".pdf",".xlsx"};

        return extensions[new Random().nextInt(extensions.length-1)];

    }

        @DataProvider
    public static List<Object> regularFiles(){

        int number = 5;
        List<Object> files = new ArrayList<Object>() ;
        for (int i=0;i<number;i++) {
            files.add(generateRandomFileName(getExtension()));
        }
        return files;
    }

        @DataProvider
    public static Object[] numberFiles(){

        int number = 5;
        List<Object> files = new ArrayList<Object>();
        for (int i=0;i<number;i++) {
            files.add(generateRandomOnlyNumbersName(getExtension()));
        }
        return files.toArray();
    }

        @DataProvider
    public static Object[] noExFiles(){

        int number = 5;
        List<Object> files = new ArrayList<Object>();
        for (int i=0;i<number;i++) {
            files.add(generateRandomFileName(""));
        }
        return files.toArray();
    }

        @DataProvider
    public static  Object[] loadWrongFileNameFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                TestBase.class.getResourceAsStream("/wrongfilenames.data")));

        List<Object> fileNames = new ArrayList<Object>();
        String line = in.readLine();
        while (line != null) {
            String[] values = line.split(";");
            for (String value:values){
                fileNames.add(value);
            }
            line = in.readLine();
        }
        in.close();

        return fileNames.toArray();
    }


    private static Object generateRandomFileName(String extension) {

        String ex = !extension.equals("") ? extension.split("\\.")[1] : "no_extension";
        Object fileName = ex + "_file"+new Random().nextInt()+extension;
        return fileName;
    }

    private static Object generateRandomOnlyNumbersName(String extension) {

        Object fileName = new Random().nextInt()+ extension;
        return fileName;
    }


}
