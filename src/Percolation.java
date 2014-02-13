/**
 * User: outzider
 * Date: 2/5/14
 * Time: 2:03 PM
 */
public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private boolean[][] open;
    private boolean[][] full;
    private int N;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        weightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 2);
        open = new boolean[N][N];
        full = new boolean[N][N];

        for (int i = 0; i < N; i++) { //join top row with source point
            int q = xyTo1D(0, i);
            weightedQuickUnionUF.union(0, q);
        }

        for (int i = 0; i < N; i++) {
            int q = xyTo1D(N - 1, i);
            System.out.println("sink: " + (N * N + 1));
            weightedQuickUnionUF.union(N * N + 1, q);
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkInputParameters(i, j);

        int x = i - 1;
        int y = j - 1;

//        System.out.printf("Opening (%d, %d)\n", x, y);

        //search left neighbor
        if (y > 0 && open[x][y - 1]) {
            System.out.printf("Joining left neighbor of (%d,%d) to (%d,%d)\n", x, y, x, y - 1);
            weightedQuickUnionUF.union(xyTo1D(x, y), xyTo1D(x, y - 1));
        }

        //search right neighbor
        if (y < N - 1 && open[x][y + 1]) {
            System.out.printf("Joining right neighbor of (%d,%d) to (%d,%d)\n", x, y, x, y + 1);
            weightedQuickUnionUF.union(xyTo1D(x, y), xyTo1D(x, y + 1));
        }

        //search up neighbor
        if (x > 0 && open[x - 1][y]) {
            System.out.printf("Joining up neighbor of (%d,%d) to (%d,%d)\n", x, y, x - 1, y);
            weightedQuickUnionUF.union(xyTo1D(x, y), xyTo1D(x - 1, y));
        }

        //search down neighbor
        if (x < N - 1 && open[x + 1][y]) {
            System.out.printf("Joining down neighbor of (%d,%d) to (%d,%d)\n", x, y, x + 1, y);
            weightedQuickUnionUF.union(xyTo1D(x, y), xyTo1D(x + 1, y));
        }

        open[x][y] = true;
    }


    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkInputParameters(i, j);

        int x = i - 1;
        int y = j - 1;
        return open[x][y];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkInputParameters(i, j);

        return weightedQuickUnionUF.connected(0, xyTo1D(i - 1, j - 1));
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, (N * N) + 1);
    }

    private int xyTo1D(int row, int column) {
        return ((row * N) + column) + 1; //adding 1 to offset "source" index
    }

    private void checkInputParameters(int i, int j) {
        if (i < 0 || i > N) throw new IndexOutOfBoundsException();
        if (j < 0 || j > N) throw new IndexOutOfBoundsException();
    }

    //TODO: Remove once debugging is complete
    public void printGrid() {
        System.out.println("Printing 2d array");
        int count = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%b\t", open[i][j]);
            }
            System.out.println();
        }
    }

    //TODO: Remove once debugging is complete
    public void print1dArrayDetail() {
        System.out.println("Top row connections to source point");
        for (int i = 0; i < N; i++) {
            int q = xyTo1D(0, i);
            System.out.println(String.format(" %d is connected to source %b ", q, weightedQuickUnionUF.connected(0, q)));
        }
        System.out.println("Bottom row connections to sink point");
        for (int i = 0; i < N; i++) {
            int q = xyTo1D(N - 1, i);
            System.out.println(String.format(" %d is connected to sink %b ", q, weightedQuickUnionUF.connected(N + 2, q)));
        }
        System.out.println("Count of components in UF " + weightedQuickUnionUF.count());
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = Integer.parseInt(in.readLine());
        Percolation percolation = new Percolation(n);
//        boolean isPercolated = false;
        while (in.hasNextLine()) {
            String line = in.readLine().trim();
//            System.out.println(String.format("%s", line));
            String[] unionPoints = line.split("\\s+");
//            System.out.println(Arrays.toString(unionPoints));
            int p = Integer.parseInt(unionPoints[0]);
            int q = Integer.parseInt(unionPoints[1]);
            percolation.open(p, q);
        }
        percolation.printGrid();
        percolation.print1dArrayDetail();
        System.out.println("system percolates: " + percolation.percolates());
    }
}