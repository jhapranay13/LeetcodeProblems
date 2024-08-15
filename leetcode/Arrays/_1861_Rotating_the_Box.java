package leetcode.Arrays;

/**
 *
 *
 * You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
 *
 * A stone '#'
 * A stationary obstacle '*'
 * Empty '.'
 * The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. Gravity does not affect the obstacles' positions, and the inertia from the box's rotation does not affect the stones' horizontal positions.
 *
 * It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.
 *
 * Return an n x m matrix representing the box after the rotation described above.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: box = [["#",".","#"]]
 * Output: [["."],
 *          ["#"],
 *          ["#"]]
 * Example 2:
 *
 *
 *
 * Input: box = [["#",".","*","."],
 *               ["#","#","*","."]]
 * Output: [["#","."],
 *          ["#","#"],
 *          ["*","*"],
 *          [".","."]]
 * Example 3:
 *
 *
 *
 * Input: box = [["#","#","*",".","*","."],
 *               ["#","#","#","*",".","."],
 *               ["#","#","#",".","#","."]]
 * Output: [[".","#","#"],
 *          [".","#","#"],
 *          ["#","#","*"],
 *          ["#","*","."],
 *          ["#",".","*"],
 *          ["#",".","."]]
 *
 *
 * Constraints:
 *
 * m == box.length
 * n == box[i].length
 * 1 <= m, n <= 500
 * box[i][j] is either '#', '*', or '.'.
 *
 *
 */

public class _1861_Rotating_the_Box {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;

        for (int r = 0; r < rows; r++) {
            int emptyCell = cols - 1;

            for (int c = cols - 1; c >= 0; c--) {

                if (box[r][c] == '#') {
                    box[r][c] = '.';
                    box[r][emptyCell] = '#';
                    emptyCell--;
                }

                if (box[r][c] == '*') {
                    emptyCell = c - 1;
                }
            }
        }
        char[][] ans = new char[cols][rows];

        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {
                ans[c][r] = box[rows - r - 1][c];
            }
        }
        return ans;
    }
}
