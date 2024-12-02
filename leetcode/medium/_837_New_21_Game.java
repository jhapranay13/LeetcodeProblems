package leetcode.medium;

/**
 *
 *
 * Alice plays the following game, loosely based on the card game "21".
 *
 * Alice starts with 0 points and draws numbers while she has less than k points. During each draw, she gains an integer number of points randomly from the range [1, maxPts], where maxPts is an integer. Each draw is independent and the outcomes have equal probabilities.
 *
 * Alice stops drawing numbers when she gets k or more points.
 *
 * Return the probability that Alice has n or fewer points.
 *
 * Answers within 10-5 of the actual answer are considered accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10, k = 1, maxPts = 10
 * Output: 1.00000
 * Explanation: Alice gets a single card, then stops.
 * Example 2:
 *
 * Input: n = 6, k = 1, maxPts = 10
 * Output: 0.60000
 * Explanation: Alice gets a single card, then stops.
 * In 6 out of 10 possibilities, she is at or below 6 points.
 * Example 3:
 *
 * Input: n = 21, k = 17, maxPts = 10
 * Output: 0.73278
 *
 *
 * Constraints:
 *
 * 0 <= k <= n <= 10^4
 * 1 <= maxPts <= 10^4
 *
 *
 */
// All solutions give TLE need to figure out
public class _837_New_21_Game {
    // Top down

    public double new21Game(int n, int k, int maxPts) {

        if (k == 0 || n >= k - 1 + maxPts) {
            return 1.0;
        }
        double prob = (double) 1 / (double)maxPts;
        double[] memo = new double[n + maxPts + 1];
        return recur(n, k, maxPts, prob, 0, memo);
    }

    private double recur(int n, int k, int maxPts, double prob, int curr, double[] memo) {

        if (curr > n) {
            return 0.0;
        }

        if (curr >= k) {
            return 1.0;
        }

        if(memo[curr] > 0) {
            return memo[curr];
        }
        double ans = 0.0;

        for (int i = 1; i <= maxPts; i++) {
            double next = recur(n, k, maxPts, prob, curr + i, memo);
            ans += prob * next;
        }
        return memo[curr] = ans;
    }
    //=============================================================================================
    // Bottom up
    public double new21Game2(int n, int k, int maxPts) {

        if (k == 0 || n >= k - 1 + maxPts) {
            return 1.0;
        }
        double prob = (double) 1 / (double)maxPts;
        double[] memo = new double[n + maxPts + 1];

        for (int curr = n; curr >= 0; curr--) {

            if (curr >= k) {
                memo[curr] = 1.0;
                continue;
            }
            double ans = 0.0;

            for (int i = 1; i <= maxPts; i++) {
                double next = memo[curr + i];
                ans += prob * next;
            }
            memo[curr] = ans;
        }
        return memo[0];
    }
}
