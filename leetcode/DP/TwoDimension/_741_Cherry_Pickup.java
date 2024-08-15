package leetcode.DP.TwoDimension;

/**
 *
 * You are given an n x n grid representing a field of cherries, each cell is one of three possible integers.
 *
 * 0 means the cell is empty, so you can pass through,
 * 1 means the cell contains a cherry that you can pick up and pass through, or
 * -1 means the cell contains a thorn that blocks your way.
 * Return the maximum number of cherries you can collect by following the rules below:
 *
 * Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving right or down through valid path cells (cells with value 0 or 1).
 * After reaching (n - 1, n - 1), returning to (0, 0) by moving left or up through valid path cells.
 * When passing through a path cell containing a cherry, you pick it up, and the cell becomes an empty cell 0.
 * If there is no valid path between (0, 0) and (n - 1, n - 1), then no cherries can be collected.
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,1,-1],[1,0,-1],[1,1,1]]
 * Output: 5
 * Explanation: The player started at (0, 0) and went down, down, right right to reach (2, 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more cherry.
 * The total number of cherries picked up is 5, and this is the maximum possible.
 * Example 2:
 *
 * Input: grid = [[1,1,-1],[1,-1,1],[-1,1,1]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * grid[i][j] is -1, 0, or 1.
 * grid[0][0] != -1
 * grid[n - 1][n - 1] != -1
 *
 */


public class _741_Cherry_Pickup {
    public int cherryPickup(int[][] grid) {
        Integer[][][][] memo = new Integer[grid.length][grid[0].length][grid.length][grid[0].length];
        int ans = recur(grid, 0, 0, 0, 0, memo);
        return ans <= 0 ? 0 : ans;
    }
    /*
    As we can see p1 and p2 both makes 1 move simultaneously.
    We can see that x1+y1 = x2 + y2 always holds true.
    So we can get the 4th variable reduces i.e. y2
    y2 = x1+y1-x2
    int dd=solve2(x1+1,y1,x2+1,g,cpsf+cherries,m,n); //both moves down
    int dr=solve2(x1+1,y1,x2,g,cpsf+cherries,m,n);   //p1 moves down and p2 moves right
    int rr=solve2(x1,y1+1,x2,g,cpsf+cherries,m,n);  //both moves right
    int rd=solve2(x1,y1+1,x2+1,g,cpsf+cherries,m,n); //
     */

    private int recur(int[][] grid, int r1, int c1, int r2, int c2, Integer[][][][] memo) {

        if (r1 > grid.length - 1 || r2 > grid.length - 1 || c1 > grid[0].length - 1 ||
                c2 > grid[0].length - 1 || grid[r1][c1] == -1 || grid[r2][c2] == -1) {
            return Integer.MIN_VALUE;
        }

        if (r1 == grid.length - 1 && c1 == grid[0].length - 1) {
            return grid[r1][c1];
        }

        if (memo[r1][c1][r2][c2] != null) {
            return memo[r1][c1][r2][c2];
        }
        int ans = 0;

        if (r1 == r2 && c1 == c2) {
            ans += grid[r1][c1];
        } else {
            ans += grid[r1][c1] + grid[r2][c2];
        }
        int dd = recur(grid, r1 + 1, c1, r2 + 1, c2, memo);
        int dl = recur(grid, r1 + 1, c1, r2, c2 + 1, memo);
        int ll = recur(grid, r1, c1 + 1, r2, c2 + 1, memo);
        int ld = recur(grid, r1, c1 + 1, r2 + 1, c2, memo);
        int res = Math.max(Math.max(dd, dl), Math.max(ll, ld));

        if (ans != Integer.MIN_VALUE) {
            ans += res;
        }
        return memo[r1][c1][r2][c2] = ans;
    }
}
