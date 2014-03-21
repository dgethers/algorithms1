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
        private int numberOfMoves = 0;

        private SearchNode(Board initialBoard, SearchNode previousSearchNode, int numberOfMoves, int priority) {
            this.initialBoard = initialBoard;
            this.previousSearchNode = previousSearchNode;
            this.priority = priority;
            this.numberOfMoves = numberOfMoves;
        }

        @Override
        public int compareTo(SearchNode o) {
            return Integer.valueOf(priority).compareTo(o.priority);
        }
    }

    private SearchNode goalBoard;
    private boolean isSolvable = false;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initialBoard) {
        solve(initialBoard);
    }

    private void solve(Board initialBoard) {
        MinPQ<SearchNode> initialMinPQ = new MinPQ<SearchNode>();
        MinPQ<SearchNode> twinMinPQ = new MinPQ<SearchNode>();

        initialMinPQ.insert(new SearchNode(initialBoard, null, 0, initialBoard.manhattan()));
        Board twin = initialBoard.twin();
        twinMinPQ.insert(new SearchNode(twin, null, 0, twin.manhattan()));

        boolean keepProcessing = true;
        while (keepProcessing) {
            SearchNode current = initialMinPQ.delMin();
            SearchNode twinCurrent = twinMinPQ.delMin();

            Board currentBoard = current.initialBoard;
            Board twinBoard = twinCurrent.initialBoard;

            if (currentBoard.isGoal()) {
                goalBoard = current;
                isSolvable = true;
                keepProcessing = false;
            }

            if (twinBoard.isGoal() && initialMinPQ.size() > 0) { //twin is solvable but original still has nodes
                keepProcessing = false;
            }

            for (Board nextBoard : currentBoard.neighbors()) {
                if (!doesBoardMatchPreviousSearchEntries(current, nextBoard)) {
                    SearchNode sn = new SearchNode(nextBoard, current, current.numberOfMoves + 1, nextBoard.manhattan()
                            + (current.numberOfMoves + 1));
                    initialMinPQ.insert(sn);
                }
            }

            for (Board nextBoard : twinBoard.neighbors()) {
                if (!doesBoardMatchPreviousSearchEntries(twinCurrent, twinBoard)) {
                    twinMinPQ.insert(new SearchNode(nextBoard, twinCurrent, twinCurrent.numberOfMoves + 1,
                            twinBoard.manhattan() + (twinCurrent.numberOfMoves + 1)));
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

        return isSolvable;
    }

    // min number of priority to solve initial board; -1 if no solution
    public int moves() {
        if (goalBoard != null) {
            SearchNode current = goalBoard;
            int count = 0;
            while (current != null) {
                count++;
                current = current.previousSearchNode;
            }

            return count - 1;
        }

        return -1;
    }

    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (goalBoard != null) {
            Stack<Board> goalPath = new Stack<Board>();
            SearchNode current = goalBoard;
            while (current != null) {
                goalPath.push(current.initialBoard);
                current = current.previousSearchNode;
            }

            return goalPath;
        }

        return null;
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
