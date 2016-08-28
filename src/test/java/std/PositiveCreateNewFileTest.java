package std;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Alex on 27.08.2016.
 */

@Test(groups = {"positive"}, priority = 1, alwaysRun = true, dataProviderClass = DataProviders.class)
public class PositiveCreateNewFileTest extends TestBase {

    @Test(dataProvider = "regularFiles")
    public void Positive_RegularFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName), isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }


    @Test(dataProvider = "numberFiles")
    public void Positive_FileNumbersTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }

    @Test(dataProvider = "noExFiles")
    public void Positive_NoExtensionFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }





}
