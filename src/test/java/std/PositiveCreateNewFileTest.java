package std;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Alex on 27.08.2016.
 */

@Test(groups = {"positive"}, priority = 1, alwaysRun = true)
public class PositiveCreateNewFileTest extends TestBase {

    public void Positive_txtFileTest() throws Exception {
        String fileName = "test.txt";
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName), isFileExistsInFolder(directoryPathStr.toString(),fileName));

    }


    public void Positive_xmlFileNumbersTest() throws Exception {
        String fileName = "123.xml";
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }


    public void Positive_NoExtensionFileTest() throws Exception {
        String fileName = "123testFile";
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }





}
