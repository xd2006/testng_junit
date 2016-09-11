package std;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static std.MyCategories.NegativeTests;

/**
 * Created by Alex on 04.09.2016.
 */
@RunWith(DataProviderRunner.class)
public class NegativeCreateNewFileTest extends TestBase {

    private static int attempt = 1;

    @Test
    @Category(NegativeTests.class)
    @UseDataProvider("loadWrongFileNameFromFile")
    public void wrongSymbolsTest(String fileName) throws Exception {
        boolean bool = false;
        Exception exc = null;
        try {
            File f = new File(directoryPathStr + fileName);
            bool = f.createNewFile();
        } catch (IOException e) {
            exc = e;
        } finally {
            SoftAssertions s = new SoftAssertions();
            s.assertThat(exc).isNotNull();
            s.assertThat(bool).isFalse();
            s.assertThat(isFileExistsInFolder(directoryPathStr, fileName)).withFailMessage(String.format("File %s is created", fileName)).isFalse();
            s.assertAll();

        }
    }

    @Test
    @Unstable(4)
    @Category(NegativeTests.class)
    public void noNameTest() throws Exception {
        if (attempt == 4) {
            attempt = 1;
        boolean bool;
        File f = new File(directoryPathStr);
        bool = f.createNewFile();
        Assert.assertThat(bool,is(false));
        Assert.assertFalse("File without name was created", isFileExistsInFolder(directoryPathStr, ""));
        } else {
            Assert.fail("Failed on " + (attempt++) + " attempt");
        }
    }
}
