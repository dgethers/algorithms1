import java.util.Arrays;

/**
 * User: outzider
 * Date: 2/21/14
 * Time: 11:07 PM
 */
public class Fast {

    private Point[] coordinates;

    //TODO: Uncomment calls to draw points and lines
    public Fast() {
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
    }

    private void setPointsFromInputFile(String inputFile) {
        In in = new In(inputFile);
        int N = in.readInt();
        coordinates = new Point[N];
        for (int i = 0; i < coordinates.length; i++) {
            Point point = new Point(in.readInt(), in.readInt());
            coordinates[i] = point;
//            point.draw();
        }
    }

    private void printAndDrawCollinearLineSegments() {
        for (int i = 0; i < coordinates.length; i++) {
            System.out.printf("Currently on %d iteration%n", i);
            Point p = coordinates[i];
            Point[] tmp = Arrays.copyOfRange(coordinates, i, coordinates.length);
            Arrays.sort(tmp);
            Arrays.sort(tmp, p.SLOPE_ORDER);
            int minIndex = -1;
            int maxIndex = -1;
            double slopeTo = Double.NaN;

            for (int j = 0; j < tmp.length; j++) {
                Point q = tmp[j];
//                System.out.printf("Index: %d | Working on %s & %s with slope %f%n", j, p, q, p.slopeTo(q));

                if (p.slopeTo(q) == slopeTo) {
                    if (minIndex == -1) {
                        minIndex = j - 1;
                    }

                    maxIndex = j;
                }

                slopeTo = p.slopeTo(q);
            }

//            System.out.printf("minIndex: %d, maxIndex: %d%n", minIndex, maxIndex);

            if (minIndex > -1) {
                Point[] results = Arrays.copyOfRange(tmp, minIndex, maxIndex + 1);
                System.out.printf("size of result array: %d%n", results.length);
                if (results.length + 1 > 3) {
                    System.out.printf("Result: %s%s%n", p, Arrays.toString(results));
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