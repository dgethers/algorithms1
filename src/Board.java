import java.util.Arrays;

/**
 * User: outzider
 * Date: 3/11/14
 * Time: 10:49 AM
 */

public class Board {

    private final int[][] blocks;
    private final int N;

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
    public Board(int[][] tiles) {
        N = tiles.length;
        this.blocks = deepCopyOfArray(tiles);

        createReferenceGoalBoard();
        createCorrectPositionReferenceArray();
    }

    private void createCorrectPositionReferenceArray() {
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
        int countOfBlockInPlace = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] != solvedTilePositions[i][j]) {
//                    System.out.printf("block[%d][%d]=%d vs. solvedTilePositions=%d%n", i, j, blocks[i][j], solvedTilePositions[i][j]);
                    if (blocks[i][j] != 0) {
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
                if (blocks[i][j] != solvedTilePositions[i][j] && blocks[i][j] != 0) {
                    Point currentPosition = new Point(i, j);
                    Point correctPosition = correctTilePositions[blocks[i][j]];

                    if (correctPosition.x == currentPosition.x) { //on the same x-axis, only move on y-axis
                        if (correctPosition.y > currentPosition.y) {
//                            System.out.println(correctPosition.y - currentPosition.y);
                            sum = sum + (correctPosition.y - currentPosition.y);
                        } else {
//                            System.out.println(currentPosition.y - correctPosition.y);
                            sum = sum + (currentPosition.y - correctPosition.y);
                        }
                    } else if (correctPosition.x > currentPosition.x) {
                        if (correctPosition.y > currentPosition.y) {
//                            System.out.println((correctPosition.y - currentPosition.y) + (correctPosition.x - currentPosition.x));
                            sum = sum + (correctPosition.y - currentPosition.y) + (correctPosition.x - currentPosition.x);
                        } else {
//                            System.out.println((currentPosition.y - correctPosition.y) + (correctPosition.x - currentPosition.x));
                            sum = sum + (currentPosition.y - correctPosition.y) + (correctPosition.x - currentPosition.x);
                        }
                    } else {
                        if (correctPosition.y > currentPosition.y) {
//                            System.out.println((correctPosition.y - currentPosition.y) + (currentPosition.x - correctPosition.x));
                            sum = sum + (correctPosition.y - currentPosition.y) + (currentPosition.x - correctPosition.x);
                        } else {
//                            System.out.println((currentPosition.y - correctPosition.y) + (currentPosition.x - correctPosition.x));
                            sum = sum + (currentPosition.y - correctPosition.y) + (currentPosition.x - correctPosition.x);
                        }
                    }
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
        int[][] tiles = deepCopyOfArray(blocks);
        int randomI;
        int randomJ;
        do {
            randomI = StdRandom.uniform(N);
            randomJ = StdRandom.uniform(N);
        } while (continueGeneratingRandomIndex(tiles, randomI, randomJ));
//        System.out.printf("randomI:%d, randomJ:%d%n", randomI, randomJ);

        swap(tiles, randomI, randomJ);

        return new Board(tiles);
    }

    private boolean continueGeneratingRandomIndex(int[][] tiles, int chosenI, int chosenJ) {
        return tiles[chosenI][chosenJ] == 0 || chosenJ < N - 1 && tiles[chosenI][chosenJ + 1] == 0
                || chosenJ > 0 && tiles[chosenI][chosenJ - 1] == 0;
    }

    private void swap(int[][] tiles, int i, int j) {
        int newJ;
        if (j < N - 1) { //go to right
            newJ = j + 1;
        } else { //go to left
            newJ = j - 1;
        }

//        System.out.printf("i:%d, j:%d newJ:%d%n", i, j, newJ);

        swapTile(tiles, i, j, i, newJ);
    }

    private int[][] deepCopyOfArray(int[][] source) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[i][j] = source[i][j];
            }
        }

        return copy;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        if (that.N > this.N || that.N < this.N) return false;

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
        //up, left, down, right
        Queue<Board> neighbors = new Queue<Board>();
        int emptyI = 0, emptyJ = 0;
        outerloop:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    emptyI = i;
                    emptyJ = j;
                    break outerloop;
                }
            }
        }

        if (emptyI > 0) { //up
            int[][] clone = deepCopyOfArray(blocks);
            swapTile(clone, emptyI, emptyJ, emptyI - 1, emptyJ);
            final Board item = new Board(clone);
            neighbors.enqueue(item);
        }

        if (emptyJ > 0) { //left
            int[][] clone = deepCopyOfArray(blocks);
            swapTile(clone, emptyI, emptyJ, emptyI, emptyJ - 1);
            final Board item = new Board(clone);
            neighbors.enqueue(item);
        }

        if (emptyI < N - 1) { //down
            int[][] clone = deepCopyOfArray(blocks);
            swapTile(clone, emptyI, emptyJ, emptyI + 1, emptyJ);
            Board item = new Board(clone);
            neighbors.enqueue(item);
        }

        if (emptyJ < N - 1) { //right
            int[][] clone = deepCopyOfArray(blocks);
            swapTile(clone, emptyI, emptyJ, emptyI, emptyJ + 1);
            Board item = new Board(clone);
            neighbors.enqueue(item);
        }


        return neighbors;
    }

    private void swapTile(int[][] tiles, int originI, int originJ, int destinationI, int destinationJ) {
        int tmp = tiles[destinationI][destinationJ];
        tiles[destinationI][destinationJ] = tiles[originI][originJ];
        tiles[originI][originJ] = tmp;
    }

    // string representation of the board (in the output format specified below)
    public String toString() {
        final int dimension = blocks.length;
        StringBuilder s = new StringBuilder();
        s.append(dimension).append("\n");
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) {
    }
}