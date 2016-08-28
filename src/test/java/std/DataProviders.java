package std;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 28.08.2016.
 */
public class DataProviders {


    private static String getExtension(){
        String[] extensions = new String[]{".txt",".xml",".doc",".pdf",".xlsx"};

        return extensions[new Random().nextInt(extensions.length-1)];

    }

    @DataProvider
    public static Iterator<Object[]> regularFiles(){

        int number = 5;
        List<Object[]> files = new ArrayList<Object[]>();
        for (int i=0;i<number;i++) {
            files.add(new Object[]{generateRandomFileName(getExtension())});
        }
        return files.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> numberFiles(){

        int number = 5;
        List<Object[]> files = new ArrayList<Object[]>();
        for (int i=0;i<number;i++) {
            files.add(new Object[]{generateRandomOnlyNumbersName(getExtension())});
        }
        return files.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> noExFiles(){

        int number = 5;
        List<Object[]> files = new ArrayList<Object[]>();
        for (int i=0;i<number;i++) {
            files.add(new Object[]{generateRandomFileName("")});
        }
        return files.iterator();
    }

    @DataProvider
    public static  Iterator<Object[]> loadWrongFileNameFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/wrongfilenames.data")));

        List<Object[]> fileNames = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            fileNames.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        return fileNames.iterator();
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
