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
        int absMinIndex = -1;
        int absMaxIndex = -1;
        Point absMinPoint = null;
        boolean firstMatchFound = true;
        for (int i = 0; i < coordinates.length; i++) {
//            System.out.printf("Currently on %d iteration%n", i);
            Point p = coordinates[i];
            if (absMinPoint == null) {
                absMinPoint = p;
            }
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

                        if (absMinIndex == -1 || absMinIndex > minIndex) {
                            absMinIndex = i;
                            absMinPoint = tmp[absMinIndex];
                        }
                    }

                    maxIndex = j;

                    if (absMaxIndex < maxIndex) {
                        absMaxIndex = maxIndex;
                    }
                }

                slopeTo = p.slopeTo(q);
            }

//            System.out.printf("minIndex: %d, maxIndex: %d | absMinIndex: %d, absMaxIndex: %d%n", minIndex, maxIndex, absMinIndex, absMaxIndex);

            if (minIndex > -1) {
//                Point[] results = Arrays.copyOfRange(tmp, minIndex, maxIndex + 1);
                Point[] results = new Point[maxIndex - minIndex + 2];
                results[0] = tmp[0];
                int index = 1;
                for (int k = minIndex; k <= maxIndex; k++) {
                    results[index++] = tmp[k];
                }
                Arrays.sort(results);
//                System.out.printf("size of result array: %d%n", results.length);
                if (results.length > 3) {
                    boolean isWithinMinMaxRange = minIndex >= absMinIndex && maxIndex < absMaxIndex;
                    boolean isFirstElementTheSameAsAbsoluteFirst = results[0].compareTo(absMinPoint) == 0;
//                    System.out.println("results[0] = " + results[0]);
//                    System.out.println("absMinPoint = " + absMinPoint);
//                    System.out.println("isWithinMinMaxRange = " + isWithinMinMaxRange);
//                    System.out.println("isFirstElementTheSameAsAbsoluteFirst = " + isFirstElementTheSameAsAbsoluteFirst);
//                    System.out.println("firstMatchFound = " + firstMatchFound);
                    if (!isWithinMinMaxRange || (!isFirstElementTheSameAsAbsoluteFirst)) {
                        System.out.printf("Result: %s%n", Arrays.toString(results));
                    }

                }
                firstMatchFound = false;
                absMinPoint = results[0];
            }
        }
    }

    public static void main(String[] args) {
        Fast fast = new Fast();
        fast.setPointsFromInputFile(args[0]);
        fast.printAndDrawCollinearLineSegments();
    }
}