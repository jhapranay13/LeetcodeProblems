package leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * You are given a 0-indexed integer array nums of length n. The number of ways to partition nums is the number of pivot indices that satisfy both conditions:
 *
 * 1 <= pivot < n
 * nums[0] + nums[1] + ... + nums[pivot - 1] == nums[pivot] + nums[pivot + 1] + ... + nums[n - 1]
 * You are also given an integer k. You can choose to change the value of one element of nums to k, or to leave the array unchanged.
 *
 * Return the maximum possible number of ways to partition nums to satisfy both conditions after changing at most one element.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,-1,2], k = 3
 * Output: 1
 * Explanation: One optimal approach is to change nums[0] to k. The array becomes [3,-1,2].
 * There is one way to partition the array:
 * - For pivot = 2, we have the partition [3,-1 | 2]: 3 + -1 == 2.
 * Example 2:
 *
 * Input: nums = [0,0,0], k = 1
 * Output: 2
 * Explanation: The optimal approach is to leave the array unchanged.
 * There are two ways to partition the array:
 * - For pivot = 1, we have the partition [0 | 0,0]: 0 == 0 + 0.
 * - For pivot = 2, we have the partition [0,0 | 0]: 0 + 0 == 0.
 * Example 3:
 *
 * Input: nums = [22,4,-25,-20,-15,15,-16,7,19,-10,0,-13,-14], k = -33
 * Output: 4
 * Explanation: One optimal approach is to change nums[2] to k. The array becomes [22,4,-33,-20,-15,15,-16,7,19,-10,0,-13,-14].
 * There are four ways to partition the array.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 2 <= n <= 10^5
 * -10^5 <= k, nums[i] <= 10^5
 *
 */

public class _2025_Maximum_Number_of_Ways_to_Partition_an_Array {
    public int waysToPartition(int[] nums, int k) {
        int presum[] = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {

            if (i == 0) {
                presum[i] = nums[i];
            } else {
                presum[i] = nums[i] + presum[i - 1];
            }
        }
        int sufsum[] = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {

            if (i == nums.length - 1) {
                sufsum[i] = nums[i];
            } else {
                sufsum[i] = nums[i] + sufsum[i + 1];
            }
        }
        Map<Integer, Integer> freq = new HashMap<>();
        int sum = presum[nums.length - 1];
        int[] holder = new int[nums.length]; // Count at a particular position

        for (int i = 0; i < nums.length; i++) {
            int newSum = sum - nums[i] + k;

            if (newSum % 2 == 0) {
                holder[i] += freq.getOrDefault(newSum / 2, 0);
            }
            freq.put(presum[i], freq.getOrDefault(presum[i], 0) + 1);
        }
        freq.clear();

        for (int i = nums.length - 1; i >= 0; i--) {
            int newSum = sum - nums[i] + k;

            if (newSum % 2 == 0) {
                holder[i] += freq.getOrDefault(newSum / 2, 0);
            }
            freq.put(sufsum[i], freq.getOrDefault(sufsum[i], 0) + 1);
        }
        int ans1 = 0;
        //nums.length - 1 because we need to divide the array into 2
        for (int i = 0; i < nums.length - 1; i++) {

            if (sum % 2 == 0 && presum[i] == sum / 2) {
                ans1++;
            }
        }
        int ans2 = 0;

        for (int i = 0; i < holder.length; i++) {
            ans2 = Math.max(ans2, holder[i]);
        }
        return Math.max(ans1, ans2);
    }
    //=============================================================================================
    // Another way
    public int waysToPartition1(int[] nums, int k) {
        int sum = 0;
        int presum[] = new int[nums.length];
        Map<Integer, Integer> sumCount = new HashMap<>();
        // Not putting last in the map since we are diving the array into 2
        for (int i = 0; i < nums.length - 1; i++) {

            if (i == 0) {
                presum[i] = nums[i];
            } else {
                presum[i] = nums[i] + presum[i - 1];
            }
            sumCount.put(presum[i], sumCount.getOrDefault(presum[i], 0) + 1);
        }
        presum[nums.length - 1] = nums[nums.length - 1] + presum[nums.length - 2];
        sum = presum[nums.length - 1];
        int ans = 0;

        if (sum % 2 == 0) {
            ans = sumCount.getOrDefault(sum / 2, 0);
        }
        Map<Integer, Integer> prevSumCount = new HashMap<>();
        // Keeping prev sum count and prefix contribution to prev step

        for (int i = 0; i < nums.length; i++) {
            int diff = k - nums[i];
            int changedSum = sum + diff;

            if (changedSum % 2 == 0) {
                int left = prevSumCount.getOrDefault(changedSum / 2, 0);
                int right = sumCount.getOrDefault(changedSum / 2 - diff, 0);
                ans = Math.max(ans, left + right);
            }
            sumCount.put(presum[i], sumCount.getOrDefault(presum[i], 0) - 1);

            if (sumCount.get(presum[i]) <= 0) {
                sumCount.remove(presum[i]);
            }
            prevSumCount.put(presum[i], prevSumCount.getOrDefault(presum[i], 0) + 1);
        }
        return ans;
    }
}
