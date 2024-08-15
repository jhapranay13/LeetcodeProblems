package leetcode.medium;

/**
 *
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.
 *
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
 * Output: 2
 * Example 2:
 *
 * Input: board = [["."]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 200
 * board[i][j] is either '.' or 'X'.
 *
 */

public class _419_Battleship_On_Board {
    public int countBattleships(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] v = new boolean[rows][cols];
        int ans = 0;

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                if (!v[r][c] && board[r][c] == 'X') {
                    recur(board, v, r, c);
                    ans++;
                }
            }
        }
        return ans;
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void recur(char[][] board, boolean[][] v, int r, int c) {

        if (r == board.length && c == board[0].length) {
            return;
        }
        v[r][c] = true;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nc < 0 || nr == board.length || nc == board[0].length ||
                    board[nr][nc] == '.' || v[nr][nc]) {
                continue;
            }
            recur(board, v, nr, nc);
        }
    }
}
