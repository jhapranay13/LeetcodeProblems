package leetcode.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 *
 * You may assume that the borders of the maze are all walls (see examples).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * Example 2:
 *
 *
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [3,2]
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination. Notice that you can pass through the destination but you cannot stop there.
 * Example 3:
 *
 * Input: maze = [[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]], start = [4,3], destination = [0,1]
 * Output: false
 *
 *
 * Constraints:
 *
 * m == maze.length
 * n == maze[i].length
 * 1 <= m, n <= 100
 * maze[i][j] is 0 or 1.
 * start.length == 2
 * destination.length == 2
 * 0 <= startrow, destinationrow <= m
 * 0 <= startcol, destinationcol <= n
 * Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
 * The maze contains at least 2 empty spaces.
 *
 *
 */

public class _490_The_Maze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        Set<Integer> v = new HashSet<>();
        return recur(maze, destination, v, start[0], start[1]);
    }
    private int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private boolean recur(int[][] maze, int[] destination, Set<Integer> v, int r, int c) {

        if (r == destination[0] && c == destination[1]) {
            return true;
        }
        int idx = c + r * maze[0].length;
        v.add(idx);
        boolean ans = false;

        for (int[] dir : dirs) {
            int nr = r;
            int nc = c;

            while (nr >= 0 && nc >= 0 && nr != maze.length && nc != maze[0].length && maze[nr][nc] != 1) {
                nr += dir[0];
                nc += dir[1];
            }
            nr -= dir[0];
            nc -= dir[1];

            int nidx = nc + nr * maze[0].length;

            if (v.contains(nidx)) {
                continue;
            }
            ans = recur(maze, destination, v, nr, nc);

            if (ans) {
                break;
            }
        }
        return ans;
    }
}
