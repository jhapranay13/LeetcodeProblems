package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * You are given an array points containing the coordinates of points on a 2D plane, sorted by the x-values, where points[i] = [xi, yi] such that xi < xj for all 1 <= i < j <= points.length. You are also given an integer k.
 *
 * Return the maximum value of the equation yi + yj + |xi - xj| where |xi - xj| <= k and 1 <= i < j <= points.length.
 *
 * It is guaranteed that there exists at least one pair of points that satisfy the constraint |xi - xj| <= k.
 *
 *
 *
 * Example 1:
 *
 * Input: points = [[1,3],[2,0],[5,10],[6,-10]], k = 1
 * Output: 4
 * Explanation: The first two points satisfy the condition |xi - xj| <= 1 and if we calculate the equation we get 3 + 0 + |1 - 2| = 4. Third and fourth points also satisfy the condition and give a value of 10 + -10 + |5 - 6| = 1.
 * No other pairs satisfy the condition, so we return the max of 4 and 1.
 * Example 2:
 *
 * Input: points = [[0,0],[3,0],[9,2]], k = 3
 * Output: 3
 * Explanation: Only the first two points have an absolute difference of 3 or less in the x-values, and give the value of 0 + 0 + |0 - 3| = 3.
 *
 *
 * Constraints:
 *
 * 2 <= points.length <= 105
 * points[i].length == 2
 * -108 <= xi, yi <= 108
 * 0 <= k <= 2 * 108
 * xi < xj for all 1 <= i < j <= points.length
 * xi form a strictly increasing sequence.
 *
 */

public class _1499_MaxValueOfEquation {
    //Still researching
    //=============================================================================================
    //PriorityQueue Approach
    public int findMaxValueOfEquation(int[][] points, int k) {
        int maxValue = Integer.MIN_VALUE;
        PriorityQueue<int[]> candidates = new PriorityQueue<>((o0, o1) -> o0[0] == o1[0] ? -1 * Integer.compare(o0[1], o1[1]) : -1 * Integer.compare(o0[0], o1[0]));

        // point is xj, yj value we want to find for best value
        for (int[] point : points) {
            while (!candidates.isEmpty() && point[0] - candidates.peek()[1] > k)
                candidates.poll();

            if (!candidates.isEmpty())
                maxValue = Math.max(maxValue, candidates.peek()[0] + point[1] + point[0]);

            // The formula is yi + yj + xj - xi since xj > xi. We keep yi - xi in our queue
            candidates.add(new int[]{point[1] - point[0], point[0]});
        }
        return maxValue;

    }
    //============================================================================================
    //MonotonicQueue Approach
    //Given equation is yi + yj + |xi - xj|
    //converting it yi + yj - xi + xj
    //Grouping it (yi - xi) + (yj + xj)
    //Sum will be maximum if yi - xi is maximum
    //Since i < j and if current is j then we can pop out any yi and xj which is less than current
    public int findMaxValueOfEquation1(int[][] points, int k) {
        Deque<int[]> monoQueue = new LinkedList<>();
        int ans = Integer.MIN_VALUE;

        for(int[] point : points) {

            while (!monoQueue.isEmpty() && point[0] - monoQueue.peek()[0] > k) {
                monoQueue.pollFirst();
            }

            if (!monoQueue.isEmpty()) {
                ans = Math.max(ans, monoQueue.peek()[1] + point[1] - monoQueue.peek()[0] + point[0]);
            }

            while (!monoQueue.isEmpty() && monoQueue.peekLast()[1] - monoQueue.peekLast()[0] <= point[1] - point[0]) {
                monoQueue.pollLast();
            }
            monoQueue.offerLast(point);
        }
        return ans;
    }
}
