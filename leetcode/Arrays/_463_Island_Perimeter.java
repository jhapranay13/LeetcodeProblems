package leetcode.Arrays;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * You are given row x col grid representing a map where grid[i][j] = 1 represents land and grid[i][j] = 0 represents water.
 *
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells).
 *
 * The island doesn't have "lakes", meaning the water inside isn't connected to the water around the island. One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
 *
 *
 *
 * Example 1:
 *                  [0,__|1|,___0,   0],
 *                  [|1,__1,____1|   0],
 *                  [0,__|1|,   0,   0],
 *                  [|1,__1|,   0,   0]
 *
 *
 * Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * Output: 16
 * Explanation: The perimeter is the 16 yellow stripes in the image above.
 * Example 2:
 *
 * Input: grid = [[1]]
 * Output: 4
 * Example 3:
 *
 * Input: grid = [[1,0]]
 * Output: 4
 *
 *
 * Constraints:
 *
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] is 0 or 1.
 * There is exactly one island in grid.
 *
 */

public class _463_Island_Perimeter {
    //Iterative approach
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int up, down, left, right;
        int result = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    if (r == 0) { up = 0; }
                    else { up = grid[r-1][c]; }

                    if (c == 0) { left = 0; }
                    else { left = grid[r][c-1]; }

                    if (r == rows-1) { down = 0; }
                    else { down = grid[r+1][c]; }

                    if (c == cols-1) { right = 0; }
                    else { right = grid[r][c+1]; }

                    result += 4-(up+left+right+down);
                }
            }
        }
        return result;
    }
    //=============================================================================================
    //DFS Approach
    public int islandPerimeter1(int[][] grid) {
        int ans = 0;

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {
                    Set<Integer> v = new HashSet<>();
                    ans = dfs(grid, r, c, v);
                    break;
                }
            }
        }
        return ans;
    }
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int dfs(int[][] grid, int r, int c, Set<Integer> v) {
        int id = c + r * grid[0].length;

        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] == 0) {
            return 1;
        }

        if (v.contains(id)) {
            return 0;
        }
        v.add(id);
        int ans = 0;

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            ans += dfs(grid, nr, nc, v);
        }
        return ans;
    }
}
