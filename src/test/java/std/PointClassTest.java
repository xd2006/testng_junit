package std;

import org.apache.commons.math3.util.Precision;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by Alex on 28.05.2016.
 */
public class PointClassTest {

    @Test(enabled = false)
    public void DistanceCheckRounding()
    {

        Point p1 = new Point(-2.3,8);
        Point p2 = new Point(8.5,0.7);
        double expectedDistance = 13.036;
        Assert.assertEquals(Precision.round(p1.distanceToPoint(p2),3), expectedDistance);
        Assert.assertEquals(Precision.round(p2.distanceToPoint(p1),3), expectedDistance);

    }

    @Test(enabled = false)
    public void DistanceCheckPrecise()
    {

        Point p1 = new Point(-10,100);
        Point p2 = new Point(2.5,0.75);
        double expectedDistance = 100.03405670070569;
        Assert.assertEquals(p1.distanceToPoint(p2), expectedDistance);
        Assert.assertEquals(p2.distanceToPoint(p1), expectedDistance);

    }
    @Test(enabled = false)
    public void DistanceCheckZeroPoint()
    {

        Point p1 = new Point(0,0);
        Point p2 = new Point(0.5,0.7);
        double expectedDistance = 0.8602325267042626;
        Assert.assertEquals(p1.distanceToPoint(p2), expectedDistance);
        Assert.assertEquals(p2.distanceToPoint(p1), expectedDistance);

    }




}
