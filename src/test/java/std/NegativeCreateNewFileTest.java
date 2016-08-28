package std;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Alex on 28.08.2016.
 */
@Test(groups = {"negative"}, priority = 2, alwaysRun = true)
public class NegativeCreateNewFileTest extends TestBase  {
    public void Negative_ComparingSymbolsTest() throws Exception {
        String fileName = "Fi<>le.txt";
        boolean bool = false;
        try {
            File f = new File(directoryPathStr + "Fi<>le.txt");
            bool = f.createNewFile();
        } catch (Exception e) {
        }
        finally{
            Assert.assertFalse(bool);
            Assert.assertFalse(String.format("File %s is created",fileName),isFileExistsInFolder(directoryPathStr,fileName));
        }
    }


    public void Negative_NoNameTest() throws Exception {

        boolean bool = false;
        try {
            File f = new File(directoryPathStr);
            bool = f.createNewFile();

        } catch (Exception e) {
        }
        finally{
            Assert.assertFalse(bool);
            Assert.assertFalse("File without name was created",isFileExistsInFolder(directoryPathStr,""));
        }
    }

}
