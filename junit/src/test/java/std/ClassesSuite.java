package std;

import org.junit.ClassRule;
import org.junit.experimental.categories.Categories;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Alex on 04.09.2016.
 */


@RunWith(Categories.class)
@Suite.SuiteClasses({
        NegativeCreateNewFileTest.class,
        PositiveCreateNewFileTest.class
})
@Categories.ExcludeCategory(MyCategories.BrokenTests.class)

public class ClassesSuite extends TestBase {

    @ClassRule
    public static TemporaryFolder workFolder = new TemporaryFolder() {
        @Override
        public void before() throws Throwable {
            super.before();
            directoryPathStr = workFolder.getRoot().toPath().toString() + "\\";
        }
    };

}









