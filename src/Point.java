/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator();       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        double yResult = that.y - y;
        double xResult = that.x - x;

        if (that.x == x && that.y == y) { //degenerate point
            return Double.NEGATIVE_INFINITY;
        }

        if (yResult == 0.0) { //horizontal line
            return 0;
        }

        if (xResult == 0.0) { //vertical line
            return Double.POSITIVE_INFINITY;
        }

        return yResult / xResult;
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (y < that.y || (y == that.y && x < that.x)) {
            return -1;
        }

        if (y > that.y || (y == that.y && x > that.x)) {
            return 1;
        }

        return 0;
    }

    // return string representation of this point
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public class SlopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point p, Point q) {
            double originToP = slopeTo(p);
            double originToQ = slopeTo(q);

            if (originToP < originToQ) {
                return -1;
            }

            if (originToP > originToQ) {
                return 1;
            }

            return 0;
        }
    }

    // unit test
    public static void main(String[] args) {

    }
}
