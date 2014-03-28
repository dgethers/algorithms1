import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * User: outzider
 * Date: 3/28/2014
 * Time: 1:56 PM
 */
public class KdTreeTest {

    @Test
    public void insert1Item() {
        KdTree kdTree = new KdTree();
        Point2D p = new Point2D(0.0, 0.0);
        kdTree.insert(p);
        assertTrue(kdTree.contains(p));
    }

    @Test
    public void sizeWithEmptyTree() {
        KdTree kdTree = new KdTree();
        assertEquals(0, kdTree.size());
    }

    @Test
    public void isEmptyOnEmptyList() {
        KdTree kdTree = new KdTree();
        assertTrue(kdTree.isEmpty());
    }
}
