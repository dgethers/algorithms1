/**
 * User: outzider
 * Date: 2/5/14
 * Time: 5:21 PM
 */
public class PercolationStats {

    private double[] percentOfSitesOpen;
    private int T;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {

        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }

        percentOfSitesOpen = new double[T];
        this.T = T;

        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            boolean percolate = false;
            int openSites = 0;

            while (!percolate) {
                int x = StdRandom.uniform(1, N + 1);
                int y = StdRandom.uniform(1, N + 1);

                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                    openSites++;
                }

                percolate = percolation.percolates();
            }

            double value = (double) openSites / (N * N);
            percentOfSitesOpen[i] = value;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(percentOfSitesOpen);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(percentOfSitesOpen);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return StdStats.mean(percentOfSitesOpen) - (1.96) * (StdStats.stddev(percentOfSitesOpen) / Math.sqrt(T));
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return StdStats.mean(percentOfSitesOpen) + (1.96) * (StdStats.stddev(percentOfSitesOpen) / Math.sqrt(T));
    }

    // test client, described below
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.printf("mean:\t = %f%n", percolationStats.mean());
        System.out.printf("stddev:\t = %f%n", percolationStats.stddev());
        System.out.printf("95 confidence interval\t = %f, %f%n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
