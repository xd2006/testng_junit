package std;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Alex on 27.08.2016.
 */

@Test
public class CreateNewFileFunctionTest extends TestBase {

    public void Positive_txtFileTest() throws Exception {
        File f = new File(directoryPathStr + "test.txt");
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
    }


    public void Positive_xmlFileNumbersTest() throws Exception {

        File f = new File(directoryPathStr + "123.xml");
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
    }


    public void Positive_NoExtensionFileTest() throws Exception {

        File f = new File(directoryPathStr + "123testFile");
        boolean bool = f.createNewFile();
        Assert.assertTrue(bool);
    }


    public void Negative_ComparingSymbolsTest() throws Exception {
        File f = null;
        boolean bool = false;
        try {
            f = new File(directoryPathStr + "Fi<>le.txt");
            bool = f.createNewFile();
            Assert.assertFalse(bool);
        } catch (Exception e) {
            Assert.assertFalse(bool);
        }
    }


    public void Negative_NoNameTest() throws Exception {
        File f = null;
        boolean bool = false;
        try {
            f = new File(directoryPathStr);
            bool = f.createNewFile();
            Assert.assertFalse(bool);
        } catch (Exception e) {
            Assert.assertFalse(bool);
        }
    }


}
