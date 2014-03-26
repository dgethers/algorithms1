import java.util.Arrays;

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
        Arrays.sort(coordinates);
        Point[] tmp = Arrays.copyOfRange(coordinates, 0, coordinates.length);
        for (Point p : coordinates) {
            Arrays.sort(tmp, p.SLOPE_ORDER);
            double slopeTo = Double.NaN;
            int min = -1;
            int max = -1;
            int matchCount = 1;
            for (int j = 0; j < tmp.length; j++) {
                Point q = tmp[j];

                if (slopeTo == p.slopeTo(q)) {
                    if (min == -1) {
                        min = j;
                    }

                    max = j;
                    matchCount++;
                }

                if (slopeTo != p.slopeTo(q)) {
                    if (matchCount > 2 && pIsLessThanAllOfItsPart(tmp, p, min, max)) {
                        printAndDrawLineSegment(tmp, p, min, max, matchCount);
                    }
                    min = -1;
                    matchCount = 1;
                }

                slopeTo = p.slopeTo(q);
            }

            if (matchCount > 2 && pIsLessThanAllOfItsPart(tmp, p, min, max)) {
                printAndDrawLineSegment(tmp, p, min, max, matchCount);
            }
        }
    }

    private boolean pIsLessThanAllOfItsPart(Point[] array, Point p, int min, int max) {
        for (int i = min - 1; i <= max; i++) {
            if (p.compareTo(array[i]) < 0) {
                return false;
            }
        }

        return true;
    }

    private void printAndDrawLineSegment(Point[] array, Point p, int min, int max, int matchCount) {
        int index = 0;
        Point[] result = new Point[matchCount + 1];
        result[index++] = p;
        for (int i = min - 1; i <= max; i++) {
            result[index++] = array[i];
        }
        Arrays.sort(result);

        for (int i = 0; i < result.length; i++) {
            StdOut.print(result[i]);

            if (i < result.length - 1) {
                StdOut.print(" -> ");
            }
        }
        StdOut.println();
        result[0].drawTo(result[result.length - 1]);
    }

    public static void main(String[] args) {
        Fast fast = new Fast();
        fast.setPointsFromInputFile(args[0]);
        fast.printAndDrawCollinearLineSegments();
    }
}