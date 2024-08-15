package leetcode.medium;

import java.util.*;

/**
 *
 * A perfectly straight street is represented by a number line. The street has street lamp(s) on it and is represented by a 2D integer array lights. Each lights[i] = [positioni, rangei] indicates that there is a street lamp at position positioni that lights up the area from [positioni - rangei, positioni + rangei] (inclusive).
 *
 * The brightness of a position p is defined as the number of street lamp that light up the position p.
 *
 * Given lights, return the brightest position on the street. If there are multiple brightest positions, return the smallest one.
 *
 *
 *
 * Example 1:
 *
 *                                /| \
 *                              /  |  \
 *                            /    |   \
 *                          /      |    \
 *                        /        |      \
 *             _________/__________|________\_________________________________________
 *              -6    -5    -4    -3   -2    -1    0    1    2     3     4
 *              Similarly for index 1 and 2
 *
 * Input: lights = [[-3,2],[1,2],[3,3]]
 * Output: -1
 * Explanation:
 * The first street lamp lights up the area from [(-3) - 2, (-3) + 2] = [-5, -1].
 * The second street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].
 * The third street lamp lights up the area from [3 - 3, 3 + 3] = [0, 6].
 *
 * Position -1 has a brightness of 2, illuminated by the first and second street light.
 * Positions 0, 1, 2, and 3 have a brightness of 2, illuminated by the second and third street light.
 * Out of all these positions, -1 is the smallest, so return it.
 * Example 2:
 *
 * Input: lights = [[1,0],[0,1]]
 * Output: 1
 * Explanation:
 * The first street lamp lights up the area from [1 - 0, 1 + 0] = [1, 1].
 * The second street lamp lights up the area from [0 - 1, 0 + 1] = [-1, 1].
 *
 * Position 1 has a brightness of 2, illuminated by the first and second street light.
 * Return 1 because it is the brightest position on the street.
 * Example 3:
 *
 * Input: lights = [[1,2]]
 * Output: -1
 * Explanation:
 * The first street lamp lights up the area from [1 - 2, 1 + 2] = [-1, 3].
 *
 * Positions -1, 0, 1, 2, and 3 have a brightness of 1, illuminated by the first street light.
 * Out of all these positions, -1 is the smallest, so return it.
 *
 *
 * Constraints:
 *
 * 1 <= lights.length <= 105
 * lights[i].length == 2
 * -108 <= positioni <= 108
 * 0 <= rangei <= 108
 *
 */

public class _2021_Brightest_Position_on_Street {
    // Similar to maximum Meeting room problem
    public int brightestPosition(int[][] lights) {
        List<int[]> holder = new ArrayList<>();

        for (int[] light : lights) {
            holder.add(new int[] {light[0] - light[1], light[0] + light[1]});
        }
        Collections.sort(holder, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int point = 0;
        int max = 0;

        for (int[] light : holder) {

            while (!pq.isEmpty() && pq.peek()[1] < light[0]) {
                pq.poll();
            }
            pq.offer(light);

            if (pq.size() > max) {
                max = pq.size();
                point = light[0];
            }
        }
        return point;
    }
    //=============================================================================================
    // Line sweep algo
    public int brightestPosition1(int[][] lights) {
        TreeMap<Integer, Integer> lineSweep = new TreeMap<>();

        for (int i = 0; i < lights.length; i++) {
            int left = lights[i][0] - lights[i][1];
            int right = lights[i][0] + lights[i][1];
            lineSweep.put(left, lineSweep.getOrDefault(left, 0) + 1);
            // Putting end as right + 1 since if last is overlapping with start ans end the count will be zero
            // In order to avoid that as it is included adding one which is excluded.
            lineSweep.put(right + 1, lineSweep.getOrDefault(right + 1, 0) - 1);
        }
        int point = 0;
        int max = 0;
        int curr = 0;

        for (int key : lineSweep.keySet()) {
            curr += lineSweep.get(key);

            if (curr > max) {
                max = curr;
                point = key;
            }
        }
        return point;
    }
}
