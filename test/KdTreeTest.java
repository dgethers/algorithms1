import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void isEmptyOnNonEmptyList() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(1.0, 0.5));
        assertFalse(kdTree.isEmpty());
    }

    @Test
    public void correctSize() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(1.0, 0.5));
        kdTree.insert(new Point2D(0.75, 1.0));
        kdTree.insert(new Point2D(0.40, .75));
        assertEquals(3, kdTree.size());
    }

    @Test
    public void insertDuplicateItems() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(1.0, 0.5));
        kdTree.insert(new Point2D(1.0, 0.5));
        assertEquals(1, kdTree.size());
    }
}
