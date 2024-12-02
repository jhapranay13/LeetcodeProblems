package leetcode.BFS;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:
 *
 * A cell containing a thief if grid[r][c] = 1
 * An empty cell if grid[r][c] = 0
 * You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.
 *
 * The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.
 *
 * Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).
 *
 * An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.
 *
 * The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,0,0],[0,0,0],[0,0,1]]
 * Output: 0
 * Explanation: All paths from (0, 0) to (n - 1, n - 1) go through the thieves in cells (0, 0) and (n - 1, n - 1).
 * Example 2:
 *
 *                 | 0 |  0   1
 *                 | 0 |__0___0
 *                 | 0 |__0___0
 *
 * Input: grid = [[0,0,1],[0,0,0],[0,0,0]]
 * Output: 2
 * Explanation: The path depicted in the picture above has a safeness factor of 2 since:
 * - The closest cell of the path to the thief at cell (0, 2) is cell (0, 0). The distance between them is | 0 - 0 | + | 0 - 2 | = 2.
 * It can be shown that there are no other paths with a higher safeness factor.
 * Example 3:
 *
 *                     |0___|0|__0   1
 *                      0   |0|__0|  0
 *                      0    0  |0|__0
 *                      1    0  |0|__0|
 *
 * Input: grid = [[0,0,0,1],[0,0,0,0],[0,0,0,0],[1,0,0,0]]
 * Output: 2
 * Explanation: The path depicted in the picture above has a safeness factor of 2 since:
 * - The closest cell of the path to the thief at cell (0, 3) is cell (1, 2). The distance between them is | 0 - 1 | + | 3 - 2 | = 2.
 * - The closest cell of the path to the thief at cell (3, 0) is cell (3, 2). The distance between them is | 3 - 3 | + | 0 - 2 | = 2.
 * It can be shown that there are no other paths with a higher safeness factor.
 *
 *
 * Constraints:
 *
 * 1 <= grid.length == n <= 400
 * grid[i].length == n
 * grid[i][j] is either 0 or 1.
 * There is at least one thief in the grid.
 *
 *
 */

public class _2812_Find_the_Safest_Path_in_a_Grid {
    final int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] matrix = new int[n][n];
        Deque<int[]> q = new LinkedList<>();

        for (int r = 0; r < n; r++) {

            for (int c = 0; c < n; c++) {

                if (grid.get(r).get(c) == 1) {
                    matrix[r][c] = 0;
                    q.offer(new int[] {r, c});
                } else {
                    matrix[r][c] = -1;
                }
            }
        }
        int maxVal = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                int[] pos = q.poll();
                int r = pos[0];
                int c = pos[1];
                int val = matrix[r][c];

                for (int[] d : dir) {
                    int nr = r + d[0];
                    int nc = c + d[1];

                    if (nr >= 0 && nc >= 0 && nr < n && nc < n &&
                            matrix[nr][nc] == -1) {
                        matrix[nr][nc] = val + 1;
                        maxVal = Math.max(maxVal, val + 1);
                        q.offer(new int[] {nr, nc});
                    }
                }
            }
        }
        int lo = 0;
        int hi = maxVal;
        int ans = 0;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if(isValid(matrix, pivot)) {
                ans = pivot;
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private boolean isValid(int[][] matrix, int safeDist) {
        int n = matrix.length;

        if (matrix[0][0] < safeDist || matrix[n - 1][n - 1] < safeDist) {
            return false;
        }
        Deque<int[]> q = new LinkedList<>();
        boolean[][] v = new boolean[n][n];
        q.offer(new int[] {0, 0});
        v[0][0] = true;

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            int r = pos[0];
            int c = pos[1];

            for (int[] d : dir) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr == n - 1 && nc == n - 1) {
                    return true;
                }

                if (nr >= 0 && nc >= 0 && nr < n && nc < n &&
                        !v[nr][nc] && matrix[nr][nc] >= safeDist) {
                    q.offer(new int[] {nr, nc});
                    v[nr][nc] = true;
                }
            }
        }
        return false;
    }
}
