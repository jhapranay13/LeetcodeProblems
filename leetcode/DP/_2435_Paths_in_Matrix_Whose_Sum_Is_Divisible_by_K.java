package leetcode.DP;

/**
 *
 * You are given a 0-indexed m x n integer matrix grid and an integer k. You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.
 *
 * Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be very large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
 * Output: 2
 * Explanation: There are two paths where the sum of the elements on the path is divisible by k.
 * The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
 * The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.
 * Example 2:
 *
 *
 * Input: grid = [[0,0]], k = 5
 * Output: 1
 * Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.
 * Example 3:
 *
 *
 * Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
 * Output: 10
 * Explanation: Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 5 * 104
 * 1 <= m * n <= 5 * 104
 * 0 <= grid[i][j] <= 100
 * 1 <= k <= 50
 *
 */

public class _2435_Paths_in_Matrix_Whose_Sum_Is_Divisible_by_K {
    public int numberOfPaths(int[][] grid, int k) {
        Long memo[][][] = new Long[grid.length][grid[0].length][k];
        return (int)recur(grid, k, 0, 0, 0, memo);
    }
    private int[][] dirs = {{0, 1}, {1, 0}};
    private int mod = 1000000007;

    private long recur(int[][] grid, int k, int r, int c, int rem, Long[][][] memo) {

        if (r == grid.length || c == grid[0].length) {
            return 0;
        }

        if (memo[r][c][rem] != null) {
            return memo[r][c][rem];
        }
        int val = (grid[r][c] + rem) % mod;
        val %= k;

        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return val == 0 ? 1 : 0;
        }
        long ans = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            ans += recur(grid, k, nr, nc, val, memo);
            ans %= mod;
        }
        return memo[r][c][rem] = ans;
    }
}
