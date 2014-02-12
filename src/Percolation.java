/**
 * User: outzider
 * Date: 2/5/14
 * Time: 2:03 PM
 */
public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int N;
    private boolean[][] grid;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        weightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 2);
        grid = new boolean[N][N];
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkInputParameters(i, j);

        int x = i - 1;
        int y = j - 1;

        //search left neighbor
        if (x > 0 && isOpen(x - 1, y)) {
            weightedQuickUnionUF.union(xyTo1D(x, y), xyTo1D(x - 1, y));
        }

        //search right neighbor
        if (x < N - 1&& isOpen(x + 1, y))  {
            weightedQuickUnionUF.union(xyTo1D(x, y), xyTo1D(x + 1, y));
        }

        grid[x][y] = true;
    }


    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkInputParameters(i, j);

        return grid[i][j];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkInputParameters(i, j);

        //TODO: Implement this
        return true;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, N + 2);
    }

    private int xyTo1D(int x, int y) {
        return (y * N) + x;
    }

    private void checkInputParameters(int i, int j) {
        if (i < 0 || i > N) throw new IndexOutOfBoundsException();
        if (j < 0 || j > N) throw new IndexOutOfBoundsException();
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = Integer.parseInt(in.readLine());
        Percolation percolation = new Percolation(n);
        boolean isPercolated = false;
        while (in.hasNextLine()) {
            String line = in.readLine().trim();
//            System.out.println(String.format("%s", line));
            String[] unionPoints = line.split("\\s+");
//            System.out.println(Arrays.toString(unionPoints));
            int p = Integer.parseInt(unionPoints[0]);
            int q = Integer.parseInt(unionPoints[1]);
            percolation.open(p, q);
            if (percolation.percolates()) {
                isPercolated = true;
                break;
            }
        }
        System.out.println("system percolates: " + isPercolated);
    }
}