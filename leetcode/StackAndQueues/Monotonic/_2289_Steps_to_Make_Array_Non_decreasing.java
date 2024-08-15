package leetcode.StackAndQueues.Monotonic;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 *
 * You are given a 0-indexed integer array nums. In one step, remove all elements nums[i] where nums[i - 1] > nums[i] for all 0 < i < nums.length.
 *
 * Return the number of steps performed until nums becomes a non-decreasing array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,3,4,4,7,3,6,11,8,5,11]
 * Output: 3
 * Explanation: The following are the steps performed:
 * - Step 1: [5,3,4,4,7,3,6,11,8,5,11] becomes [5,4,4,7,6,11,11]
 * - Step 2: [5,4,4,7,6,11,11] becomes [5,4,7,11,11]
 * - Step 3: [5,4,7,11,11] becomes [5,7,11,11]
 * [5,7,11,11] is a non-decreasing array. Therefore, we return 3.
 * Example 2:
 *
 * Input: nums = [4,5,7,7,13]
 * Output: 0
 * Explanation: nums is already a non-decreasing array. Therefore, we return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 *
 */

public class _2289_Steps_to_Make_Array_Non_decreasing {
    // Idea is to start for the end and check the count for each nums[i] where nums[i + 1] is lesser
    public int totalSteps(int[] nums) {
        int ans = 0;
        Deque<int[]> q = new LinkedList<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            int count  = 0;

            while (!q.isEmpty() && nums[i] > nums[q.peek()[0]]) {
                count++;
                count = Math.max(count, q.pop()[1]);
            }
            ans = Math.max(ans, count);
            q.push(new int[] {i, count});
        }
        return ans;
    }
}
