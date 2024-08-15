package leetcode.medium;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these three possible types of commands:
 *
 * -2: Turn left 90 degrees.
 * -1: Turn right 90 degrees.
 * 1 <= k <= 9: Move forward k units, one unit at a time.
 * Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot runs into an obstacle, then it will instead stay in its current location and move on to the next command.
 *
 * Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5, return 25).
 *
 * Note:
 *
 * North means +Y direction.
 * East means +X direction.
 * South means -Y direction.
 * West means -X direction.
 *
 *
 * Example 1:
 *
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 4 units to (0, 4).
 * 2. Turn right.
 * 3. Move east 3 units to (3, 4).
 * The furthest point the robot ever gets from the origin is (3, 4), which squared is 32 + 42 = 25 units away.
 * Example 2:
 *
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 4 units to (0, 4).
 * 2. Turn right.
 * 3. Move east 1 unit and get blocked by the obstacle at (2, 4), robot is at (1, 4).
 * 4. Turn left.
 * 5. Move north 4 units to (1, 8).
 * The furthest point the robot ever gets from the origin is (1, 8), which squared is 12 + 82 = 65 units away.
 * Example 3:
 *
 * Input: commands = [6,-1,-1,6], obstacles = []
 * Output: 36
 * Explanation: The robot starts at (0, 0):
 * 1. Move north 6 units to (0, 6).
 * 2. Turn right.
 * 3. Turn right.
 * 4. Move south 6 units to (0, 0).
 * The furthest point the robot ever gets from the origin is (0, 6), which squared is 62 = 36 units away.
 *
 *
 * Constraints:
 *
 * 1 <= commands.length <= 10^4
 * commands[i] is either -2, -1, or an integer in the range [1, 9].
 * 0 <= obstacles.length <= 10^4
 * -3 * 10^4 <= xi, yi <= 3 * 10^4
 * The answer is guaranteed to be less than 2^31.
 *
 */

public class _874_Walking_Robot_Simulation {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> obstacleHolder = new HashSet<>();

        for (int[] obstacle : obstacles) {
            String obKey = obstacle[0] + ", " + obstacle[1];
            obstacleHolder.add(obKey);
        }
        int dir = 0;
        int row = 0;
        int col = 0;
        int ans = 0;

        outer:
        for (int command : commands) {

            if (command == -1) {
                dir = ((dir + 1) + dirs.length) % dirs.length;
            } else if (command == -2) {
                dir = ((dir - 1) + dirs.length) % dirs.length;
            } else {

                while (command-- > 0) {
                    int nr = dirs[dir][0] + row;
                    int nc = dirs[dir][1] + col;
                    String key = nr + ", " + nc;

                    if (obstacleHolder.contains(key)) {
                        continue outer;
                    }
                    row = nr;
                    col = nc;
                    ans = Math.max(ans, row * row + col * col);
                }
            }
        }
        return ans;
    }
    // Arranging Clock wise
    private int [][]dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
}
