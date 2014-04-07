import java.util.Arrays;

/**
 * User: outzider
 * Date: 3/26/2014
 * Time: 3:08 PM
 */
public class PointSET {

    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.isEmpty();
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point p to the set (if it is not already in the set)
    public void insert(Point2D p) {
        points.add(p);
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all of the points to standard draw
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Stack<Point2D> stack = new Stack<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point)) {
                stack.push(point);
            }

        }

        return stack;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (points.isEmpty()) {
            return null;
        } else {
            Point2D[] array = new Point2D[points.size()];
            int index = 0;
            for (Point2D point : points) {
                array[index++] = point;
            }
            Arrays.sort(array, p.DISTANCE_TO_ORDER);
            return array[0];
        }
    }
}