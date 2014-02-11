import java.util.Arrays;

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
        System.out.println("Original N: " + N);
        this.N = N + 2;
        System.out.println("Modified N: " + this.N);
        weightedQuickUnionUF = new WeightedQuickUnionUF(this.N);

    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkInputParameters(i, j);

        grid[i][j] = true;

        if (!weightedQuickUnionUF.connected(i, j)) {
            weightedQuickUnionUF.union(i, j);
        }
    }


    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkInputParameters(i, j);

        return grid[i][j] == false;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkInputParameters(i, j);

        return grid[i][j];
    }

    private void checkInputParameters(int i, int j) {
        if (i < 0 || i > N) throw new IndexOutOfBoundsException();
        if (j < 0 || j > N) throw new IndexOutOfBoundsException();
    }

    // does the system percolate?
    public boolean percolates() {
//        weightedQuickUnionUF.union(0, 1);
//        weightedQuickUnionUF.union(N - 1, N - 2);
        return weightedQuickUnionUF.connected(0, N - 1);
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
