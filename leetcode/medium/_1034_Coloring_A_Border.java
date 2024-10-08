package leetcode.medium;

/**
 *
 * You are given an m x n integer matrix grid, and three integers row, col, and color. Each value in the grid represents the color of the grid square at that location.
 *
 * Two squares belong to the same connected component if they have the same color and are next to each other in any of the 4 directions.
 *
 * The border of a connected component is all the squares in the connected component that are either 4-directionally adjacent to a square not in the component, or on the boundary of the grid (the first or last row or column).
 *
 * You should color the border of the connected component that contains the square grid[row][col] with color.
 *
 * Return the final grid.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * Output: [[3,3],[3,2]]
 * Example 2:
 *
 * Input: grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * Output: [[1,3,3],[2,3,3]]
 * Example 3:
 *
 * Input: grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * Output: [[2,2,2],[2,1,2],[2,2,2]]
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j], color <= 1000
 * 0 <= row < m
 * 0 <= col < n
 *
 */

public class _1034_Coloring_A_Border {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        recur(grid, color, new boolean[grid.length][grid[0].length], row, col, grid[row][col]);
        return grid;
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private void recur(int[][] grid, int color, boolean[][] v, int r, int c, int cellColor) {

        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || v[r][c] || grid[r][c] != cellColor) {
            return;
        }
        boolean border = false;
        v[r][c] = true;

        if (r == 0 || c == 0 || r == grid.length - 1 || c == grid[0].length - 1 ||
                grid[r + 1][c] != cellColor || grid[r - 1][c] != cellColor || grid[r][c - 1] != cellColor ||
                grid[r][c + 1] != cellColor) {
            border = true;
        }

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            recur(grid, color, v, nr, nc, cellColor);
        }

        if (border) {
            grid[r][c] = color;
        }
    }
}
