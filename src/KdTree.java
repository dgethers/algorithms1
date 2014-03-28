/**
 * User: outzider
 * Date: 3/26/2014
 * Time: 3:08 PM
 */
public class KdTree {

    private static class Node {
        private Point2D p; // the point
        private RectHV rect; // the axis-aligned rectangle corresponding to this node
        private Node lb; // the left/bottom subtree
        private Node rb; // the right/top subtree
    }

    private int size;

    // construct an empty set of points
    public KdTree() {
        //TODO: Implement this
    }

    // is the set empty?
    public boolean isEmpty() {
        return size < 1;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        //TODO: Implement this
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        //TODO: Implement this
        return false;
    }

    // draw all of the points to standard draw
    public void draw() {
        //TODO: Implement this
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        //TODO: Implement this
        return null;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        //TODO: Implement this
        return null;
    }
}