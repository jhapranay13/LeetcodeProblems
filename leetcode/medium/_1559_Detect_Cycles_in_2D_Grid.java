package leetcode.medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.
 *
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.
 *
 * Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 *
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
 * Output: true
 * Explanation: There are two valid cycles shown in different colors in the image below:
 *
 * Example 2:
 *
 *
 *
 * Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
 * Output: true
 * Explanation: There is only one valid cycle highlighted in the image below:
 *
 * Example 3:
 *
 *
 *
 * Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
 * Output: false
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid consists only of lowercase English letters.
 *
 */

public class _1559_Detect_Cycles_in_2D_Grid {
    public boolean containsCycle(char[][] grid) {
        boolean[][] v = new boolean[grid.length][grid[0].length];

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[r].length; c++) {

                if (!v[r][c] && recur(grid, v, r, c, grid[r][c], -1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }
    private int[][] dirs = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    private boolean recur(char[][] grid, boolean[][] v, int r, int c, char value, int pr, int pc) {

        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] != value) {
            return false;
        }

        if (v[r][c]) {
            return true;
        }
        v[r][c] = true;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr == pr && nc == pc) {
                continue;
            }

            if (recur(grid, v, nr, nc, value, r, c)) {
                return true;
            }
        }
        return false;
    }
    //=============================================================================================
    //BFS approach
    public boolean containsCycle1(char[][] grid) {
        boolean[][] v = new boolean[grid.length][grid[0].length];

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[r].length; c++) {

                if (!v[r][c] && bfs(grid, v, r, c, grid[r][c], -1, -1)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean bfs(char[][] grid, boolean[][] v, int r, int c, char value, int pr, int pc) {
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c, pr, pc});
        v[r][c] = true;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] data = q.poll();

                for (int[] dir : dirs) {
                    int nr = dir[0] + data[0];
                    int nc = dir[1] + data[1];

                    if (nr < 0 || nc < 0 || nr == grid.length || nc == grid[0].length ||
                            grid[nr][nc] != value) {
                        continue;
                    }

                    if (nr == data[2] && nc == data[3]) {
                        continue;
                    }

                    if (v[nr][nc]) {
                        return true;
                    }
                    v[nr][nc] = true;
                    q.offer(new int[] {nr, nc, data[0], data[1]});
                }
            }
        }
        return false;
    }
}
