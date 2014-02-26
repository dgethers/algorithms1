/**
 * User: outzider
 * Date: 2/21/14
 * Time: 11:07 PM
 */
public class Brute {

    private Point[] coordinates;

    public Brute(String inputFile) {
        In in = new In(inputFile);
        int N = Integer.parseInt(in.readLine());
//        System.out.println("N = " + N);
        coordinates = new Point[N];
        for (int i = 0; i < coordinates.length; i++) {
            String line = in.readLine();
            String[] points = line.trim().split("\\s+");
            Point point = new Point(Integer.parseInt(points[0]), Integer.parseInt(points[1]));
//            System.out.println(point);
            coordinates[i] = point;
        }
    }

    public void checkIfLineIsOnSegment() {
        //draw points
        for (int i = 0; i < coordinates.length; i++) {
            Point coordinate = coordinates[i];
            coordinate.draw();
        }
        coordinates[0].drawTo(coordinates[coordinates.length - 1]);
    }

    public static void main(String[] args) {
        Brute brute = new Brute(args[0]);
        brute.checkIfLineIsOnSegment();

    }
}
