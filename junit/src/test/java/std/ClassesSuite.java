package std;

import org.junit.ClassRule;
import org.junit.experimental.categories.Categories;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
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


    public static TemporaryFolder workFolder = new TemporaryFolder();
    public static ExternalResource pathForTest = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            directoryPathStr= workFolder.getRoot().toPath().toString() + "\\";
        }
    };

    @ClassRule
    public static RuleChain rules = RuleChain
            .outerRule(workFolder)
            .around(pathForTest);
}








