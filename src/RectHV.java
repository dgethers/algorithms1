/**
 * User: outzider
 * Date: 3/26/2014
 * Time: 2:49 PM
 */
public class RectHV {

    // construct the rectangle [xmin, xmax] x [ymin, ymax]
    // throw a java.lang.IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
    public RectHV(double xmin, double ymin, double xmax, double ymax) {
        //TODO: Implement this
    }

    // minimum x-coordinate of rectangle
    public double xmin() {
        //TODO: Implement this
        return 0.0;
    }

    // minimum y-coordinate of rectangle
    public double ymin() {
        //TODO: Implement this
        return 0.0;

    }

    // maximum x-coordinate of rectangle
    public double xmax() {
        //TODO: Implement this
        return 0.0;
    }

    // maximum y-coordinate of rectangle
    public double ymax() {
        //TODO: Implement this
        return 0.0;

    }

    // does this rectangle contain the point p (either inside or on boundary)?
    public boolean contains(Point2D p) {
        //TODO: Implement this
        return false;
    }

    // does this rectangle intersect that rectangle (at one or more points)?
    public boolean intersects(RectHV that) {
        //TODO: Implement this
        return false;
    }

    // Euclidean distance from point p to the closest point in rectangle
    public double distanceTo(Point2D p) {
        //TODO: Implement this
        return 0.0;
    }

    // square of Euclidean distance from point p to closest point in rectangle
    public double distanceSquaredTo(Point2D p) {
        //TODO: Implement this
        return 0.0;

    }

    // does this rectangle equal that?
    public boolean equals(Object that) {
        //TODO: Implement this
        return false;
    }

    // draw to standard draw
    public void draw() {
        //TODO: Implement this
    }

    // string representation
    public String toString() {
        //TODO: Implement this
        return null;
    }
}
