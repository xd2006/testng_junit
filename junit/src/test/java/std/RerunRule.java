package std;

import org.junit.Assert;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Alex on 11.09.2016.
 */
public class RerunRule implements TestRule {

    @Override
    public Statement apply(Statement base, Description desc) {
        return new ReRunStatement(base, desc);
    }

    public class ReRunStatement extends Statement {

        private final Statement base;
        private Description desc;

        public ReRunStatement(Statement base, Description desc) {
            this.base = base;
            this.desc = desc;
        }

        @Override
        public void evaluate() throws Throwable {

            if (desc.getAnnotation(Unstable.class) != null) {
                int count = 1;
                Throwable exc=null;
                do {
                    exc = null;
                    try {
                        base.evaluate();
                    } catch (Throwable t) {
                        exc = t;
                    }
                    count++;
                }while (count <= desc.getAnnotation(Unstable.class).value() && exc != null);
                Assert.assertTrue("Test failed after "+(count-1) +" attempts",exc==null);
            }
            else base.evaluate();


        }

    }
}
