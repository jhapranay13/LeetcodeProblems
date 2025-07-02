package leetcode.Greedy;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * You are given an array nums of length n. You are also given an integer k.
 *
 * You perform the following operation on nums once:
 *
 * Select a subarray nums[i..j] where 0 <= i <= j <= n - 1.
 * Select an integer x and add x to all the elements in nums[i..j].
 * Find the maximum frequency of the value k after the operation.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6], k = 1
 *
 * Output: 2
 *
 * Explanation:
 *
 * After adding -5 to nums[2..5], 1 has a frequency of 2 in [1, 2, -2, -1, 0, 1].
 *
 * Example 2:
 *
 * Input: nums = [10,2,3,4,5,5,4,3,2,2], k = 10
 *
 * Output: 4
 *
 * Explanation:
 *
 * After adding 8 to nums[1..9], 10 has a frequency of 4 in [10, 10, 11, 12, 13, 13, 12, 11, 10, 10].
 *
 *
 *
 * Constraints:
 *
 * 1 <= n == nums.length <= 10^5
 * 1 <= nums[i] <= 50
 * 1 <= k <= 50
 *
 */
public class _3434_Maximum_Frequency_After_Subarray_Operation {
    public int maxFrequency(int[] nums, int k) {
        int kFreq = 0;
        Set<Integer> numbers = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == k) {
                kFreq++;
            } else {
                numbers.add(nums[i]);
            }

        }
        int ans = 0;

        for (int currNum : numbers) {
            int currNumFreq = 0;

            for (int j = 0; j < nums.length; j++) {

                if (currNum == nums[j]) {
                    currNumFreq++;
                } else if (nums[j] == k) {
                    currNumFreq--; // subtracting coz if we add x to this it won't remain K
                }

                if (currNumFreq < 0) {
                    currNumFreq = 0; // restarting from this point
                }
                ans = Math.max(ans, currNumFreq);
            }
        }
        return ans + kFreq;
    }
}
