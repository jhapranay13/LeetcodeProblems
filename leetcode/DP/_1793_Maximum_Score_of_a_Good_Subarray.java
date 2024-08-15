package leetcode.DP;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * You are given an array of integers nums (0-indexed) and an integer k.
 *
 * The score of a subarray (i, j) is defined as min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1). A good subarray is a subarray where i <= k <= j.
 *
 * Return the maximum possible score of a good subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,3,7,4,5], k = 3
 * Output: 15
 * Explanation: The optimal subarray is (1, 5) with a score of min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15.
 * Example 2:
 *
 * Input: nums = [5,5,4,5,4,1,1,1], k = 0
 * Output: 20
 * Explanation: The optimal subarray is (0, 4) with a score of min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 2 * 10^4
 * 0 <= k < nums.length
 *
 */

public class _1793_Maximum_Score_of_a_Good_Subarray {
    public int maximumScore(int[] nums, int k) {
        int[] leftRange = new int[nums.length];
        Deque<Integer> mono = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            while (!mono.isEmpty() && nums[mono.peekLast()] >= num) {
                mono.pollLast();
            }
            leftRange[i] = mono.isEmpty() ? 0 : mono.peekLast() + 1;
            mono.offerLast(i);
        }
        mono = new LinkedList<>();
        int[] rightRange = new int[nums.length];

        for (int i = nums.length - 1; i >= 0; i--) {
            int num = nums[i];

            while (!mono.isEmpty() && nums[mono.peekLast()] >= num) {
                mono.pollLast();
            }
            rightRange[i] = mono.isEmpty() ? nums.length - 1 : mono.peekLast() - 1;
            mono.offerLast(i);
        }
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            int leftIndex = leftRange[i];
            int rightIndex = rightRange[i];

            if (leftIndex <= k && k <= rightIndex) {
                int length = rightIndex - leftIndex + 1;
                int num = nums[i];
                ans = Math.max(ans, length * num);
            }
        }
        return ans;
    }
}
