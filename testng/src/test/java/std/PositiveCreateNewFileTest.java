package std;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 27.08.2016.
 */

@Test(groups = {"positive"}, priority = 1, alwaysRun = true, dataProviderClass = DataProviders.class)
public class PositiveCreateNewFileTest extends TestBase {

    @Test(dataProvider = "regularFiles")
    public void regularFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName), isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }


    @Test(dataProvider = "numberFiles")
    public void fileNumbersTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }

    @Test(dataProvider = "noExFiles")
    public void noExtensionFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }

    @Test
    @TempDir(read = true,write=true)
    public void canCreateFileInWritableReadableDir() throws IOException {
        String fileName = "valid.txt";
        boolean bool;
        File f = new File(directoryPathStr+fileName);
        bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue("File wasn't created",isFileExistsInFolder(directoryPathStr,fileName));
    }

    @Test
    @TempDir(read = false,write=true)
    public void canCreateFileInWritableNotReadableDir() throws IOException {
        String fileName = "valid.txt";
        boolean bool;
        File f = new File(directoryPathStr+fileName);
        bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue("File wasn't created",isFileExistsInFolder(directoryPathStr,fileName));
    }





}
