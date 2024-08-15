package leetcode.Graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:
 *
 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
 * Notice that there could be some signs on the cells of the grid that point outside the grid.
 *
 * You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.
 *
 * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
 *
 * Return the minimum cost to make the grid have at least one valid path.
 *
 *
 *
 * Example 1:
 *                  -> -> -> ->
 *                  <- <- <- <-
 *                  -> -> -> ->
 *                  <- <- <- <-
 *
 * Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
 * Output: 3
 * Explanation: You will start at point (0, 0).
 * The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
 * The total cost = 3.
 * Example 2:
 *
 *                     -> -> -V
 *  *                  V- <- <-
 *  *                  -> -> -^
 *
 * Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
 * Output: 0
 * Explanation: You can follow the path from (0, 0) to (2, 2).
 * Example 3:
 *                        -> <-
 *                        ^- -V
 *
 * Input: grid = [[1,2],[4,3]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 100
 * 1 <= grid[i][j] <= 4
 *
 */

public class _1368_MinimumCostToMakeAtLeastOneValidPathInAGrid {
    public int minCost(int[][] grid) {
        int ans = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            public int compare(int[] x, int[] y) {
                return x[2] - y[2];
            }
        });
        pq.offer(new int[] {0, 0, 0});
        boolean[][] v = new boolean[grid.length][grid[0].length];

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            v[node[0]][node[1]] = true;

            if (node[0] == grid.length - 1 && node[1] == grid[0].length - 1) {
                ans = node[2];
                break;
            }

            for (int i = 0; i < dirs.length; i++) {
                int dir[] = dirs[i];
                int nr = node[0] + dir[0];
                int nc = node[1] + dir[1];

                if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length ||
                        v[nr][nc]) {
                    continue;
                }
                /**
                 * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
                 * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
                 * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
                 * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
                 *
                 * So if 0th index is selected since direction is in order 0 means left direction
                 * So if grid[node[0]][node[1]] has the value which is equal to selected index of
                 * direction + 1 then it is reachable without cost
                 */
                if (grid[node[0]][node[1]] == i + 1) { //This cell is reachable by moving in the same direction
                    pq.offer(new int[] {nr, nc, node[2]});
                } else {
                    pq.offer(new int[] {nr, nc, node[2] + 1});
                }
            }
        }
        return ans;
    }
    int dirs[][] = {{0, 1}, {0, -1}, {1, 0},{-1, 0}};//Arrange this in the order of direction
}
