package std;

import com.tngtech.java.junit.dataprovider.DataProvider;
import org.junit.runners.model.FrameworkMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 11.09.2016.
 */
public class UniversalDataProviders {

    @DataProvider
    public static List<Object> dataSourceLoader(FrameworkMethod testMethod) throws IOException {
        DataSource ds = testMethod.getAnnotation(DataSource.class);
        if (ds == null) {
            throw new Error("Test method has no @DataSource annotation: " + testMethod.getName());
        }
        switch (ds.type()) {
            case RESOURCE:
                return loadDataFromResource(ds.value());

            case FILE:
                return loadDataFromFile(ds.value());

            case METHOD:
                return loadDataFromMethod(ds.value());

            default:
                throw new Error("Data source type is not supported: " + ds.type());
        }
    }

    private static List<Object> loadDataFromMethod(String value) {
return null;
    }

    private static List<Object> loadDataFromResource(String value) throws IOException {
        return loadDataFromInputStream(UniversalDataProviders.class.getResourceAsStream(value));
    }

    private static List<Object> loadDataFromFile(String value) throws IOException {
        return loadDataFromInputStream(new FileInputStream(new File(value)));
    }

    private static List<Object> loadDataFromInputStream(InputStream input) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(
                input));

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

}
