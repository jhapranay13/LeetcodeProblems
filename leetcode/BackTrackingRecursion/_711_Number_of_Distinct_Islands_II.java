package leetcode.BackTrackingRecursion;

import java.util.*;

/**
 *
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 *
 * An island is considered to be the same as another if they have the same shape, or have the same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
 *
 * Return the number of distinct islands.
 *
 *
 *
 * Example 1:
 *
 *              [1,1,0,0,0],
 *              [1,0,0,0,0],
 *              [0,0,0,0,1],
 *              [0,0,0,1,1]
 *
 * Input: grid = [[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]
 * Output: 1
 * Explanation: The two islands are considered the same because if we make a 180 degrees clockwise rotation on the first island, then two islands will have the same shapes.
 * Example 2:
 *
 *
 * Input: grid = [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] is either 0 or 1.
 *
 */

public class _711_Number_of_Distinct_Islands_II {
    public int numDistinctIslands2(int[][] grid) {
        List<List<int[]>> holder = new ArrayList<>();

        for (int r = 0; r < grid.length; r++) {

            for (int c = 0; c < grid[0].length; c++) {

                if (grid[r][c] == 1) {
                    List<int[]> points = new ArrayList<>();
                    dfs(grid, points, r, c);
                    holder.add(points);
                }
            }
        }
        // taking the first point and substracting it from all point so that it could all be
        // zero based. Will make it simple to compare. First point coz that will be the minimum
        for (List<int[]> points : holder) {
            int[] data = points.get(0);
            int r = data[0];
            int c = data[1];

            for (int[] point : points) {
                point[0] -= r;
                point[1] -= c;
            }
        }
        Set<String> ansHolder = new HashSet<>();

        for (List<int[]> points : holder) {
            String str = processPointWithRotationAndReflection(points);
            ansHolder.add(str);
        }
        return ansHolder.size();
    }
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int[][] reflection = {{1, 1}, {-1, -1}, {-1, 1}, {1, -1}};
    //Every point cn be rotated and reflected in eight ways
    private String processPointWithRotationAndReflection(List<int[]> points) {
        List<List<int[]>> holder = new ArrayList<>();

        for (int[] reflect : reflection) {
            List<int[]> temp1 = new ArrayList<>();
            List<int[]> temp2 = new ArrayList<>();

            for (int[] point : points) {
                temp1.add(new int[] {reflect[0] * point[0], reflect[1] * point[1]});
                temp2.add(new int[] {reflect[1] * point[1], reflect[0] * point[0]});
            }
            Collections.sort(temp1, (a, b) -> {

                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            });
            Collections.sort(temp2, (a, b) -> {

                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            });
            holder.add(temp1);
            holder.add(temp2);
        }
        List<String> strHolder = new ArrayList<>();

        for (List<int[]> pointData: holder) {
            StringBuilder str = new StringBuilder();
            int x = pointData.get(0)[0];
            int y = pointData.get(0)[1];
            //Substracting minimum point s again to make it zero based which will be easy to compare
            for (int[] point : pointData) {
                str.append((point[0]- x) + "|" + (point[1] - y) + ", ");
            }
            strHolder.add(str.toString());
        }
        Collections.sort(strHolder);
        return strHolder.get(0);
    }

    private void dfs(int grid[][], List<int[]> points, int r, int c) {

        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] != 1) {
            return;
        }
        grid[r][c] = 2;
        points.add(new int[] {r, c});

        for (int[] dir : dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            dfs(grid, points, nr, nc);
        }
    }
}
