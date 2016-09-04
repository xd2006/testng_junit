package std;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static std.MyCategories.PositiveTests;

/**
 * Created by Alex on 04.09.2016.
 */
public class PositiveCreateNewFileTest extends TestBase {

    @Test
    @Category(PositiveTests.class)
    public void regularFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertThat(bool,is(true));
        Assert.assertTrue(String.format("File %s wasn't created",fileName), isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }


    @Test
    @Category(PositiveTests.class)
    public void fileNumbersTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertThat(bool,is(true));
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }

    @Test
    @Category(PositiveTests.class)
    public void noExtensionFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertThat(bool,is(true));
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr.toString(),fileName));
    }
}
