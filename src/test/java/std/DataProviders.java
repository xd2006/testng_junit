package std;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by Alex on 28.08.2016.
 */
public class DataProviders {


    private String getExtension(){
        String[] extensions = new String[]{".txt",".xml",".doc",".pdf",".xlsx"};

        return extensions[new Random().nextInt(extensions.length-1)];

    }

    @DataProvider
    public Iterator<Object[]> regularFiles(){

        int number = 5;
        List<Object[]> files = new ArrayList<Object[]>();
        for (int i=0;i<number;i++) {
            files.add(new Object[]{generateRandomFileName(getExtension())});
        }
        return files.iterator();
    }

    @DataProvider
    public Iterator<Object[]> numberFiles(){

        int number = 5;
        List<Object[]> files = new ArrayList<Object[]>();
        for (int i=0;i<number;i++) {
            files.add(new Object[]{generateRandomOnlyNumbersName(getExtension())});
        }
        return files.iterator();
    }

    @DataProvider
    public Iterator<Object[]> noExFiles(){

        int number = 5;
        List<Object[]> files = new ArrayList<Object[]>();
        for (int i=0;i<number;i++) {
            files.add(new Object[]{generateRandomFileName("")});
        }
        return files.iterator();
    }

    private  Object generateRandomFileName(String extension) {

        String ex = !extension.equals("") ? extension.split("\\.")[1] : "no_extension";
        Object fileName = ex + "_file"+new Random().nextInt()+extension;
        return fileName;
    }

    private Object generateRandomOnlyNumbersName(String extension) {

        Object fileName = new Random().nextInt()+ extension;
        return fileName;
    }
}
