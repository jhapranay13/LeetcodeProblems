package leetcode.medium;

/**
 *
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 *
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 *
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.
 *
 *
 * Example 1:
 *
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 *
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 *
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 *
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 *
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 *
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 *
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 *
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 *
 *
 * Constraints:
 *
 * 2 <= n <= 100
 * player is 1 or 2.
 * 0 <= row, col < n
 * (row, col) are unique for each different call to move.
 * At most n2 calls will be made to move.
 *
 *
 * Follow-up: Could you do better than O(n2) per move() operation?
 *
 */

public class _348_DesignTicTacToe {

    class TicTacToe {
        private int[][] board;
        private int n;

        public TicTacToe(int n) {
            this.board = new int[n][n];
            this.n = n;
        }

        public int move(int row, int col, int player) {

            if (row >= 0 && row < n && col >= 0 && col < n) {
                board[row][col] = player;
                boolean diag = false;
                boolean revdiag = false;
                boolean rOrC = false;

                if (row == col || col == n - row - 1) {
                    diag = checkDiag(player, row);
                    revdiag = checkRev(player, col);
                }
                rOrC = checkRowOrCol(player, row, col);
                return diag || revdiag || rOrC ? player : 0;
            }
            return 0;
        }

        private boolean checkDiag(int p, int r) {

            for (int i = 0; i < n; i++) {
                if (board[i][i] != p) {
                    return false;
                }

            }
            return true;
        }

        private boolean checkRev(int p, int r) {
            int c = n - 1;

            for (int i = 0; i < n; i++) {
                if (board[i][c--] != p) {
                    return false;
                }
            }
            return true;
        }

        private boolean checkRowOrCol(int p, int row, int col) {
            boolean rf = true;

            for (int c = 0; c < n; c++) {

                if (board[row][c] != p) {
                    rf = false;
                    break;
                }
            }

            if (rf) {
                return rf;
            }
            boolean cf = true;

            for (int r = 0; r < n; r++) {

                if (board[r][col] != p) {
                    cf = false;
                    break;
                }
            }
            return cf;
        }
    }

/**
 * Your TicTacToe object will be instantiated and called as such:
 * TicTacToe obj = new TicTacToe(n);
 * int param_1 = obj.move(row,col,player);
 */
    //=============================================================================================
    //Variation
int[] rows;
    int cols[];
    int diag;
    int antidiag;
    int n;
    //void the constructor to remove error
    public void TicTacToe(int n) {
        this.rows = new int[n];
        this.cols = new int[n];
        this.n = n;
    }

    public int move(int row, int col, int player) {
        int curr = player == 1 ? 1 : -1;

        rows[row] += curr;
        cols[col] += curr;

        if (row == col) {
            diag += curr;
        }

        if (col == (cols.length - row - 1)) {
            antidiag += curr;
        }

        if (Math.abs(rows[row]) == n || Math.abs(cols[col]) == n || Math.abs(diag) == n ||
                Math.abs(antidiag) == n) {
            return player;
        }
        return 0;
    }
}
