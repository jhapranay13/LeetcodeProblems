package leetcode.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Given an integer array nums and an integer k, you are asked to construct the array ans of size n-k+1 where ans[i] is the number of distinct numbers in the subarray nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]].
 *
 * Return the array ans.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,2,2,1,3], k = 3
 * Output: [3,2,2,2,3]
 * Explanation: The number of distinct elements in each subarray goes as follows:
 * - nums[0:2] = [1,2,3] so ans[0] = 3
 * - nums[1:3] = [2,3,2] so ans[1] = 2
 * - nums[2:4] = [3,2,2] so ans[2] = 2
 * - nums[3:5] = [2,2,1] so ans[3] = 2
 * - nums[4:6] = [2,1,3] so ans[4] = 3
 * Example 2:
 *
 * Input: nums = [1,1,1,1,2,3,4], k = 4
 * Output: [1,2,3,4]
 * Explanation: The number of distinct elements in each subarray goes as follows:
 * - nums[0:3] = [1,1,1,1] so ans[0] = 1
 * - nums[1:4] = [1,1,1,2] so ans[1] = 2
 * - nums[2:5] = [1,1,2,3] so ans[2] = 3
 * - nums[3:6] = [1,2,3,4] so ans[3] = 4
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 */

public class _1852_Distinct_Numbers_in_Each_Subarray {
    public int[] distinctNumbers(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int slow = 0;
        int fast = 0;
        int[] ans = new int[nums.length - k + 1];
        int index = 0;

        while (fast < nums.length) {
            int num = nums[fast];
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            if (fast - slow + 1 == k) {
                ans[index++] = freqMap.size();
                int slowNum = nums[slow++];

                if (freqMap.get(slowNum) == 1) {
                    freqMap.remove(slowNum);
                } else {
                    freqMap.put(slowNum, freqMap.get(slowNum) - 1);
                }
            }
            fast++;
        }
        return ans;
    }
}
