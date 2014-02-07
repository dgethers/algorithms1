/**
 * User: outzider
 * Date: 2/5/14
 * Time: 2:03 PM
 */
public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int N;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        weightedQuickUnionUF = new WeightedQuickUnionUF(N);
        this.N = N;

    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        checkInputParameters(i, j);

//        if (!weightedQuickUnionUF.connected(i, j)) {
        System.out.println("p = " + weightedQuickUnionUF.find(i));
//            weightedQuickUnionUF.union(i, j);
//        }
    }


    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkInputParameters(i, j);

        return weightedQuickUnionUF.connected(i, j);
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkInputParameters(i, j);

        return false;
    }

    private void checkInputParameters(int i, int j) {
        if (i < 0 || i > N) throw new IndexOutOfBoundsException();
        if (j < 0 || j > N) throw new IndexOutOfBoundsException();
    }

    // does the system percolate?
    public boolean percolates() {

        return false;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        System.out.println("n = " + n);
        System.out.println("-> " + in.readLine());
        Percolation percolation = new Percolation(n);
        while (in.hasNextLine()) {
            String line = in.readLine();
            System.out.println("line = " + line);
            String[] tmp = line.split(" ");
            int i = Integer.parseInt(tmp[0]);
            int j = Integer.parseInt(tmp[1]);
            System.out.println("here");
            percolation.open(i, j);
            System.out.println(String.format("i=%d, j=%d, is connected = %b", i, j, percolation.isOpen(i, j)));

        }
    }
}
