package std;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static std.MyCategories.PositiveTests;

/**
 * Created by Alex on 04.09.2016.
 */
@RunWith(DataProviderRunner.class)
public class PositiveCreateNewFileTest extends TestBase {

    @Test
    @Category(PositiveTests.class)
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(value = "/correctfilenames.data", type= DataSource.Type.RESOURCE)
    public void preparedFilesTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertThat(bool,is(true));
        Assert.assertTrue(String.format("File %s wasn't created",fileName), isFileExistsInFolder(directoryPathStr,fileName));
    }



    @Test
    @Category(PositiveTests.class)
    @UseDataProvider(value = "regularFiles", location = DataProviders.class)
    public void regularFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertThat(bool,is(true));
        Assert.assertTrue(String.format("File %s wasn't created",fileName), isFileExistsInFolder(directoryPathStr,fileName));
    }


    @Test
    @Category(PositiveTests.class)
    @UseDataProvider(value = "numberFiles", location = DataProviders.class)
    public void fileNumbersTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertThat(bool,is(true));
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr,fileName));
    }

    @Test
    @Category(PositiveTests.class)
    @UseDataProvider(value = "noExFiles", location = DataProviders.class)
    public void noExtensionFileTest(String fileName) throws Exception {
        File f = new File(directoryPathStr + fileName);
        boolean bool = f.createNewFile();
        Assert.assertThat(bool,is(true));
        Assert.assertTrue(String.format("File %s wasn't created",fileName),isFileExistsInFolder(directoryPathStr,fileName));
    }
}
