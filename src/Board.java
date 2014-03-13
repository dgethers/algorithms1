/**
 * User: outzider
 * Date: 3/11/14
 * Time: 10:49 AM
 */

public class Board {

    private int[][] blocks;
    private int N;

    // construct a board from an N-by-N array of blocks (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        this.blocks = blocks.clone();
        N = blocks.length;
        //TODO: Check to ensure board is a valid grid of same number of columns and rows
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
        //TODO: Implement this

        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {
        int blockValue = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == N - 1 && j == N - 1) {
                    if (blocks[N - 1][N - 1] != 0) {
                        return false;
                    }
                } else {
                    if (blocks[i][j] != blockValue++) {
                        return false;
                    }
                }
            }
        }

        return true;
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
        System.out.printf("randomI:%d, randomJ:%d%n", randomI, randomJ);

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

        System.out.printf("i:%d, j:%d newJ:%d%n", i, j, newJ);
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
}