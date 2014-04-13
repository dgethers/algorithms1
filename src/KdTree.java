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
        private int nodeOrientation;

        private Node(Point2D p, RectHV rect, Node lb, Node rb, int nodeOrientation) {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rb = rb;
            this.nodeOrientation = nodeOrientation;
        }

        @Override
        public String toString() {
            return String.format("(%f, %f) with rect: %s", p.x(), p.y(), rect.toString());
        }
    }

    private static final int X_AXIS_ORIENTATION = 0;
    private static final int Y_AXIS_ORIENTATION = 1;
    private int size;
    private Node root;

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
        //Note 1: If inserting | (x) and value is the same insert to the right
        //Note 2: Data structure is a set so if value is inserted twice only keep one
        insert(root, p, X_AXIS_ORIENTATION);
    }

    private void insert(Node node, Point2D p, int orientation) {
        if (node == null) { //no entries in the data structure
            RectHV rectHV = new RectHV(0, 0, 1, 1);
            root = new Node(p, rectHV, null, null, X_AXIS_ORIENTATION);
            size++;
            return;
        }

        if (node.p.equals(p)) {
            return;
        }

        if (orientation == X_AXIS_ORIENTATION) {
            if (Double.compare(p.x(), node.p.x()) < 0) {
                if (node.lb == null) {
                    RectHV rectHV = new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
                    node.lb = new Node(p, rectHV, null, null, Y_AXIS_ORIENTATION);
                    size++;
                } else {
                    if (!node.lb.p.equals(p)) {
                        insert(node.lb, p, Y_AXIS_ORIENTATION);
                    }
                }
            } else {
                if (node.rb == null) {
                    RectHV rectHV = new RectHV(node.p.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                    node.rb = new Node(p, rectHV, null, null, Y_AXIS_ORIENTATION);
                    size++;
                } else {
                    if (!node.rb.p.equals(p)) {
                        insert(node.rb, p, Y_AXIS_ORIENTATION);
                    }
                }
            }
        } else {
            if (Double.compare(p.y(), node.p.y()) < 0) {
                if (node.lb == null) {
                    RectHV rectHV = new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.p.y());
                    node.lb = new Node(p, rectHV, null, null, X_AXIS_ORIENTATION);
                    size++;
                } else {
                    if (!node.lb.p.equals(p)) {
                        insert(node.lb, p, X_AXIS_ORIENTATION);
                    }
                }
            } else {
                if (node.rb == null) {
                    RectHV rectHV = new RectHV(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.rect.ymax());
                    node.rb = new Node(p, rectHV, null, null, X_AXIS_ORIENTATION);
                    size++;
                } else {
                    if (!node.rb.p.equals(p)) {
                        insert(node.rb, p, X_AXIS_ORIENTATION);
                    }
                }
            }
        }
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {

        return get(root, p, X_AXIS_ORIENTATION) != null;
    }

    private Point2D get(Node node, Point2D p, int orientation) {
        if (node == null) {
            return null;
        } else if (node.p.equals(p)) {
            return node.p;
        } else {
            if (orientation == X_AXIS_ORIENTATION) {
                if (Double.compare(p.x(), node.p.x()) < 0) {
                    return get(node.lb, p, Y_AXIS_ORIENTATION);
                } else {
                    return get(node.rb, p, Y_AXIS_ORIENTATION);
                }
            } else if (orientation == Y_AXIS_ORIENTATION) {
                if (Double.compare(p.y(), node.p.y()) < 0) {
                    return get(node.lb, p, X_AXIS_ORIENTATION);
                } else {
                    return get(node.rb, p, X_AXIS_ORIENTATION);
                }
            }
        }

        return null; //should never get here
    }

    // draw all of the points to standard draw
    public void draw() {
        //Note 1: USe StdDraw.setPenColor(StdDraw.BLACK) and StdDraw.setPenRadius(.01) before before drawing the points
        //Note 2: StdDraw.setPenColor(StdDraw.RED) or StdDraw.setPenColor(StdDraw.BLUE) and StdDraw.setPenRadius() before drawing the splitting lines

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.0003);
        StdDraw.square(.5, .5, .5);
        draw(root);

    }

    private void draw(Node current) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        current.p.draw();

        if (current.nodeOrientation == X_AXIS_ORIENTATION) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.0003);
            StdDraw.line(current.p.x(), current.rect.ymin(), current.p.x(), current.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(.0003);
            StdDraw.line(current.rect.xmin(), current.p.y(), current.rect.xmax(), current.p.y());
        }

        if (current.lb != null)
            draw(current.lb);

        if (current.rb != null)
            draw(current.rb);
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        //Note 1: If no points are in range return an empty Iterable<Point2D>
        Stack<Point2D> rangedPoints = new Stack<Point2D>();
        range(rect, root, rangedPoints);

        return rangedPoints;
    }

    private void range(RectHV rect, Node node, Stack<Point2D> rangedPoints) {
        if (node != null && node.rect.intersects(rect)) {

            if (rect.contains(node.p)) {
                rangedPoints.push(node.p);
            }

            if (node.lb != null && node.lb.rect.intersects(rect)) {
                range(rect, node.lb, rangedPoints);
            }

            if (node.rb != null && node.rb.rect.intersects(rect)) {
                range(rect, node.rb, rangedPoints);
            }
        }

    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {

        if (root == null) {
            return null;
        }

        return nearest(root, p).p;
    }

    private Node nearest(Node node, Point2D p) {
        Node nearest = node;
        if (node.rect.contains(p)) {
            if (node.rb != null && node.rect.contains(p)) {
                Node returned = nearest(node.rb, p);
                if (returned.p.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p)) {
                    nearest = returned;
                }
            }

            if (node.lb != null && node.rect.contains(p)) {
                Node returned = nearest(node.lb, p);
                if (returned.p.distanceSquaredTo(p) < nearest.p.distanceSquaredTo(p)) {
                    nearest = returned;
                }
            }
        }

        return nearest;
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        In in = new In(args[0]);      // input file
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            kdTree.insert(new Point2D(x, y));
        }
        kdTree.draw();
//        System.out.printf("nearest is: %s and should be %s%n", new Point2D(0.206107, 0.095492),  kdTree.nearest(new Point2D(0.206107, 0.098000)));
//        System.out.printf("nearest is: %s and should be %s%n", new Point2D(0.975528, 0.654508),  kdTree.nearest(new Point2D(0.995528, 0.670000)));
//        System.out.printf("nearest is: %s and should be %s%n", new Point2D(0.793893, 0.904508), kdTree.nearest(new Point2D(0.936133, 0.946875)));
    }
}