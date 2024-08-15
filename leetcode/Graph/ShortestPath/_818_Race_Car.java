package leetcode.Graph.ShortestPath;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions. Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
 *
 * When you get an instruction 'A', your car does the following:
 * position += speed
 * speed *= 2
 * When you get an instruction 'R', your car does the following:
 * If your speed is positive then speed = -1
 * otherwise speed = 1
 * Your position stays the same.
 * For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to 1 --> 2 --> 4 --> -1.
 *
 * Given a target position target, return the length of the shortest sequence of instructions to get there.
 *
 *
 *
 * Example 1:
 *
 * Input: target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0 --> 1 --> 3.
 * Example 2:
 *
 * Input: target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0 --> 1 --> 3 --> 7 --> 7 --> 6.
 *
 *
 * Constraints:
 *
 * 1 <= target <= 10^4
 *
 */

public class _818_Race_Car {
    public int racecar(int target) {
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 1, 0});
        Set<String> v = new HashSet<>();
        v.add(0 + "|" + 1);

        while (!q.isEmpty()) {
            int size = q.size();

            while(size-- > 0) {
                int pos[] = q.poll();

                if (Math.abs(pos[0]) > target * 2) {
                    continue;
                }

                if (pos[0] == target) {
                    return pos[2];
                }
                String key = (pos[0] + pos[1]) + "|" + (pos[1] * 2);

                if (v.add(key)) {
                    q.offer(new int[]{(pos[0] + pos[1]), (pos[1] * 2), pos[2] + 1});
                }
                key = pos[0] + "|" + (pos[1] > 0 ? -1 : 1);

                if (v.add(key)) {
                    q.offer(new int[]{pos[0], (pos[1] > 0 ? -1 : 1), pos[2] + 1});
                }
            }
        }
        return -1;
    }
}
