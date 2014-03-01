import java.util.Arrays;

/**
 * User: outzider
 * Date: 2/21/14
 * Time: 11:07 PM
 */
public class Brute {

    private Point[] coordinates;

    public Brute(String inputFile) {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        In in = new In(inputFile);
        int N = in.readInt();
        coordinates = new Point[N];
        for (int i = 0; i < coordinates.length; i++) {
            Point point = new Point(in.readInt(), in.readInt());
            coordinates[i] = point;
            point.draw();
        }
    }

    public void printAndDrawCollinearLineSegments() {
        for (int i = 0; i < coordinates.length; i++) {
            for (int j = i + 1; j < coordinates.length; j++) {
                for (int k = j + 1; k < coordinates.length; k++) {
                    for (int l = k + 1; l < coordinates.length; l++) {
                        if (coordinates[i].slopeTo(coordinates[j]) == coordinates[i].slopeTo(coordinates[k]) &&
                                coordinates[i].slopeTo(coordinates[k]) == coordinates[i].slopeTo(coordinates[l])) {
                            Point[] tmp = {coordinates[i], coordinates[j], coordinates[k], coordinates[l]};
                            Arrays.sort(tmp);
                            System.out.printf("%s->%s->%s->%s%n", tmp[0], tmp[1], tmp[2], tmp[3]);
                            tmp[0].drawTo(tmp[3]);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Brute brute = new Brute(args[0]);
        brute.printAndDrawCollinearLineSegments();

    }
}
