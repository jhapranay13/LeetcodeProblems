package leetcode.BFS;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *
 * This is an interactive problem.
 *
 * There is a robot in a hidden grid, and you are trying to get it from its starting cell to the target cell in this grid. The grid is of size m x n, and each cell in the grid is either empty or blocked. It is guaranteed that the starting cell and the target cell are different, and neither of them is blocked.
 *
 * You want to find the minimum distance to the target cell. However, you do not know the grid's dimensions, the starting cell, nor the target cell. You are only allowed to ask queries to the GridMaster object.
 *
 * Thr GridMaster class has the following functions:
 *
 * boolean canMove(char direction) Returns true if the robot can move in that direction. Otherwise, it returns false.
 * void move(char direction) Moves the robot in that direction. If this move would move the robot to a blocked cell or off the grid, the move will be ignored, and the robot will remain in the same position.
 * boolean isTarget() Returns true if the robot is currently on the target cell. Otherwise, it returns false.
 * Note that direction in the above functions should be a character from {'U','D','L','R'}, representing the directions up, down, left, and right, respectively.
 *
 * Return the minimum distance between the robot's initial starting cell and the target cell. If there is no valid path between the cells, return -1.
 *
 * Custom testing:
 *
 * The test input is read as a 2D matrix grid of size m x n where:
 *
 * grid[i][j] == -1 indicates that the robot is in cell (i, j) (the starting cell).
 * grid[i][j] == 0 indicates that the cell (i, j) is blocked.
 * grid[i][j] == 1 indicates that the cell (i, j) is empty.
 * grid[i][j] == 2 indicates that the cell (i, j) is the target cell.
 * There is exactly one -1 and 2 in grid. Remember that you will not have this information in your code.
 *
 *
 *
 * Example 1:
 *
 * Input: grid = [[1,2],[-1,0]]
 * Output: 2
 * Explanation: One possible interaction is described below:
 * The robot is initially standing on cell (1, 0), denoted by the -1.
 * - master.canMove('U') returns true.
 * - master.canMove('D') returns false.
 * - master.canMove('L') returns false.
 * - master.canMove('R') returns false.
 * - master.move('U') moves the robot to the cell (0, 0).
 * - master.isTarget() returns false.
 * - master.canMove('U') returns false.
 * - master.canMove('D') returns true.
 * - master.canMove('L') returns false.
 * - master.canMove('R') returns true.
 * - master.move('R') moves the robot to the cell (0, 1).
 * - master.isTarget() returns true.
 * We now know that the target is the cell (0, 1), and the shortest path to the target cell is 2.
 * Example 2:
 *
 * Input: grid = [[0,0,-1],[1,1,1],[2,0,0]]
 * Output: 4
 * Explanation: The minimum distance between the robot and the target cell is 4.
 * Example 3:
 *
 * Input: grid = [[-1,0],[0,2]]
 * Output: -1
 * Explanation: There is no path from the robot to the target cell.
 *
 *
 * Constraints:
 *
 * 1 <= n, m <= 500
 * m == grid.length
 * n == grid[i].length
 * grid[i][j] is either -1, 0, 1, or 2.
 * There is exactly one -1 in grid.
 * There is exactly one 2 in grid.
 *
 */


/**
        * // This is the GridMaster's API interface.
        * // You should not implement it, or speculate about its implementation
        * class GridMaster {
 *     boolean canMove(char direction);
 *     void move(char direction);
 *     boolean isTarget();
 * }
 */

public class _1778_Shortest_Path_in_a_Hidden_Grid {
    class GridMaster {
      boolean canMove(char direction) {return false;}
      void move(char direction) {}
      boolean isTarget() {return true;}
  }

    public int findShortestPath(GridMaster master) {
        int[][] grid = new int[1002][1002];
        int r = 500;
        int c = 500;
        boolean[][] v = new boolean[1002][1002];
        generateGrid(r, c, master, grid, v);

        if (rd == -1 && cd == -1) {
            return -1;
        }
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {500, 500});
        int ans = 0;
        v = new boolean[1002][1002];
        v[500][500] = true;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] data = q.poll();
                int rc = data[0];
                int cc = data[1];

                if (rc == rd && cc == cd) {
                    return ans;
                }

                for (int[] dir : dirs) {
                    int nr = rc + dir[0];
                    int nc = cc + dir[1];

                    if (nr < 0 || nc < 0 || nr >= grid.length || nc >= grid[0].length || grid[nr][nc] == 0 || v[nr][nc]) {
                        continue;
                    }
                    v[nr][nc] = true;
                    q.offer(new int[] {nr, nc});
                }
            }
            ans++;
        }
        return ans;
    }
    int rd = -1;
    int cd = -1;
    private char[][] moves = new char[][]{{'R', 'L'}, {'L', 'R'}, {'D', 'U'}, {'U', 'D'}};
    private int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private void generateGrid(int r, int c, GridMaster gm, int[][] grid, boolean[][] v) {

        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || v[r][c]) {
            return;
        }

        if (gm.isTarget()) {
            rd = r;
            cd = c;
        }
        grid[r][c] = 1;
        v[r][c] = true;

        for (int i = 0; i < dirs.length; i++) {
            int nr = r + dirs[i][0];
            int nc = c + dirs[i][1];

            if (gm.canMove(moves[i][0])) {
                gm.move(moves[i][0]);
                generateGrid(nr, nc, gm, grid, v);
                gm.move(moves[i][1]);
            }
        }
    }
}
