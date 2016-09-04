package std;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Alex on 04.09.2016.
 */


@RunWith(Categories.class)
@Suite.SuiteClasses({
        NegativeCreateNewFileTest.class,
        PositiveCreateNewFileTest.class
})
@Categories.ExcludeCategory(MyCategories.BrokenTests.class)

public class MySuite extends TestBase{

    @BeforeClass
        public static void setUp() {
            String tmp_dir_prefix = "test_";
            try {
                Path tmp = Files.createTempDirectory(tmp_dir_prefix);
                directoryPathStr = tmp.toString()+"\\";
                direcoryPath = tmp;
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        @AfterClass
        public static void cleanUp() throws IOException {

            deleteAllFilesFolder(direcoryPath.toString());
            Files.delete(direcoryPath);

        }

    }

