package leetcode.medium;

/**
 *
 * You are given an integer array nums and an integer k. You can partition the array into at most k non-empty adjacent subarrays. The score of a partition is the sum of the averages of each subarray.
 *
 * Note that the partition must use every integer in nums, and that the score is not necessarily an integer.
 *
 * Return the maximum score you can achieve of all the possible partitions. Answers within 10-6 of the actual answer will be accepted.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [9,1,2,3,9], k = 3
 * Output: 20.00000
 * Explanation:
 * The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 * Example 2:
 *
 * Input: nums = [1,2,3,4,5,6,7], k = 4
 * Output: 20.50000
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 */

public class _813_Largest_Sum_of_Averages {
    // Bottom up
    public double largestSumOfAverages(int[] nums, int k) {
        double[][] memo = new double[nums.length + 1][k + 1];

        for (int index = nums.length - 1; index >= 0; index--) {

            for (int partition = k - 1; partition >= 0; partition--) {
                double sum = 0;

                if (k - partition == 1) {
                    for (int i = index; i < nums.length; i++) {
                        sum += nums[i];
                    }
                    double avg = sum / (nums.length - index);
                    memo[index][partition] = avg;
                    continue;
                }
                double ans = 0;

                for (int i = index; i < nums.length; i++) {
                    sum += nums[i];
                    double avg = sum / (i - index + 1);
                    double temp = memo[i + 1][partition + 1];
                    ans = Math.max(ans, temp + avg);
                }
                memo[index][partition] = ans;
            }
        }
        return memo[0][0];
    }
    //=============================================================================================
    // Top Down Approach
    public double largestSumOfAverages1(int[] nums, int k) {
        double[][] memo = new double[nums.length][k + 1];
        return recur(nums, k, 0, 0, memo);
    }

    private double recur(int[] nums, int k, int index, int partition, double[][] memo) {

        if (index == nums.length) {
            return 0.0;
        }
        double sum = 0;

        if (k - partition == 1) {
            for (int i = index; i < nums.length; i++) {
                sum += nums[i];
            }
            double avg = sum / (nums.length - index);
            return avg;
        }

        if (memo[index][partition] != 0) {
            return memo[index][partition];
        }
        double ans = 0;

        for (int i = index; i < nums.length; i++) {
            sum += nums[i];
            double avg = sum / (i - index + 1);
            double temp = recur(nums, k, i + 1, partition + 1, memo);
            ans = Math.max(ans, temp + avg);
        }
        return memo[index][partition] = ans;
    }

}
