package leetcode.DP.TwoDimension;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:
 *
 * A chess knight can move as indicated in the chess diagram below:
 *
 *
 * We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
 *
 *
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 *
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.
 *
 * As the answer may be very large, return the answer modulo 109 + 7.
 *
 *                        _______   _________
 *                               |  |
 *                     |         |  |       |
 *                      -------Knoght--------
 *                     |         |  |       |
 *                               |  |
 *
 *
 *
 *                         1   2   3
 *                         4   5   6
 *                         7   8   9
 *                         *   0   #
 *
 * All knight chess moves
 *
 * Example 1:
 *
 * Input: n = 1
 * Output: 10
 * Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
 * Example 2:
 *
 * Input: n = 2
 * Output: 20
 * Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
 * Example 3:
 *
 * Input: n = 3131
 * Output: 136006598
 * Explanation: Please take care of the mod.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 5000
 *
 */

public class _935_Knight_Dialer {
    Set<Integer> idxNotReachable = new HashSet<>();
    int[][] dirs = {{1, 2}, {1, -2}, {2, 1}, {2, -1}, {-1, 2}, {-1, -2}, {-2, -1}, {-2, 1}};
    int mod = 1000000007;

    public int knightDialer(int n) {
        idxNotReachable.add(9);
        idxNotReachable.add(11);
        int ans = 0;
        int[][][] memo = new int[4][3][n + 1];

        for (int r = 0; r < 4; r++) {

            for (int c = 0; c < 3; c++) {
                ans = (ans + recur(r, c, n, memo)) % mod;
            }
        }
        return ans;
    }

    private int recur(int r, int c, int n, int[][][] memo) {
        int idx = c + r * 3;

        if (r < 0 || c < 0 || r >= 4 || c >= 3 || idxNotReachable.contains(idx)) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        if (memo[r][c][n] > 0) {
            return memo[r][c][n];
        }
        int ans = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            ans = (ans + recur(nr, nc, n - 1, memo)) % mod;
        }
        return memo[r][c][n] = ans;
    }
}
