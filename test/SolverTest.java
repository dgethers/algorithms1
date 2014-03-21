import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: outzider
 * Date: 3/20/14
 * Time: 8:12 PM
 */
public class SolverTest {

    @Test
    public void puzzle2x2Unsolvable1() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle2x2-unsolvable1.txt"));
        assertEquals(-1, solver.moves());
    }

    @Test
    public void puzzle2x2Unsolvable2() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle2x2-unsolvable2.txt"));
        assertEquals(-1, solver.moves());
    }

    @Test
    public void puzzle2x2Unsolvable3() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle2x2-unsolvable3.txt"));
        assertEquals(-1, solver.moves());
    }

    @Test
    public void puzzle3x3Unsolvable() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle3x3-unsolvable.txt"));
        assertEquals(-1, solver.moves());
    }

    @Test
    public void puzzle3x3Unsolvable1() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle3x3-unsolvable1.txt"));
        assertEquals(-1, solver.moves());
    }

    @Test
    public void puzzle3x3Unsolvable2() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle3x3-unsolvable2.txt"));
        assertEquals(-1, solver.moves());
    }

    @Test
    public void puzzle00() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle00.txt"));
        assertEquals(0, solver.moves());
    }

    @Test
    public void puzzle01() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle01.txt"));
        assertEquals(1, solver.moves());
    }

    @Test
    public void puzzle02() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle02.txt"));
        assertEquals(2, solver.moves());
    }

    @Test
    public void puzzle03() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle03.txt"));
        assertEquals(3, solver.moves());
    }

    @Test
    public void puzzle04() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle04.txt"));
        assertEquals(4, solver.moves());
    }

    @Test
    public void puzzle05() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle05.txt"));
        assertEquals(5, solver.moves());
    }

    @Test
    public void puzzle06() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle06.txt"));
        assertEquals(6, solver.moves());
    }

    @Test
    public void puzzle07() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle07.txt"));
        assertEquals(7, solver.moves());
    }

    @Test
    public void puzzle08() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle08.txt"));
        assertEquals(8, solver.moves());
    }

    @Test
    public void puzzle09() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle09.txt"));
        assertEquals(9, solver.moves());
    }

    @Test
    public void puzzle10() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle10.txt"));
        assertEquals(10, solver.moves());
    }

    @Test
    public void puzzle11() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle11.txt"));
        assertEquals(11, solver.moves());
    }

    @Test
    public void puzzle12() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle12.txt"));
        assertEquals(12, solver.moves());
    }

    @Test
    public void puzzle13() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle13.txt"));
        assertEquals(13, solver.moves());
    }

    @Test
    public void puzzle14() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle14.txt"));
        assertEquals(14, solver.moves());
    }

    @Test
    public void puzzle15() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle15.txt"));
        assertEquals(15, solver.moves());
    }

    @Test
    public void puzzle16() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle16.txt"));
        assertEquals(16, solver.moves());
    }

    @Test
    public void puzzle17() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle17.txt"));
        assertEquals(17, solver.moves());
    }

    @Test
    public void puzzle18() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle18.txt"));
        assertEquals(18, solver.moves());
    }

    @Test
    public void puzzle19() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle19.txt"));
        assertEquals(19, solver.moves());
    }

    @Test
    public void puzzle20() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle20.txt"));
        assertEquals(20, solver.moves());
    }

    @Test
    public void puzzle21() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle21.txt"));
        assertEquals(21, solver.moves());
    }

    @Test
    public void puzzle22() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle22.txt"));
        assertEquals(22, solver.moves());
    }

    @Test
    public void puzzle23() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle23.txt"));
        assertEquals(23, solver.moves());
    }

    @Test
    public void puzzle24() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle24.txt"));
        assertEquals(24, solver.moves());
    }

    @Test
    public void puzzle25() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle25.txt"));
        assertEquals(25, solver.moves());
    }

    @Test
    public void puzzle26() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle26.txt"));
        assertEquals(26, solver.moves());
    }

    @Test
    public void puzzle27() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle27.txt"));
        assertEquals(27, solver.moves());
    }

    @Test
    public void puzzle28() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle28.txt"));
        assertEquals(28, solver.moves());
    }

    @Test
    public void puzzle29() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle29.txt"));
        assertEquals(29, solver.moves());
    }

    @Test
    public void puzzle30() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle30.txt"));
        assertEquals(30, solver.moves());
    }

    @Test
    public void puzzle31() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle31.txt"));
        assertEquals(31, solver.moves());
    }

    @Test
    public void puzzle32() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle32.txt"));
        assertEquals(32, solver.moves());
    }

    @Test
    public void puzzle33() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle33.txt"));
        assertEquals(33, solver.moves());
    }

    @Test
    public void puzzle34() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle34.txt"));
        assertEquals(34, solver.moves());
    }

    @Test
    public void puzzle35() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle35.txt"));
        assertEquals(35, solver.moves());
    }

    @Test
    public void puzzle36() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle36.txt"));
        assertEquals(36, solver.moves());
    }

    @Test
    public void puzzle37() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle37.txt"));
        assertEquals(37, solver.moves());
    }

    @Test
    public void puzzle38() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle38.txt"));
        assertEquals(38, solver.moves());
    }

    @Test
    public void puzzle39() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle39.txt"));
        assertEquals(39, solver.moves());
    }

    @Test
    public void puzzle40() {
        Solver solver = new Solver(getBoardFromInputFile("in/8puzzle/puzzle40.txt"));
        assertEquals(40, solver.moves());
    }


    private static Board getBoardFromInputFile(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        int[][] tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tiles[i][j] = in.readInt();
            }
        }

        // solve the slider puzzle
        Board initial = new Board(tiles);

        return initial;
    }
}
