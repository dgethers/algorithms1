import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * User: outzider
 * Date: 2/13/14
 * Time: 4:53 PM
 */
public class PercolationTest {

    @Test
    public void input1() {
        assertTrue(getPercolationResults("in/percolation/input1.txt"));
    }

    @Test
    public void input1No() {
        assertFalse(getPercolationResults("in/percolation/input1-no.txt"));
    }

    @Test
    public void input2() {
        assertTrue(getPercolationResults("in/percolation/input2.txt"));
    }

    @Test
    public void input2No() {
        assertFalse(getPercolationResults("in/percolation/input2-no.txt"));
    }

    @Test
    public void input3() {
        assertTrue(getPercolationResults("in/percolation/input3.txt"));
    }

    @Test
    public void input4() {
        assertTrue(getPercolationResults("in/percolation/input4.txt"));
    }

    @Test
    public void input5() {
        assertTrue(getPercolationResults("in/percolation/input5.txt"));
    }

    @Test
    public void input6() {
        assertTrue(getPercolationResults("in/percolation/input6.txt"));
    }

    @Test
    public void input7() {
        assertTrue(getPercolationResults("in/percolation/input7.txt"));
    }

    @Test
    public void input8() {
        assertTrue(getPercolationResults("in/percolation/input8.txt"));
    }

    @Test
    public void input10() {
        assertTrue(getPercolationResults("in/percolation/input10.txt"));
    }

    @Test
    public void input10No() {
        assertFalse(getPercolationResults("in/percolation/input10-no.txt"));
    }

    @Test
    public void input20() {
        assertTrue(getPercolationResults("in/percolation/input20.txt"));
    }

    @Test
    public void input50() {
        assertTrue(getPercolationResults("in/percolation/input50.txt"));
    }

    @Test
    public void Heart25() {
        assertFalse(getPercolationResults("in/percolation/heart25.txt"));
    }

    @Test
    public void Greeting57() {
        assertFalse(getPercolationResults("in/percolation/greeting57.txt"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullIndexOutOfBoundsExceptionWithLargerIndex() {
        Percolation percolation = new Percolation(4);
        percolation.isFull(5, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void openIndexOutOfBoundsExceptionWithLargerIndex() {
        Percolation percolation = new Percolation(4);
        percolation.open(5, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenIndexOutOfBoundsExceptionWithLargerIndex() {
        Percolation percolation = new Percolation(4);
        percolation.isOpen(5, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpenIndexOutOfBoundsExceptionWithNegativeIndex() {
        Percolation percolation = new Percolation(4);
        percolation.isOpen(-1, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void openIndexOutOfBoundsExceptionWithNegativeIndex() {
        Percolation percolation = new Percolation(4);
        percolation.open(3, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void isFullIndexOutOfBoundsExceptionWithNegativeIndex() {
        Percolation percolation = new Percolation(4);
        percolation.isFull(-1, -1);
    }

    private boolean getPercolationResults(String inputFile) {
        In in = new In(inputFile);
        int n = Integer.parseInt(in.readLine());
        Percolation percolation = new Percolation(n);
        while (in.hasNextLine()) {
            String line = in.readLine();
            if (!line.trim().equals("")) {
                String[] coordinates = line.trim().split("\\s+");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                percolation.open(x, y);
            }
        }

        return percolation.percolates();
    }
}
