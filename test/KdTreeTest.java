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

    @Test
    public void rangeOfPointsInEmptyRectange() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
        Iterable<Point2D> range = kdTree.range(new RectHV(0.0, 0.0, 0.0, 0.0));
        assertFalse(range.iterator().hasNext());
    }

    @Test
    public void nearestForCircle10() {
        KdTree kdTree = new KdTree();
        loadPointsFromFileIntoKdTree(kdTree, "in/kdtree/circle10.txt");
        Point2D nearest = kdTree.nearest(new Point2D(0.81, 0.3));
        assertEquals(new Point2D(0.975528, 0.345492), nearest);
        assertEquals(new Point2D(0.206107, 0.904508), kdTree.nearest(new Point2D(0.237891, 0.884570)));
        assertEquals(new Point2D(0.206107, 0.904508), kdTree.nearest(new Point2D(1.043555, 0.648242)));

    }

    private void loadPointsFromFileIntoKdTree(KdTree kdTree, String inputFile) {
        In in = new In(inputFile);
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            kdTree.insert(new Point2D(x, y));
        }
    }
}
