/**
 * User: outzider
 * Date: 3/11/14
 * Time: 11:30 AM
 */
public class Solver {

    private static class SearchNode implements Comparable<SearchNode> {
        private Board initialBoard;
        private SearchNode previousSearchNode;
        private int priority;

        private SearchNode(Board initialBoard, SearchNode previousSearchNode, int priority) {
            this.initialBoard = initialBoard;
            this.previousSearchNode = previousSearchNode;
            this.priority = priority;
        }

        @Override
        public int compareTo(SearchNode o) {
            return Integer.valueOf(priority).compareTo(o.priority);
        }
    }

    private MinPQ<SearchNode> minPQ;
    private Board initial;
    private int totalMoves;
    private Stack<Board> solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        minPQ = new MinPQ<SearchNode>();
        solution = new Stack<Board>();

        minPQ.insert(new SearchNode(initial, null, initial.manhattan()));
        Board prevBoard = null;
        while (!minPQ.isEmpty()) {
            SearchNode current = minPQ.delMin();
            Board board = current.initialBoard;
            solution.push(board);
            SearchNode prevSearchNode = current.previousSearchNode;
            if (prevSearchNode != null) {
                prevBoard = prevSearchNode.initialBoard;
            }

            if (current.initialBoard.isGoal()) {
                System.out.println("Found solution");
                break;
            }
            totalMoves++;

            for (Board nextBoard : board.neighbors()) {
                if (!doesBoardMatchPreviousSearchEntries(current, nextBoard)) {
                    minPQ.insert(new SearchNode(nextBoard, current, nextBoard.manhattan() + totalMoves));
                }
            }
        }
    }

    private boolean doesBoardMatchPreviousSearchEntries(SearchNode searchNode, Board board) {
        SearchNode previous = searchNode.previousSearchNode;
        while (previous != null) {
            if (previous.initialBoard.equals(board)) {
                return true;
            }

            previous = previous.previousSearchNode;
        }

        return false;
    }

    // is the initial board solvable?
    public boolean isSolvable() {

        return totalMoves > -1;
    }

    // min number of priority to solve initial board; -1 if no solution
    public int moves() {

        return totalMoves;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {

        return solution;
    }

    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of priority = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
