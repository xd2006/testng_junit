package std;

import com.tngtech.java.junit.dataprovider.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 11.09.2016.
 */
public class DataProviders {

    //region DataProviders
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
    public static List<Object> numberFiles(){

        int number = 5;
        List<Object> files = new ArrayList<Object>();
        for (int i=0;i<number;i++) {
            files.add(generateRandomOnlyNumbersName(getExtension()));
        }
        return files;
    }

    @DataProvider
    public static List<Object> noExFiles() {

        int number = 5;
        List<Object> files = new ArrayList<Object>();
        for (int i = 0; i < number; i++) {
            files.add(generateRandomFileName(""));
        }
        return files;
    }

    @DataProvider
    public static  List<Object> loadWrongFileNameFromFile() throws IOException {
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

        return fileNames;
    }
    //endregion

    //region Private Methods
    private static String getExtension(){
        String[] extensions = new String[]{".txt",".xml",".doc",".pdf",".xlsx"};

        return extensions[new Random().nextInt(extensions.length-1)];
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
    //endregion
}
