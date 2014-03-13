import java.util.Arrays;

/**
 * User: outzider
 * Date: 3/11/14
 * Time: 10:49 AM
 */

public class Board {

    private int[][] blocks;
    private int N;

    private int[][] solvedTilePositions;
    private Point[] correctTilePositions;

    private static class Point {
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private int x;
        private int y;

        @Override
        public String toString() {
            return String.format("(x:%d, y:%d)", x, y);
        }
    }

    // construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks.clone();
        N = blocks.length;

        createReferenceGoalBoard();
        correctTilePositions = new Point[N * N];
        int row = 0;
        int column = 0;
        for (int i = 0; i < correctTilePositions.length; i++) {
            if (i == 0) {
                correctTilePositions[i] = new Point(N - 1, N - 1);
            } else {
                correctTilePositions[i] = new Point(row, column++);
                if (i % N == 0) {
                    row++;
                    column = 0;
                }
            }
        }
        System.out.println("correctTilePositions array: " + Arrays.toString(correctTilePositions));
    }

    private void createReferenceGoalBoard() {
        solvedTilePositions = new int[N][N];
        int currentValue = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    solvedTilePositions[i][j] = 0;
                } else {
                    solvedTilePositions[i][j] = currentValue++;
                }
            }
        }
    }


    // board dimension N
    public int dimension() {
        return N;
    }

    // number of blocks out of place
    public int hamming() {
        int correctValue = 1;
        int countOfBlockInPlace = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    if (blocks[N - 1][N - 1] != 0) {
                        countOfBlockInPlace++;
                    }
                } else {
                    if (blocks[i][j] != correctValue++) {
                        countOfBlockInPlace++;
                    }
                }
            }
        }

        return countOfBlockInPlace;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != solvedTilePositions[i][j]) {
                    Point correctPosition = correctTilePositions[blocks[i][j]];
                    System.out.printf("current (%d, %d) with value %d, expected place is: (%d, %d)%n", i, j, blocks[i][j],
                            correctPosition.x, correctPosition.y);
                }
            }
        }


        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return Arrays.deepEquals(solvedTilePositions, blocks);
    }

    // a board obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] tiles = blocks.clone();
        int randomI;
        int randomJ;
        do {
            randomI = StdRandom.uniform(N);
            randomJ = StdRandom.uniform(N);
        } while (tiles[randomI][randomJ] == 0);
//        System.out.printf("randomI:%d, randomJ:%d%n", randomI, randomJ);

        swap(tiles, randomI, randomJ);

        return new Board(tiles);
    }

    private void swap(int[][] tiles, int i, int j) {
        int newJ;
        if (j < N - 1) { //go to right
            newJ = j + 1;
        } else if (j > 0) { //go to left
            newJ = j - 1;
        } else { //TODO: Possible dead code. Remove if so.
            newJ = j;
        }

//        System.out.printf("i:%d, j:%d newJ:%d%n", i, j, newJ);
        int tmp = tiles[i][newJ];
        tiles[i][newJ] = tiles[i][j];
        tiles[i][j] = tmp;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != that.blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }


    // all neighboring boards
    public Iterable<Board> neighbors() {
        //TODO: Implement this

        return null;
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N).append("\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
        Board board = new Board(new int[][]{{1, 0, 3}, {4, 2, 5}, {7, 8, 6}});
        System.out.println(board.manhattan());
    }
}