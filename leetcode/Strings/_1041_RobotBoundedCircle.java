package leetcode.Strings;

/**
 *
 * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three instructions:
 *
 * "G": go straight 1 unit;
 * "L": turn 90 degrees to the left;
 * "R": turn 90 degrees to the right.
 * The robot performs the instructions given in order, and repeats them forever.
 *
 * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
 *
 *
 *
 * Example 1:
 *
 * Input: instructions = "GGLLGG"
 * Output: true
 * Explanation: The robot moves from (0,0) to (0,2), turns 180 degrees, and then returns to (0,0).
 * When repeating these instructions, the robot remains in the circle of radius 2 centered at the origin.
 * Example 2:
 *
 * Input: instructions = "GG"
 * Output: false
 * Explanation: The robot moves north indefinitely.
 * Example 3:
 *
 * Input: instructions = "GL"
 * Output: true
 * Explanation: The robot moves from (0, 0) -> (0, 1) -> (-1, 1) -> (-1, 0) -> (0, 0) -> ...
 *
 *
 * Constraints:
 *
 * 1 <= instructions.length <= 100
 * instructions[i] is 'G', 'L' or, 'R'.
 *
 */

public class _1041_RobotBoundedCircle {
    //limit cycle trajectory.

    //After at most 4 cycles, the limit cycle trajectory returns to the initial point x = 0, y = 0.
    //That is related to the fact that 4 directions (north, east, south, west) define the
    //repeated cycles' plane symmetry
    //We do not need to run 4 cycles to identify the limit cycle trajectory. One cycle is enough.
    //There could be two situations here.

    //First, if the robot returns to the initial point after one cycle, that's the limit cycle trajectory.

    //Second, if the robot doesn't face north at the end of the first cycle, that's the limit
    //cycle trajectory. Once again, that's the consequence of the plane symmetry for the repeated cycles

    public boolean isRobotBounded(String instructions) {
        //directions arranged in clock wise
        // suppose index north = 0, east = 1, south = 2, west = 3
        int [][]dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int dir = 0;
        int r = 0;
        int c = 0;

        for (int i = 0; i < instructions.length(); i++) {
            char ch = instructions.charAt(i);

            if (ch == 'L') {
                dir = (dir + 1) % 4; //in case current direction is 3 next should be 0
            } else if (ch == 'R') {
                dir = (dir - 1 + 4) % 4; //in case current direction is zero next should be 3
            } else {
                r = r + dirs[dir][0];
                c = c + dirs[dir][1];
            }
        }
        return (r == 0 && c == 0) || dir != 0;
    }
}
