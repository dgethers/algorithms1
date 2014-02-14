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
    public void testInput1() {
        assertTrue(getPercolationResults("in/percolation/input1.txt"));
    }

    @Test
    public void testInput1No() {
        assertFalse(getPercolationResults("in/percolation/input1-no.txt"));
    }

    @Test
    public void testInput2() {
        assertTrue(getPercolationResults("in/percolation/input2.txt"));
    }

    @Test
    public void testInput2No() {
        assertFalse(getPercolationResults("in/percolation/input2-no.txt"));
    }

    @Test
    public void testInput3() {
        assertTrue(getPercolationResults("in/percolation/input3.txt"));
    }

    @Test
    public void testInput4() {
        assertTrue(getPercolationResults("in/percolation/input4.txt"));
    }

    @Test
    public void testInput5() {
        assertTrue(getPercolationResults("in/percolation/input5.txt"));
    }

    @Test
    public void testInput6() {
        assertTrue(getPercolationResults("in/percolation/input6.txt"));
    }

    @Test
    public void testInput7() {
        assertTrue(getPercolationResults("in/percolation/input7.txt"));
    }

    @Test
    public void testInput8() {
        assertTrue(getPercolationResults("in/percolation/input8.txt"));
    }

    @Test
    public void testInput10() {
        assertTrue(getPercolationResults("in/percolation/input10.txt"));
    }

    @Test
    public void testInput10No() {
        assertFalse(getPercolationResults("in/percolation/input10-no.txt"));
    }

    @Test
    public void testInput20() {
        assertTrue(getPercolationResults("in/percolation/input20.txt"));
    }

    @Test
    public void testInput50() {
        assertTrue(getPercolationResults("in/percolation/input50.txt"));
    }

    @Test
    public void testHeart25() {
        assertFalse(getPercolationResults("in/percolation/heart25.txt"));
    }

    @Test
    public void testGreeting57() {
        assertFalse(getPercolationResults("in/percolation/greeting57.txt"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsFullIndexOutOfBoundsExceptionWithLargerIndex() {
        Percolation percolation = new Percolation(4);
        percolation.isFull(5, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOpenIndexOutOfBoundsExceptionWithLargerIndex() {
        Percolation percolation = new Percolation(4);
        percolation.open(5, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsOpenIndexOutOfBoundsExceptionWithLargerIndex() {
        Percolation percolation = new Percolation(4);
        percolation.isOpen(5, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsOpenIndexOutOfBoundsExceptionWithNegativeIndex() {
        Percolation percolation = new Percolation(4);
        percolation.isOpen(-1, 3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOpenIndexOutOfBoundsExceptionWithNegativeIndex() {
        Percolation percolation = new Percolation(4);
        percolation.open(3, -1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIsFullIndexOutOfBoundsExceptionWithNegativeIndex() {
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
