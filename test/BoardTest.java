import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * User: outzider
 * Date: 3/12/14
 * Time: 9:40 AM
 */
public class BoardTest {

    @Test
    public void equalBoardsWithSameValues() {
        Board board1 = new Board(new int[][]{{1, 2}, {3, 0}});
        Board board2 = new Board(new int[][]{{1, 2}, {3, 0}});
        assertTrue(board1.equals(board2));
    }

    @Test
    public void equalBoardsWithSameReference() {
        Board board = new Board(new int[][]{{1, 2}, {3, 0}});
        assertTrue(board.equals(board));
    }

    @Test
    public void unEqualBoards() {
        Board board1 = new Board(new int[][]{{1, 2}, {3, 0}});
        Board board2 = new Board(new int[][]{{0, 3}, {1, 2}});
        assertFalse(board1.equals(board2));
    }

    @Test
    public void nonNullToString() {
        Board board = new Board(new int[][]{{1, 2}, {3, 0}});
        assertNotNull(board.toString());
    }

    @Test
    public void validGoalBoard() {
        Board board = new Board(new int[][]{{1, 2}, {3, 0}});
        assertTrue(board.isGoal());
    }

    @Test
    public void invalidGoalBoard() {
        Board board = new Board(new int[][]{{1, 0}, {3, 2}});
        assertFalse(board.isGoal());
    }

    @Test
    public void correctBoardDimension() {
        Board board = new Board(new int[][]{{1, 2}, {3, 4}, {5, 0}});
        assertEquals(3, board.dimension());
    }

    @Test
    public void validTwin() {
        Board board = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 8, 6}});
        Board twin = board.twin();
        Board[] validTwins = new Board[5];

        validTwins[0] = new Board(new int[][]{{0, 3, 1}, {4, 2, 5}, {7, 8, 6}});
        validTwins[1] = new Board(new int[][]{{0, 1, 3}, {2, 4, 5}, {7, 8, 6}});
        validTwins[2] = new Board(new int[][]{{0, 1, 3}, {4, 5, 2}, {7, 8, 6}});
        validTwins[3] = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {8, 7, 6}});
        validTwins[4] = new Board(new int[][]{{0, 1, 3}, {4, 2, 5}, {7, 6, 8}});
        int matchCount = 0;
        for (Board validTwin : validTwins) {
            if (twin.equals(validTwin)) {
                matchCount++;
            }
        }
        assertTrue(matchCount == 1);
    }
}
