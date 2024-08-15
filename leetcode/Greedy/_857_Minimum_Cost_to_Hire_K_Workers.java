package leetcode.Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 *
 * There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
 *
 * We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:
 *
 * Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * Every worker in the paid group must be paid at least their minimum wage expectation.
 * Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: quality = [10,20,5], wage = [70,50,30], k = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
 * Example 2:
 *
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 *
 *
 * Constraints:
 *
 * n == quality.length == wage.length
 * 1 <= k <= n <= 10^4
 * 1 <= quality[i], wage[i] <= 10^4
 *
 */

public class _857_Minimum_Cost_to_Hire_K_Workers {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        double[][] data = new double[quality.length][2];

        for (int i = 0; i < quality.length; i++) {
            // wage per quality ratio
            data[i] = new double []{(double) quality[i], (double)wage[i] / quality[i]};
        }
        Arrays.sort(data, (a, b) -> Double.compare(a[1], b[1]));
        // MAx heap to eliminate most expensive workers with heighest quality
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));
        double ans = Integer.MAX_VALUE;
        double sum = 0;

        for (double[] d : data) {
            sum += d[0];
            pq.offer(d);

            if (pq.size() > k) {
                sum -= pq.poll()[0];
            }

            if (pq.size() == k) {
                ans = Math.min(ans, sum * d[1]);
            }
        }
        return ans;
    }
}
