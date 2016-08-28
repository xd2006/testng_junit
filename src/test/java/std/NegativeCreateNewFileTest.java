package std;

import junit.framework.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by Alex on 28.08.2016.
 */
@Test(groups = {"negative"}, priority = 2)
public class NegativeCreateNewFileTest extends TestBase  {
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
