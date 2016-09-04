package std;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static std.MyCategories.NegativeTests;

/**
 * Created by Alex on 04.09.2016.
 */
public class NegativeCreateNewFileTest extends TestBase {

    @Test
    @Category(NegativeTests.class)
    public void wrongSymbolsTest(String fileName) throws Exception {
        boolean bool = false;
        Exception exc = null;
        try {
            File f = new File(directoryPathStr + fileName);
            bool = f.createNewFile();
        } catch (IOException e) {
            exc = e;
        } finally {
//            Assert.assertThat(exc, notNullValue());
//            Assert.assertThat(bool,is(false));
//            Assert.assertFalse(String.format("File %s is created", fileName), isFileExistsInFolder(directoryPathStr, fileName));
            SoftAssertions s = new SoftAssertions();
            s.assertThat(exc).isNot(null);
            s.assertThat(bool).isFalse();
            s.assertThat(isFileExistsInFolder(directoryPathStr, fileName)).isFalse().overridingErrorMessage(String.format("File %s is created", fileName));
            s.assertAll();

        }
    }

    @Test
    @Category(NegativeTests.class)
    public void noNameTest() throws Exception {

        boolean bool;

        File f = new File(directoryPathStr);
        bool = f.createNewFile();
        Assert.assertThat(bool,is(false));
        Assert.assertFalse("File without name was created", isFileExistsInFolder(directoryPathStr, ""));
    }
}
