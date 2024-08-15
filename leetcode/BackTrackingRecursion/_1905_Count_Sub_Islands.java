package leetcode.BackTrackingRecursion;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
 *
 * An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
 *
 * Return the number of islands in grid2 that are considered sub-islands.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * Output: 3
 * Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
 * Example 2:
 *
 *
 * Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * Output: 2
 * Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 *
 *
 * Constraints:
 *
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] and grid2[i][j] are either 0 or 1.
 *
 */

public class _1905_Count_Sub_Islands {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int ans = 0;
        Set<Integer> v = new HashSet<>();

        for (int r = 0; r < grid1.length; r++) {

            for (int c = 0; c < grid1[0].length; c++) {
                int idx = r * grid1[0].length + c;

                if (!v.contains(idx) && grid2[r][c] == 1) {
                    isPart = true;
                    recur(grid1, grid2, v, r, c);

                    if (isPart) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    private int [][]dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private boolean isPart = true;

    private void recur(int[][] grid1, int[][] grid2, Set<Integer> v, int r, int c) {
        int idx = r * grid1[0].length + c;

        if (v.contains(idx)) {
            return;
        }

        if (grid1[r][c] == 0) {
            isPart = false;
        }
        v.add(idx);

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nc < 0 || nr >= grid1.length || nc >= grid1[0].length || grid2[nr][nc] == 0) {
                continue;
            }
            recur(grid1, grid2, v, nr, nc);
        }
    }
}
