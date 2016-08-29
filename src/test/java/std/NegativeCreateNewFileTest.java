package std;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Alex on 28.08.2016.
 */
@Test(groups = {"negative"}, priority = 2, alwaysRun = true, dataProviderClass = DataProviders.class)
public class NegativeCreateNewFileTest extends TestBase  {

    @Test(dataProvider="loadWrongFileNameFromFile")
    public void Negative_ComparingSymbolsTest(String fileName) throws Exception {
        boolean bool = false;
        Exception exc=null;
        try {
            File f = new File(directoryPathStr + fileName);
            bool = f.createNewFile();
        } catch (IOException e) {
            exc=e;
        }
        finally{
            Assert.assertTrue(exc!=null && exc.getMessage().equals("The filename, directory name, or volume label syntax is incorrect"));
            Assert.assertFalse(bool);
            Assert.assertFalse(String.format("File %s is created",fileName),isFileExistsInFolder(directoryPathStr,fileName));
        }
    }


    public void Negative_NoNameTest() throws Exception {

        boolean bool;

        File f = new File(directoryPathStr);
        bool = f.createNewFile();
        Assert.assertFalse(bool);
        Assert.assertFalse("File without name was created",isFileExistsInFolder(directoryPathStr,""));
    }

}
