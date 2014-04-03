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
    }

    private int size;
    private Node root;
    public static final int X_AXIS_ORIENTATION = 0;
    public static final int Y_AXIS_ORIENTATION = 1;

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
        //Note 1: If inserting | (x) and value is the same insert to the right
        //Note 2: Data structure is a set so if value is inserted twice only keep one
        insert(root, p, X_AXIS_ORIENTATION);
    }

    private void insert(Node node, Point2D p, int orientation) {
//        System.out.println("KdTree.insert");
        if (node == null) { //no entries in the data structure
//            System.out.println("root is null so inserting first element");
            RectHV rectHV = new RectHV(0, 0, 1, 1);
            root = new Node(p, rectHV, null, null, X_AXIS_ORIENTATION);
            size++;
            return;
        }

        if (orientation == X_AXIS_ORIENTATION) {
            if (Double.compare(p.x(), node.p.x()) < 0) {
                if (node.lb == null) {
//                    System.out.println("inserting on the left side using X_AXIS");
                    RectHV rectHV = new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
                    node.lb = new Node(p, rectHV, null, null, Y_AXIS_ORIENTATION);
                    size++;
                } else {
                    insert(node.lb, p, Y_AXIS_ORIENTATION);
                }
            } else {
                if (node.rb == null) {
//                    System.out.println("inserting on the right side using X_AXIS");
                    RectHV rectHV = new RectHV(node.rect.xmin(), node.rect.ymin(), node.p.x(), node.rect.ymax());
                    node.rb = new Node(p, rectHV, null, null, Y_AXIS_ORIENTATION);
                    size++;
                } else {
                    insert(node.rb, p, Y_AXIS_ORIENTATION);
                }
            }
        } else if (orientation == Y_AXIS_ORIENTATION) {
            if (Double.compare(p.y(), node.p.y()) < 0) {
                if (node.lb == null) {
//                    System.out.println("inserting on the left side using Y_AXIS");
                    RectHV rectHV = new RectHV(node.rect.ymin(), node.rect.xmin(), node.p.y(), node.rect.xmax());
                    node.lb = new Node(p, rectHV, null, null, X_AXIS_ORIENTATION);
                    size++;
                } else {
                    insert(node.lb, p, X_AXIS_ORIENTATION);
                }
            } else {
                if (node.rb == null) {
//                    System.out.println("inserting on the right side using Y_AXIS");
                    RectHV rectHV = new RectHV(node.rect.ymin(), node.rect.xmin(), node.p.y(), node.rect.xmax());
                    node.rb = new Node(p, rectHV, null, null, X_AXIS_ORIENTATION);
                    size++;
                } else {
                    insert(node.rb, p, X_AXIS_ORIENTATION);
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

//        StdDraw.setPenColor(StdDraw.BLACK);
//        StdDraw.setPenRadius(.0003);
//        StdDraw.square(.5, .5, .5);
        draw(root, 0.0, 1.0, 0);
    }

    private static class PrintNode {
        private Node node;
        private double parentX;
        private double parentY;

        private PrintNode(Node node, double parentX, double parentY) {
            this.node = node;
            this.parentX = parentX;
            this.parentY = parentY;
        }
    }

    private static int getOppositeOrientation(int originalOrientation) {
        if (originalOrientation == X_AXIS_ORIENTATION) {
            return Y_AXIS_ORIENTATION;
        } else {
            return X_AXIS_ORIENTATION;
        }
    }

    private void draw(Node node, double parentX, double parentY, int drawCount) {
        /*
                --Vertical Line--
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.setPenRadius(.0003);
                StdDraw.line(tmp.p.x(), 0, tmp.p.x(), 1);

                --Horizontal Line--
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.setPenRadius(.0003);
                StdDraw.line(tmp.p.x(), 0, tmp.p.x(), 1);

                --Point--
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(.0003);
                node.p.draw();
         */
        //        if (node != null && drawCount < DRAW_COUNT) {
//        if (node != null) {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        node.p.draw();
        StdDraw.setPenRadius(.0001);
//            StdDraw.text(node.p.x() + .03, node.p.y() + .03, node.p.toString());
        StdDraw.text(node.p.x() + .03, node.p.y() + .03, Integer.toString(drawCount));

        if (node.nodeOrientation == X_AXIS_ORIENTATION) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(.0003);
            if (node.p.y() > .5) {
                StdDraw.line(node.p.x(), 1, node.p.x(), parentY);
            } else {
                StdDraw.line(node.p.x(), 0, node.p.x(), parentY);
            }

            StdDraw.setPenColor(StdDraw.ORANGE);
            StdDraw.setPenRadius(.0003);
            node.rect.draw();
        } else {

            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius(.0003);
            if (node.p.x() > .5) {
                StdDraw.line(1, node.p.y(), parentX, node.p.y());
            } else {
                StdDraw.line(0, node.p.y(), parentX, node.p.y());
            }

            StdDraw.setPenColor(StdDraw.GREEN);
            StdDraw.setPenRadius(.0003);
            node.rect.draw();
        }
        if (node.lb != null)
            draw(node.lb, node.p.x(), node.p.y(), ++drawCount);
        if (node.rb != null)
            draw(node.rb, node.p.x(), node.p.y(), ++drawCount);
//        }

    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        //Note 1: If no points are in range return an empty Iterable<Point2D>
        //TODO: Implement this
        return null;
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        //TODO: Implement this
        return null;
    }

    public static void main(String[] args) {
        /*KdTree kdTree = new KdTree();
        Point2D p1 = new Point2D(0.00, 0.50);
        Point2D p2 = new Point2D(0.50, 1.00);
        Point2D p3 = new Point2D(0.50, 0.00);
        Point2D p4 = new Point2D(1.00, 0.50);
        Point2D p5 = new Point2D(0.75, 0.75);
        kdTree.insert(p1);
        kdTree.insert(p2);
        kdTree.insert(p3);
        kdTree.insert(p4);
        System.out.printf("kdTree contains %s = %b%n", p1, kdTree.contains(p1));
        System.out.printf("kdTree contains %s = %b%n", p2, kdTree.contains(p2));
        System.out.printf("kdTree contains %s = %b%n", p3, kdTree.contains(p3));
        System.out.printf("kdTree contains %s = %b%n", p4, kdTree.contains(p4));
        System.out.printf("kdTree contains %s = %b%n", p5, kdTree.contains(p5));*/
        KdTree kdTree = new KdTree();
        /*In in = new In(args[0]);      // input file
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            kdTree.insert(new Point2D(x, y));
        }*/
        kdTree.insert(new Point2D(0.7, 0.2));
        kdTree.insert(new Point2D(0.5, 0.4));
        kdTree.insert(new Point2D(0.2, 0.3));
        kdTree.insert(new Point2D(0.4, 0.7));
        kdTree.insert(new Point2D(0.9, 0.6));
//        System.out.printf("size of kdtree is: %d%n", kdTree.size());
        kdTree.draw();
    }
}