package leetcode.DP.TwoDimension;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Given a m * n matrix seats  that represent seats distributions in a classroom.
 * If a seat is broken, it is denoted by '#' character otherwise it is denoted by a '.'
 * character.
 *
 * Students can see the answers of those sitting next to the left, right,
 * upper left and upper right, but he cannot see the answers of the student
 * sitting directly in front or behind him. Return the maximum number of students
 * that can take the exam together without any cheating being possible.
 *
 * Students must be placed in seats in good condition.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: seats = [["#",".","#","#",".","#"],
 *                 [".","#","#","#","#","."],
 *                 ["#",".","#","#",".","#"]]
 * Output: 4
 * Explanation: Teacher can place 4 students in available seats so they don't cheat on the exam.
 * Example 2:
 *
 * Input: seats = [[".","#"],
 *                 ["#","#"],
 *                 ["#","."],
 *                 ["#","#"],
 *                 [".","#"]]
 * Output: 3
 * Explanation: Place all students in available seats.
 *
 * Example 3:
 *
 * Input: seats = [["#",".",".",".","#"],
 *                 [".","#",".","#","."],
 *                 [".",".","#",".","."],
 *                 [".","#",".","#","."],
 *                 ["#",".",".",".","#"]]
 * Output: 10
 * Explanation: Place students in available seats in column 1, 3 and 5.
 *
 *
 * Constraints:
 *
 * seats contains only characters '.' and'#'.
 * m == seats.length
 * n == seats[i].length
 * 1 <= m <= 8
 * 1 <= n <= 8
 *
 */
public class _1349_Maximum_Students_Taking_Exam {
    public int maxStudents(char[][] seats) {
        int[][] memo = new int[seats.length][1 << seats[0].length];
        return recur(seats, 0, 0, memo);
    }

    private int recur(char[][] seats, int mask, int index, int[][] memo) {

        if (index == seats.length) {
            return 0;
        }

        if (memo[index][mask] > 0) {
            return memo[index][mask];
        }
        List<Integer> combinations = new LinkedList<>();
        getColumnCombination(seats, index, 0, mask, combinations, 0);
        int ans = 0;

        for (int combination : combinations) {
            int count = Integer.bitCount(combination);
            ans = Math.max(ans, count + recur(seats, combination, index + 1, memo));
        }
        return memo[index][mask] = ans;
    }

    private void getColumnCombination(char[][] seats, int r, int c, int prevRowMask, List<Integer> combinations, int colMask) {

        if (c == seats[0].length) {
            combinations.add(colMask);
            return;
        }
        // Don't choose this column
        getColumnCombination(seats, r, c + 1, prevRowMask, combinations, colMask);

        if (seats[r][c] == '.') {
            int leftMask = c > 0 ? (1 << c - 1) : 0;
            int rightMask = c < seats[0].length - 1 ? (1 << c + 1) : 0;

            if ((prevRowMask & leftMask) == 0 && (prevRowMask & rightMask) == 0 &&
                    (colMask & leftMask) == 0 && (colMask & rightMask) == 0) {
                int nextMask = colMask | (1 << c);
                getColumnCombination(seats, r, c + 1, prevRowMask, combinations, nextMask);
            }
        }
    }
}
