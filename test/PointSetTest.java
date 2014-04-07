import org.junit.Test;

import static org.junit.Assert.*;

/**
 * User: outzider
 * Date: 4/5/14
 * Time: 3:21 PM
 */
public class PointSetTest {

    @Test
    public void isEmptyOnEmptyPointSet() {
        PointSET pointSET = new PointSET();
        assertTrue(pointSET.isEmpty());
    }

    @Test
    public void isEmptyOnNonEmptyPointSet() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.0, 0.0));
        assertFalse(pointSET.isEmpty());
    }

    @Test
    public void sizeOnEmptyPointSet() {
        PointSET pointSET = new PointSET();
        assertEquals(0, pointSET.size());
    }

    @Test
    public void sizeOnPointSetWithTwoPoints() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.0, 0.0));
        pointSET.insert(new Point2D(0.5, 0.5));
        assertEquals(2, pointSET.size());
    }

    @Test
    public void containsOnExistingPoint() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.75, 0.50));
        assertTrue(pointSET.contains(new Point2D(0.75, 0.50)));
    }

    @Test
    public void containsOnNonExistentPoint() {
        PointSET pointSET = new PointSET();
        pointSET.insert(new Point2D(0.75, 0.50));
        assertFalse(pointSET.contains(new Point2D(0.50, 0.75)));
    }
}
