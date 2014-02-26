import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * User: outzider
 * Date: 2/25/14
 * Time: 6:14 PM
 */
public class PointTest {

    @Test
    public void equalPoints() {
        Point p = new Point(100, 100);
        Point q = new Point(100, 100);
        assertEquals(0, p.compareTo(q));
    }

    @Test
    public void lessThanPoint() {
        Point p = new Point(100, 50);
        Point q = new Point(100, 130);
        assertEquals(-1, p.compareTo(q));
    }

    @Test
    public void lessThanPointWithNegativePoint() {
        Point p = new Point(-100, 100);
        Point q = new Point(-100, 200);
        assertEquals(-1, p.compareTo(q));
    }

    @Test
    public void greaterThanPoint() {
        Point p = new Point(150, 250);
        Point q = new Point(25, 250);
        assertEquals(1, p.compareTo(q));
    }

    @Test
    public void greaterThanPointWithNegativePoint() {
        Point p = new Point(-300, 10);
        Point q = new Point(10, 10);
        assertEquals(-1, p.compareTo(q));

    }

    @Test
    public void positiveInfinityForVerticalSlope() {
        Point p = new Point(100, 100);
        Point q = new Point(100, 400);
        assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q), 0);
    }

    @Test
    public void zeroForHorizontalSlope() {
        Point p = new Point(100, 100);
        Point q = new Point(-400, 100);
        assertEquals(0, p.slopeTo(q), 0);
    }

    @Test
    public void negativeInfinityForDegeneratePoints() {
        Point p = new Point(-200, -200);
        Point q = new Point(-200, -200);
        assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(q), 0);
    }

    @Test
    public void randomPoint() {
        Point p = new Point(-200, 200);
        Point q = new Point(400, -400);
        assertNotSame(0, p.slopeTo(q));
    }

    @Test
    public void slopeComparator() throws Exception {
        Point point1 = new Point(4, 1);
        Point point2 = new Point(1, 4);
        Point origin = new Point(0, 0);
        Point[] points = {point2, point1};
        System.out.println(Arrays.toString(points));
        Arrays.sort(points, origin.SLOPE_ORDER);
        System.out.println(Arrays.toString(points));

        assertEquals(point2, points[0]);
        assertEquals(point1, points[1]);
    }
}
