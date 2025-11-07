package leetcode.hard;

import java.util.*;

/**
 *
 * There are n 1-indexed robots, each having a position on a line, health, and movement direction.
 *
 * You are given 0-indexed integer arrays positions, healths, and a string directions (directions[i] is either 'L' for left or 'R' for right). All integers in positions are unique.
 *
 * All robots start moving on the line simultaneously at the same speed in their given directions. If two robots ever share the same position while moving, they will collide.
 *
 * If two robots collide, the robot with lower health is removed from the line, and the health of the other robot decreases by one. The surviving robot continues in the same direction it was going. If both robots have the same health, they are both removed from the line.
 *
 * Your task is to determine the health of the robots that survive the collisions, in the same order that the robots were given, i.e. final health of robot 1 (if survived), final health of robot 2 (if survived), and so on. If there are no survivors, return an empty array.
 *
 * Return an array containing the health of the remaining robots (in the order they were given in the input), after no further collisions can occur.
 *
 * Note: The positions may be unsorted.
 *
 *
 *
 *
 * Example 1:
 *
 *              10  -->  15 -->   9   -->  17  -->   2
 *          -------------------------------------------
 *              1        2        3        4        5
 *
 * Input: positions = [5,4,3,2,1], healths = [2,17,9,15,10], directions = "RRRRR"
 * Output: [2,17,9,15,10]
 * Explanation: No collision occurs in this example, since all robots are moving in the same direction. So, the health of the robots in order from the first robot is returned, [2, 17, 9, 15, 10].
 * Example 2:
 *
 *               15 -->   10   <--  10  <-- 12
 *          -------------------------------------------
 *               2        3        5        6
 *
 * Input: positions = [3,5,2,6], healths = [10,10,15,12], directions = "RLRL"
 * Output: [14]
 * Explanation: There are 2 collisions in this example. Firstly, robot 1 and robot 2 will collide, and since both have the same health, they will be removed from the line. Next, robot 3 and robot 4 will collide and since robot 4's health is smaller, it gets removed, and robot 3's health becomes 15 - 1 = 14. Only robot 3 remains, so we return [14].
 * Example 3:
 *
 *
 *
 * Input: positions = [1,2,5,6], healths = [10,10,11,11], directions = "RLRL"
 * Output: []
 * Explanation: Robot 1 and robot 2 will collide and since both have the same health, they are both removed. Robot 3 and 4 will collide and since both have the same health, they are both removed. So, we return an empty array, [].
 *
 *
 * Constraints:
 *
 * 1 <= positions.length == healths.length == directions.length == n <= 105
 * 1 <= positions[i], healths[i] <= 109
 * directions[i] == 'L' or directions[i] == 'R'
 * All values in positions are distinct
 *
 */
public class _2751_Robot_Collisions {
    // 1 for right direction -1 for left direction
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int[][] holder = new int[positions.length][3];

        for (int i = 0; i < positions.length; i++) {
            holder[i] = new int[] {positions[i], directions.charAt(i) == 'R' ? 1 : -1, i};
        }
        // Sort by position
        Arrays.sort(holder, (a, b) -> a[0] - b[0]);
        Deque<int[]> stack = new LinkedList<>();

        for (int i = 0; i < positions.length; i++) {
            int[] curr = holder[i];

            if (curr[1] == 1) {
                stack.push(curr);
            } else {

                while (!stack.isEmpty() && healths[curr[2]] > 0) {
                    int[] prev = stack.pop();
                    int prevIndex = prev[2];
                    int currIndex = curr[2];

                    if (healths[prevIndex] > healths[currIndex]) {
                        healths[prevIndex] -= 1;
                        healths[currIndex] = 0;
                        stack.push(prev);
                    } else if (healths[prevIndex] < healths[currIndex]) {
                        healths[currIndex] -= 1;
                        healths[prevIndex] = 0;
                    } else {
                        healths[currIndex] = 0;
                        healths[prevIndex] = 0;
                        break;
                    }
                }
            }
        }
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < holder.length; i++) {

            if (healths[i] > 0) {
                ans.add(healths[i]);
            }
        }
        return ans;
    }
}
