package leetcode.Arrays;

/**
 *
 * iven a Tic-Tac-Toe board as a string array board, return true if and only if it is possible to reach this board position during the course of a valid tic-tac-toe game.
 *
 * The board is a 3 x 3 array that consists of characters ' ', 'X', and 'O'. The ' ' character represents an empty square.
 *
 * Here are the rules of Tic-Tac-Toe:
 *
 * Players take turns placing characters into empty squares ' '.
 * The first player always places 'X' characters, while the second player always places 'O' characters.
 * 'X' and 'O' characters are always placed into empty squares, never filled ones.
 * The game ends when there are three of the same (non-empty) character filling any row, column, or diagonal.
 * The game also ends if all squares are non-empty.
 * No more moves can be played if the game is over.
 *
 *
 * Example 1:
 *
 *
 * Input: board = ["O  ","   ","   "]
 * Output: false
 * Explanation: The first player always plays "X".
 * Example 2:
 *
 *
 * Input: board = ["XOX"," X ","   "]
 * Output: false
 * Explanation: Players take turns making moves.
 * Example 3:
 *
 *
 * Input: board = ["XOX","O O","XOX"]
 * Output: true
 *
 *
 * Constraints:
 *
 * board.length == 3
 * board[i].length == 3
 * board[i][j] is either 'X', 'O', or ' '.
 *
 */

public class _794_ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;

        for (String row : board) {

            for (char ch : row.toCharArray()) {

                if (ch == 'X') {
                    countX++;
                }

                if (ch == 'O') {
                    countO++;
                }
            }
        }
        //O cannot be greater than X and O count < X count - 1
        if (countO > countX || countO < countX - 1) {
            return false;
        }
        boolean isWinnerX = checkWinner(board, 3);// for X the value will be 3
        boolean isWinnerO = checkWinner(board, -3);//for O since the player will be assigned val -1 value will be -3

        if (isWinnerX && countO == countX) {
            return false;
        }

        if (isWinnerO && countO < countX) {
            return false;
        }

        if (isWinnerO && isWinnerX) {
            return false;
        }
        return true;
    }
    //Using an approach similar to 1275
    private boolean checkWinner(String[] board, int playerWinningVal) {
        int[] rows = new int[board.length];
        int[] col = new int[board[0].length()];

        int diagonal = 0;
        int antidiagonal = 0;

        for (int r = 0; r < board.length; r++) {
            String row = board[r];

            for (int c = 0; c < row.length(); c++) {
                char ch = row.charAt(c);

                if (Character.isWhitespace(ch)) {
                    continue;
                }
                int player = -1;

                if (ch == 'X') {
                    player = 1;
                }
                rows[r] += player;
                col[c] += player;

                if (r == c) {
                    diagonal += player;
                }

                if (r + c == board.length - 1) {
                    antidiagonal += player;
                }

                if (rows[r] == playerWinningVal || col[c] == playerWinningVal || diagonal == playerWinningVal || antidiagonal == playerWinningVal) {
                    return true;
                }
            }
        }
        return false;
    }
}
