package leetcode.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *
 * You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
 *
 * Return any such subsequence as an integer array of length k.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,1,3,3], k = 2
 * Output: [3,3]
 * Explanation:
 * The subsequence has the largest sum of 3 + 3 = 6.
 * Example 2:
 *
 * Input: nums = [-1,-2,3,4], k = 3
 * Output: [-1,3,4]
 * Explanation:
 * The subsequence has the largest sum of -1 + 3 + 4 = 6.
 * Example 3:
 *
 * Input: nums = [3,4,3,3], k = 2
 * Output: [3,4]
 * Explanation:
 * The subsequence has the largest sum of 3 + 4 = 7.
 * Another possible subsequence is [4, 3].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 */

public class _2099_Find_Subsequence_of_Length_K_With_the_Largest_Sum {
    public int[] maxSubsequence(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> nums[a] - nums[b]);

        for (int i = 0; i < nums.length; i++) {

            if (pq.size() <= k) {
                pq.offer(i);
            }

            if (pq.size() > k) {
                pq.poll();
            }
        }
        int[] ans = new int[k];
        int index = k - 1;

        while (!pq.isEmpty()) {
            ans[index--] = pq.poll();
        }
        Arrays.sort(ans);

        for (int i = 0; i < k; i++) {
            ans[i] = nums[ans[i]];
        }
        return ans;
    }
}
