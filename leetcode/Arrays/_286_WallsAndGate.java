package leetcode.Arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 *  You are given an m x n grid rooms initialized with these three possible values.
 *
 * -1 A wall or an obstacle.
 * 0 A gate.
 * INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: rooms = [[2147483647,-1,0,2147483647],
 *                  [2147483647,2147483647,2147483647,-1],
 *                  [2147483647,-1,2147483647,-1],
 *                  [0,-1,2147483647,2147483647]]
 * Output: [[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
 * Example 2:
 *
 * Input: rooms = [[-1]]
 * Output: [[-1]]
 * Example 3:
 *
 * Input: rooms = [[2147483647]]
 * Output: [[2147483647]]
 * Example 4:
 *
 * Input: rooms = [[0]]
 * Output: [[0]]
 *
 *
 * Constraints:
 *
 * m == rooms.length
 * n == rooms[i].length
 * 1 <= m, n <= 250
 * rooms[i][j] is -1, 0, or 231 - 1.
 *
 *
 *
 *
 */

public class _286_WallsAndGate {

    public void wallsAndGates(int[][] rooms) {
        Deque<int[]> q = new LinkedList<>();

        for (int r = 0; r < rooms.length; r++) {

            for (int c = 0; c < rooms[0].length; c++) {
                if (rooms[r][c] == 0) {
                    q.offer(new int[] {r, c});
                }
            }
        }
        int distance = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] pos = q.poll();

                for (int[] dir : dirs) {
                    int nr = pos[0] + dir[0];
                    int nc = pos[1] + dir[1];

                    if (nr < 0 || nr >= rooms.length || nc < 0 || nc >= rooms[0].length ||
                            rooms[nr][nc] != Integer.MAX_VALUE) {
                        continue;
                    }
                    rooms[nr][nc] = distance;
                    q.offer(new int[] {nr, nc});
                }
            }
            distance++;
        }
    }

    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
