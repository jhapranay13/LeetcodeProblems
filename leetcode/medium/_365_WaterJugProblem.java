package leetcode.medium;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 *
 *
 * You are given two jugs with capacities jug1Capacity and jug2Capacity liters.
 * There is an infinite amount of water supply available. Determine whether it is possible to
 * measure exactly targetCapacity liters using these two jugs.
 *
 * If targetCapacity liters of water are measurable, you must have targetCapacity liters of water
 * contained within one or both buckets by the end.
 *
 * Operations allowed:
 *
 * Fill any of the jugs with water.
 * Empty any of the jugs.
 * Pour water from one jug into another till the other jug is completely full, or the first jug
 * itself is empty.
 *
 *
 * Example 1:
 *
 * Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
 * Output: true
 * Explanation: The famous Die Hard example
 * Example 2:
 *
 * Input: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
 * Output: false
 * Example 3:
 *
 * Input: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= jug1Capacity, jug2Capacity, targetCapacity <= 106
 *
 */

public class _365_WaterJugProblem {
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        int small = Math.min(jug1Capacity, jug2Capacity);
        int large = Math.max(jug1Capacity, jug2Capacity);

        if (small == 0 || large == 0) {

            if (targetCapacity == 0) {
                return true;
            }
            return false;
        }
        Deque<int[]> q = new LinkedList<>();
        q.offer(new int[] {0, 0});
        Set<String> v = new HashSet<>();

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] state = q.poll();

                if (state[0] == targetCapacity || state[1] == targetCapacity ||
                        state[0] + state[1] == targetCapacity) {
                    return true;
                }
                String key = state[0] + ", " + state[1];

                if (!v.add(key)) {
                    continue;
                }

                if (state[0] < small) {
                    q.offer(new int[] {small, state[1]});
                }

                if (state[1] < large) {
                    q.offer(new int[] {state[0], large});
                }
                q.offer(new int[] {0, state[1]});
                q.offer(new int[] {state[0], 0});

                if (state[0] < small && state[1] > 0) {
                    int waterAvailable = state[1];
                    int waterNeeded = small - state[0];

                    if (waterAvailable < waterNeeded) {
                        q.offer(new int[] {state[0] + waterAvailable, 0});
                    } else {
                        q.offer(new int[] {small, state[1] - waterNeeded});
                    }
                }

                if (state[1] < large && state[0] > 0) {
                    int waterAvailable = state[0];
                    int waterNeeded = large - state[1];

                    if (waterAvailable < waterNeeded) {
                        q.offer(new int[] {0, state[1] + waterAvailable});
                    } else {
                        q.offer(new int[] {state[0] - waterNeeded, large});
                    }
                }
            }
        }
        return false;
    }
}
