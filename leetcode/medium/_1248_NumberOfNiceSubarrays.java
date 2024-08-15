package leetcode.medium;

/**
 *
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 */

public class _1248_NumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        int ans = 0;
        int fast = 0;
        int slow = 0;
        int formed = 0;

        while (fast < nums.length) {
            int num = nums[fast++];

            if (num % 2 != 0) {
                formed++;
            }

            if (formed == k) {
                int count = 0;
                //keeping the count of Substring formed by moving slow
                while (slow <= fast && formed == k) {
                    int num2 = nums[slow++];
                    count++;

                    if (num2 % 2 != 0) {
                        formed--;
                    }
                }
                int tempAns = count;
                //Till the fast finds another one to make it formed adding the substring count
                //since every addition of a number will be equalant to the substring count pf before
                //as that many new substrings will be formed
                while (fast < nums.length && formed < k) {
                    int nums3 = nums[fast++];

                    if (nums3 % 2 != 0) {
                        formed++;
                        continue;
                    }
                    tempAns += count;
                }
                ans += tempAns;
                fast--;
                formed--;
            }
        }
        return ans;
    }
}
