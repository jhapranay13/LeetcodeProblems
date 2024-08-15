package leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * You are given an integer array nums and an integer k. Append k unique positive integers that do not appear in nums to nums such that the resulting total sum is minimum.
 *
 * Return the sum of the k integers appended to nums.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,4,25,10,25], k = 2
 * Output: 5
 * Explanation: The two unique positive integers that do not appear in nums which we append are 2 and 3.
 * The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
 * The sum of the two integers appended is 2 + 3 = 5, so we return 5.
 * Example 2:
 *
 * Input: nums = [5,6], k = 6
 * Output: 25
 * Explanation: The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
 * The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum.
 * The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.
 *
 *
 * [96,44,99,25,61,84,88,18,19,33,60,86,52,19,32,47,35,50,94,17,29,98,22,21,72,100,40,84]
 * 35
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^8
 *
 */

public class _2195_Append_K_Integers_With_Minimal_Sum {
    public long minimalKSum(int[] nums, int k) {
        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }
        Arrays.sort(nums);
        long ans = 0;

        if (nums[0] != 1) {

            if (nums[0] > k) {
                ans += progressionSum(k);
                k = 0;
            } else {
                ans += progressionSum(nums[0] - 1);
                k -= nums[0] - 1;
            }
        }

        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];
            int nextNum = nums[i + 1];

            if (nextNum == num) {
                continue;
            }

            if (nextNum != num + 1 && k > 0) {

                if (num + 1 == nextNum - 1) {
                    ans += num + 1;
                    k--;
                } else {
                    int start = num + 1;
                    int diff = nextNum - num - 1;
                    int end = diff < k ? start + diff - 1 : start + k - 1;
                    long startSum = progressionSum(start - 1);
                    long endSum = progressionSum(end);
                    ans += (endSum - startSum);
                    k -= diff;
                }
            }
        }

        if (k > 0) {
            int end = max + k;
            long startSum = progressionSum(max);
            long endSum = progressionSum(end);
            ans += endSum - startSum;
        }
        return ans;
    }

    private long progressionSum(int num) {
        long ans = 0;
        ans = (long)(num) * (long)(num + 1) / 2;
        return ans;
    }
    //=============================================================================================
    //TLE Solution
    public long minimalKSum1(int[] nums, int k) {
        Set<Integer> holder= new HashSet<>();
        int max = 0;

        for (int num : nums) {
            holder.add(num);
            max = Math.max(max, num);
        }
        Arrays.sort(nums);
        long ans = 0;

        if (nums[0] != 1) {

            if (nums[0] > k) {
                ans += ((long)k * (long)(k + 1)) / 2;
                k = 0;
            } else {
                ans += ((long)nums[0] * (long)(nums[0] - 1)) / 2;
                k -= nums[0] - 1;
            }
        }
        int prev = 0;

        for (int num : nums) {

            if (prev == num) {
                continue;
            }
            int currNum = num + 1;

            while(!holder.contains(currNum) && k > 0) {
                ans += currNum;
                currNum++;
                k--;
            }

            if (k == 0) {
                break;
            }
            prev = num;
        }
        int curr = max + 1;

       /* while (k > 0) {
            ans += curr;
            curr += 1;
            k--;
        }*/
        return ans;
    }
}
