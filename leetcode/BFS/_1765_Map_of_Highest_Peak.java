package leetcode.BFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *
 * You are given an integer matrix isWater of size m x n that represents a map of land and water cells.
 *
 * If isWater[i][j] == 0, cell (i, j) is a land cell.
 * If isWater[i][j] == 1, cell (i, j) is a water cell.
 * You must assign each cell a height in a way that follows these rules:
 *
 * The height of each cell must be non-negative.
 * If the cell is a water cell, its height must be 0.
 * Any two adjacent cells must have an absolute height difference of at most 1. A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).
 * Find an assignment of heights such that the maximum height in the matrix is maximized.
 *
 * Return an integer matrix height of size m x n where height[i][j] is cell (i, j)'s height. If there are multiple solutions, return any of them.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: isWater = [[0,1],[0,0]]
 * Output: [[1,0],[2,1]]
 * Explanation: The image shows the assigned heights of each cell.
 * The blue cell is the water cell, and the green cells are the land cells.
 * Example 2:
 *
 *
 *
 * Input: isWater = [[0,0,1],[1,0,0],[0,0,0]]
 * Output: [[1,1,0],[0,1,1],[1,2,2]]
 * Explanation: A height of 2 is the maximum possible height of any assignment.
 * Any height assignment that has a maximum height of 2 while still meeting the rules will also be accepted.
 *
 *
 * Constraints:
 *
 * m == isWater.length
 * n == isWater[i].length
 * 1 <= m, n <= 1000
 * isWater[i][j] is 0 or 1.
 * There is at least one water cell.
 *
 */

public class _1765_Map_of_Highest_Peak {
    public int[][] highestPeak(int[][] isWater) {
        Deque<int[]> q = new LinkedList<>();
        int[][] ans = new int[isWater.length][isWater[0].length];

        for (int r = 0; r < isWater.length; r++) {

            for (int c = 0; c < isWater[0].length; c++) {

                if (isWater[r][c] == 1) {
                    q.offer(new int[] {r, c});
                    ans[r][c] = 0;
                } else {
                    ans[r][c] = -1;
                }
            }
        }

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int r = q.peek()[0];
                int c = q.peek()[1];
                q.poll();

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr < 0 || nc < 0 || nr == ans.length || nc == ans[0].length || ans[nr][nc] != -1) {
                        continue;
                    }
                    ans[nr][nc] = ans[r][c] + 1;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        return ans;
    }
    private int dirs[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
