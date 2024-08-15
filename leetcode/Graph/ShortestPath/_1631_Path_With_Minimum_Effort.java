package leetcode.Graph.ShortestPath;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 *
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 *
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 *
 */

public class _1631_Path_With_Minimum_Effort {
    public int minimumEffortPath(int[][] heights) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] x, int[] y) {
                return x[2] - y[2];
            }
        });
        pq.offer(new int[] {0, 0, 0});
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] v = new boolean[heights.length][heights[0].length];

        while (!pq.isEmpty()) {
            int[] pos = pq.poll();
            int posVal = heights[pos[0]][pos[1]];
            v[pos[0]][pos[1]] = true;

            if (pos[0] == heights.length - 1 && pos[1] == heights[0].length - 1) {
                return pos[2];
            }

            for (int[] dir : dirs) {
                int nx = pos[0] + dir[0];
                int ny = pos[1] + dir[1];

                if (nx < 0 || ny < 0 || nx == heights.length || ny == heights[0].length
                        || v[nx][ny]) {
                    continue;
                }
                int nPosVal = heights[nx][ny];
                int diff = Math.abs(posVal - nPosVal);
                int diffToProceed = Math.max(diff, pos[2]);
                pq.offer(new int[] {nx, ny, diffToProceed});
            }
        }
        return -1;
    }
}
