package leetcode.hard;

/**
 *
 *
 * You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are also given an integer k.
 *
 * You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on an integer position.
 *
 * Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
 *
 * Return the smallest possible value of penalty(). Answers within 10^-6 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
 * Output: 0.50000
 * Example 2:
 *
 * Input: stations = [23,24,36,39,46,56,57,65,84,98], k = 1
 * Output: 14.00000
 *
 *
 * Constraints:
 *
 * 10 <= stations.length <= 2000
 * 0 <= stations[i] <= 10^8
 * stations is sorted in a strictly increasing order.
 * 1 <= k <= 10^6
 *
 *
 */

public class _774_Minimize_Max_Distance_to_Gas_Station {
    public double minmaxGasDist(int[] stations, int k) {
        double lo = 0;
        double hi = 1e8;
        double ans = 0;
        // Answers within 10^-6 of the actual answer will be accepted.
        while (lo + 1e-6 < hi) {
            double pivot = lo + (hi - lo) / 2;

            if (check(stations, k, pivot)) {
                hi = pivot;
                ans = pivot;
            } else {
                lo = pivot;
            }
        }
        return ans;
    }

    private boolean check(int[] stations, int availableStation, double distance) {
        int usedStation = 0;

        for (int i = 0; i < stations.length - 1; i++) {
            usedStation += Math.ceil((stations[i + 1] - stations[i]) / distance) - 1;
        }
        return usedStation <= availableStation;
    }
}
