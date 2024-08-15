package leetcode.medium;

/**
 *
 * You are given an m x n matrix board, representing the current state of a crossword puzzle. The crossword contains lowercase English letters (from solved words), ' ' to represent any empty cells, and '#' to represent any blocked cells.
 *
 * A word can be placed horizontally (left to right or right to left) or vertically (top to bottom or bottom to top) in the board if:
 *
 * It does not occupy a cell containing the character '#'.
 * The cell each letter is placed in must either be ' ' (empty) or match the letter already on the board.
 * There must not be any empty cells ' ' or other lowercase letters directly left or right of the word if the word was placed horizontally.
 * There must not be any empty cells ' ' or other lowercase letters directly above or below the word if the word was placed vertically.
 * Given a string word, return true if word can be placed in board, or false otherwise.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", "c", " "]], word = "abc"
 * Output: true
 * Explanation: The word "abc" can be placed as shown above (top to bottom).
 * Example 2:
 *
 *
 * Input: board = [[" ", "#", "a"], [" ", "#", "c"], [" ", "#", "a"]], word = "ac"
 * Output: false
 * Explanation: It is impossible to place the word because there will always be a space/letter above or below it.
 * Example 3:
 *
 *
 * Input: board = [["#", " ", "#"], [" ", " ", "#"], ["#", " ", "c"]], word = "ca"
 * Output: true
 * Explanation: The word "ca" can be placed as shown above (right to left).
 *
 *
 * Constraints:
 *
 * m == board.length
 * n == board[i].length
 * 1 <= m * n <= 2 * 10^5
 * board[i][j] will be ' ', '#', or a lowercase English letter.
 * 1 <= word.length <= max(m, n)
 * word will contain only lowercase English letters.
 *
 */

public class _2018_Check_if_Word_Can_Be_Placed_In_Crossword {
    public boolean placeWordInCrossword(char[][] board, String word) {
        StringBuilder revWord = new StringBuilder();// used to simulate from right to left or down to up

        for (char ch : word.toCharArray()) {
            revWord.insert(0, ch);
        }

        for (int r = 0; r < board.length; r++) {

            for (int c = 0; c < board[0].length; c++) {

                if (board[r][c] != '#') {

                    if (r == 0 || (r != 0 && board[r - 1][c] == '#')) {

                        if (dfs(board, true, r, c, word, 0) ||
                                dfs(board, true, r, c, revWord.toString(), 0)) {
                            return true;
                        }
                    }

                    if (c == 0 || (c != 0 && board[r][c - 1] == '#')) {

                        if (dfs(board, false, r, c, word, 0) ||
                                dfs(board, false, r, c, revWord.toString(), 0)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, boolean dir, int r, int c, String word, int index) {

        if (r == board.length || c == board[0].length) {
            return index == word.length();
        }

        if (index == word.length()) {
            return board[r][c] == '#';
        }

        if (board[r][c] != ' ' && board[r][c] != word.charAt(index)) {
            return false;
        }
        int nr = r;
        int nc = c;

        if (dir) {
            nr += 1;
        } else {
            nc += 1;
        }
        return dfs(board, dir, nr, nc, word, index + 1);
    }
}
