import java.util.Arrays;
import java.util.Iterator;

/**
 * User: outzider
 * Date: 2/21/14
 * Time: 11:07 PM
 */
public class Fast {

    private Point[] coordinates;

    public Fast() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
    }

    private void setPointsFromInputFile(String inputFile) {
        In in = new In(inputFile);
        int N = in.readInt();
        coordinates = new Point[N];
        for (int i = 0; i < coordinates.length; i++) {
            Point point = new Point(in.readInt(), in.readInt());
            coordinates[i] = point;
            point.draw();
        }
    }

    private void printAndDrawCollinearLineSegments() {
        for (int i = 0; i < coordinates.length; i++) {
            Point p = coordinates[i];
            Point[] tmp = Arrays.copyOfRange(coordinates, i + 1, coordinates.length);
            Arrays.sort(tmp, p.SLOPE_ORDER);
            Bag<Point>[] items = new Bag[tmp.length];
            int bagIndex = -1;
            double slopeTo = Double.NaN;

            if (tmp.length > 0) {
                for (int j = 0; j < tmp.length; j++) {
                    double currentSlope = p.slopeTo(tmp[j]);
                    if (currentSlope == slopeTo) {
                        items[bagIndex].add(tmp[j]);
                    } else {
                        Bag<Point> matchingPoints = new Bag<Point>();
                        matchingPoints.add(p);
                        matchingPoints.add(tmp[j]);
                        items[++bagIndex] = matchingPoints;
                        slopeTo = currentSlope;
                    }
                }
            }

            for (int j = 0; j < items.length; j++) {
                if (items[j] != null && items[j].size() > 3) {
                    Point[] temp = new Point[items[j].size()];
                    int index = 0;
                    Iterator<Point> iterator = items[j].iterator();
                    while (iterator.hasNext()) {
                        temp[index++] = iterator.next();
                    }
                    Arrays.sort(temp);
                    for (int k = 0; k < temp.length; k++) {
                        System.out.print(temp[k]);

                        if (k < temp.length - 1) {
                            System.out.print(" -> ");
                        }
                    }
                    temp[0].drawTo(temp[temp.length - 1]);
                    System.out.println();
                }
            }
        }
    }

    public static void main(String[] args) {
        Fast fast = new Fast();
        fast.setPointsFromInputFile(args[0]);
        fast.printAndDrawCollinearLineSegments();
    }
}
