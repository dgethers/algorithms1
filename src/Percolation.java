/**
 * User: outzider
 * Date: 2/5/14
 * Time: 2:03 PM
 */
public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF isFullWeightedQuickUnionUF;
    private boolean[][] open;
    private int N;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        weightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 2);
        isFullWeightedQuickUnionUF = new WeightedQuickUnionUF((N * N) + 1);
        open = new boolean[N][N];

        if (N > 1) {
            //join top row with source point
            for (int i = 0; i < N; i++) {
                int q = xyTo1D(0, i);
                weightedQuickUnionUF.union(0, q);
                isFullWeightedQuickUnionUF.union(0, q);
            }

            //join bottom row with sink point
            for (int i = 0; i < N; i++) {
                int q = xyTo1D(N - 1, i);
                weightedQuickUnionUF.union(N * N + 1, q);
            }
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkInputParameters(i, j);

        //search left neighbor
        if ((j - 1) > 0 && open[(i - 1)][(j - 1) - 1]) {
            weightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1), (j - 1) - 1));
            isFullWeightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1), (j - 1) - 1));
        }

        //search right neighbor
        if ((j - 1) < N - 1 && open[(i - 1)][(j - 1) + 1]) {
            weightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1), (j - 1) + 1));
            isFullWeightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1), (j - 1) + 1));
        }

        //search up neighbor
        if ((i - 1) > 0 && open[(i - 1) - 1][(j - 1)]) {
            weightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1) - 1, (j - 1)));
            isFullWeightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1) - 1, (j - 1)));
        }

        //search down neighbor
        if ((i - 1) < N - 1 && open[(i - 1) + 1][(j - 1)]) {
            weightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1) + 1, (j - 1)));
            isFullWeightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), xyTo1D((i - 1) + 1, (j - 1)));
        }

        if (N == 1) { //only one element so link source and sink
            weightedQuickUnionUF.union(0, xyTo1D((i - 1), (j - 1)));
            weightedQuickUnionUF.union(xyTo1D((i - 1), (j - 1)), N * N + 1);
        }

        open[(i - 1)][(j - 1)] = true;
    }


    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkInputParameters(i, j);

        return open[i - 1][j - 1];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkInputParameters(i, j);

        return isFullWeightedQuickUnionUF.connected(0, xyTo1D(i - 1, j - 1));
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, (N * N) + 1);
    }

    private int xyTo1D(int row, int column) {
        return ((row * N) + column) + 1;
    }

    private void checkInputParameters(int i, int j) {
        if (i < 0 || i > N) {
            throw new IndexOutOfBoundsException();
        }

        if (j < 0 || j > N) {
            throw new IndexOutOfBoundsException();
        }
    }
}