package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 *
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
 *
 * You may change 0's to 1's to connect the two islands to form one island.
 *
 * Return the smallest number of 0's you must flip to connect the two islands.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 *
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 *
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] is either 0 or 1.
 * There are exactly two islands in grid.
 *
 */

public class _934_Shortest_Bridge {

    public int shortestBridge(int[][] grid) {
        Deque<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[grid.length][grid[0].length];
        //Mark all the poition of one island and add it in queue
        outer:
        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1 && !v[r][c]) {
                    recur(grid, v, q, r, c);
                    break outer;
                }
            }
        }
        int ans = 0;
        //Form 1st Island fing the shortest path to 2nd Island
        while (!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                int[] pos = q.poll();

                for (int[] dir : dirs) {
                    int nr = pos[0] + dir[0];
                    int nc = pos[1] + dir[1];

                    if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length ||
                            v[nr][nc]) {
                        continue;
                    }

                    if (grid[nr][nc] == 1) {
                        return ans;
                    }
                    v[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
            ans++;
        }
        return ans;
    }
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void recur(int[][] grid, boolean[][] v, Deque<int[]> q, int r, int c) {

        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || v[r][c] ||
                grid[r][c] == 0) {
            return;
        }
        v[r][c] = true;
        q.offer(new int[] {r, c});

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            recur(grid, v, q, nr, nc);
        }
    }
}
