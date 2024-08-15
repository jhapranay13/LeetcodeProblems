package leetcode.Sorting;

import java.util.PriorityQueue;

/**
 *
 *You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].
 *
 * You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.
 *
 * Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [[0,1,3,2],[5,1,2,5],[4,3,8,6]]
 * Output: 7
 * Explanation: One of the paths that we can take is the following:
 * - at t = 0, we are on the cell (0,0).
 * - at t = 1, we move to the cell (0,1). It is possible because grid[0][1] <= 1.
 * - at t = 2, we move to the cell (1,1). It is possible because grid[1][1] <= 2.
 * - at t = 3, we move to the cell (1,2). It is possible because grid[1][2] <= 3.
 * - at t = 4, we move to the cell (1,1). It is possible because grid[1][1] <= 4.
 * - at t = 5, we move to the cell (1,2). It is possible because grid[1][2] <= 5.
 * - at t = 6, we move to the cell (1,3). It is possible because grid[1][3] <= 6.
 * - at t = 7, we move to the cell (2,3). It is possible because grid[2][3] <= 7.
 * The final time is 7. It can be shown that it is the minimum time possible.
 * Example 2:
 *
 *
 *
 * Input: grid = [[0,2,4],[3,2,1],[1,0,4]]
 * Output: -1
 * Explanation: There is no path from the top left to the bottom-right cell.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 10^5
 * 0 <= grid[i][j] <= 10^5
 * grid[0][0] == 0
 *
 */

public class _2577_Minimum_Time_to_Visit_a_Cell_In_a_Grid {
    public int minimumTime(int[][] grid) {

        if (grid[0][1] > 1 && grid[1][0] > 1) {
            return -1;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.offer(new int[] {0, 0, 0});
        boolean[][] v = new boolean[grid.length][grid[0].length];

        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int r = data[0];
            int c = data[1];
            int time = data[2];

            if (r == grid.length - 1 && c == grid[0].length - 1) {
                return time;
            }

            if (v[r][c]) {
                continue;
            }
            v[r][c] = true;

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || v[nr][nc]) {
                    continue;
                }

                if (grid[nr][nc] <= time + 1) {
                    pq.offer(new int[]{nr, nc, time + 1});
                } else {
                    int diff = grid[nr][nc] - time;
                    // If i jump to previous and jump back again to wait for the time to move to this cell
                    // So the time taken for that
                    int tempTime = grid[nr][nc];
                    // So if it lands on the current cell then one more move to jump to this cell
                    if (diff % 2 == 0) {
                        tempTime += 1;
                    }
                    pq.offer(new int[]{nr, nc, tempTime});
                }
            }
        }
        return -1;
    }

    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
