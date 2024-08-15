package leetcode.Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n. There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
 *
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 *
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 * Example 2:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5 to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 * Example 3:
 *
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 *
 * Constraints:
 *
 * 1 <= k <= n <= 10^5
 * speed.length == n
 * efficiency.length == n
 * 1 <= speed[i] <= 10^5
 * 1 <= efficiency[i] <= 10^8
 *
 */

public class _1383_Maximum_Performance_of_a_Team {
    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] se = new int[speed.length][2];

        for (int i = 0; i < speed.length; i++) {
            se[i][0] = speed[i];
            se[i][1] = efficiency[i];
        }
        Arrays.sort(se, (a, b) -> b[1] - a[1]);// Sort by efficiency in descending order so that we get minimum at every postion
        PriorityQueue<int[]> eq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        long speedSum = 0;
        long ans = 0;
        //idea is to take the current efficiency as minimum and most speed till that time
        for (int[] seData : se) {
            speedSum += seData[0];

            while (!eq.isEmpty() && eq.size() > k - 1) {
                //Removing the speeds which are less and are extra
                int[] tempSeData = eq.poll();
                speedSum -= tempSeData[0];
            }
            eq.offer(seData);
            ans = Math.max(ans, speedSum * seData[1]);
        }
        return (int) (ans % 1000000007);
    }
}
