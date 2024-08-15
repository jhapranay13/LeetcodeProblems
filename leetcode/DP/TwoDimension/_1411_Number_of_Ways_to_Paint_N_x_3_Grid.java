package leetcode.DP.TwoDimension;

/**
 *
 *
 * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).
 *
 * Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 1
 * Output: 12
 * Explanation: There are 12 possible way to paint the grid as shown.
 * Example 2:
 *
 * Input: n = 5000
 * Output: 30228214
 *
 *
 * Constraints:
 *
 * n == grid.length
 * 1 <= n <= 5000
 *
 */

public class _1411_Number_of_Ways_to_Paint_N_x_3_Grid {
    public int numOfWays(int n) {
        int[][][][] memo = new int[4][4][4][n];
        return recur(n, 0, 0, 0, 0, memo);
    }
    //Color 1: red, 2: Yellow, 3: Green
    private int[] color = {1, 2, 3};
    private int mod = 1000_000_007;

    private int recur(int n, int c1, int c2, int c3, int count, int[][][][] memo) {

        if (count == n) {
            return 1;
        }

        if (memo[c1][c2][c3][count] > 0) {
            return memo[c1][c2][c3][count];
        }
        int ans = 0;

        for (int nc1 : color) {

            if (nc1 == c1) {
                continue;
            }

            for (int nc2 : color) {

                if (nc1 == nc2 || nc2 == c2) {
                    continue;
                }

                for (int nc3 : color) {

                    if (nc2 == nc3 || nc3 == c3) {
                        continue;
                    }
                    ans += recur(n, nc1, nc2, nc3, count + 1, memo);
                    ans %= mod;
                }
            }
        }
        return memo[c1][c2][c3][count] = ans;
    }
}
