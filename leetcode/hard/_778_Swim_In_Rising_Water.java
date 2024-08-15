package leetcode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).
 *
 * The rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.
 *
 * Return the least time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 *
 *
 * Example 2:
 *
 * [0,____1,___2,___3,___4]
 * [24,  23,  22,  21,  V5]
 * [12,__13,__14,__15,__16]
 * [11,V 17,  18,  19,  20]
 * [10,___9,___8,___7,___6]
 *
 * Input: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation: The final route is shown.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 *
 *
 * Constraints:
 *
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 50
 * 0 <= grid[i][j] < n2
 * Each value grid[i][j] is unique.
 *
 */

public class _778_Swim_In_Rising_Water {

    //Binary Search + DFS
    public int swimInWater(int[][] grid) {
        int lo = Integer.MAX_VALUE;
        int hi = Integer.MIN_VALUE;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {
                lo = Math.min(grid[r][c], lo);
                hi = Math.max(grid[r][c], hi);
            }
        }

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;
            boolean[][] v = new boolean[grid.length][grid[0].length];

            if (!dfs(grid, v, 0, 0, pivot)) {
                lo = pivot + 1;
            } else {
                hi = pivot;
            }
        }
        return lo;
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private boolean dfs(int[][] grid, boolean[][] v, int r, int c, int time) {

        if (grid[r][c] > time) {
            return false;
        }

        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return true;
        }

        v[r][c] = true;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length ||
                    v[nr][nc]) {
                continue;
            }

            if (dfs(grid, v, nr, nc, time)) {
                return true;
            }
        }
        return false;
    }
    //=============================================================================================
    //Dijkstra's
    public int swimInWater1(int[][] grid) {
        boolean[][] v = new boolean[grid.length][grid[0].length];
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                return x[2] - y[2];
            }
        });
        pq.offer(new int[] {0, 0, grid[0][0]});
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!pq.isEmpty()) {
            int[] pos = pq.poll();
            int r = pos[0];
            int c = pos[1];
            int elevation = pos[2];
            v[r][c] = true;

            if (r == grid.length - 1 && c == grid[0].length - 1) {
                return elevation;
            }

            for (int[] dir : dirs) {
                int nr = dir[0] + r;
                int nc = dir[1] + c;

                if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length ||
                        v[nr][nc]) {
                    continue;
                }
                int currElevation = grid[nr][nc];
                int nextElevation = Math.max(elevation, currElevation);
                pq.offer(new int[] {nr, nc, nextElevation});
            }
        }
        return -1;
    }
}
