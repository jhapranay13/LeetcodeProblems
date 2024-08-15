package leetcode.StackAndQueues;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * Given an m x n integer matrix heightMap representing the height of each unit cell in a 2D elevation map, return the volume of water it can trap after raining.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heightMap = [[1,4,3,1,3,2],[3,2,1,3,2,4],[2,3,3,2,3,1]]
 * Output: 4
 * Explanation: After the rain, water is trapped between the blocks.
 * We have two small ponds 1 and 3 units trapped.
 * The total volume of water trapped is 4.
 * Example 2:
 *
 *
 * Input: heightMap = [[3,3,3,3,3],[3,2,2,2,3],[3,2,1,2,3],[3,2,2,2,3],[3,3,3,3,3]]
 * Output: 10
 *
 *
 * Constraints:
 *
 * m == heightMap.length
 * n == heightMap[i].length
 * 1 <= m, n <= 200
 * 0 <= heightMap[i][j] <= 2 * 104
 *
 *
 */

public class _407_TrappingRainWater2 {
    public int trapRainWater(int[][] heightMap) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            public int compare(int[] x, int[] y) {
                return heightMap[x[0]][x[1]] - heightMap[y[0]][y[1]];
            }
        });
        boolean[][] visited = new boolean[heightMap.length][heightMap[0].length];

        for (int i = 0; i < heightMap.length; i++) {
            pq.offer(new int[] {i, 0});
            visited[i][0] = true;
            pq.offer(new int[] {i, heightMap[0].length - 1});
            visited[i][heightMap[0].length - 1] = true;
        }

        for (int i = 0; i < heightMap[0].length; i++) {
            pq.offer(new int[] {0, i});
            visited[0][i] = true;
            pq.offer(new int[] {heightMap.length - 1, i});
            visited[heightMap.length - 1][i] = true;;
        }
        int minHeight = Integer.MIN_VALUE;
        int ans = 0;

        while (!pq.isEmpty()) {
            int[] pos = pq.poll();
            int height = heightMap[pos[0]][pos[1]];
            minHeight = Math.max(minHeight, height);
            //Since we are getting minimum height from the boundry via priority queue.
            //only the height which is less than can trap some water.
            ans += minHeight - height;

            for (int[] dir : dirs) {
                int nr = dir[0] + pos[0];
                int nc = dir[1] + pos[1];

                if (nr >= 0 && nc >= 0 && nr < heightMap.length && nc < heightMap[0].length
                        && !visited[nr][nc]) {
                    pq.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;
                }
            }
        }
        return ans;
    }
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
