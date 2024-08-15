package leetcode.HashMapHashSet;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an integer array nums of length n, return true if there is a triplet (i, j, k) which satisfies the following conditions:
 *
 * 0 < i, i + 1 < j, j + 1 < k < n - 1
 * The sum of subarrays (0, i - 1), (i + 1, j - 1), (j + 1, k - 1) and (k + 1, n - 1) is equal.
 * A subarray (l, r) represents a slice of the original array starting from the element indexed l to the element indexed r.
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,1,2,1]
 * Output: true
 * Explanation:
 * i = 1, j = 3, k = 5.
 * sum(0, i - 1) = sum(0, 0) = 1
 * sum(i + 1, j - 1) = sum(2, 2) = 1
 * sum(j + 1, k - 1) = sum(4, 4) = 1
 * sum(k + 1, n - 1) = sum(6, 6) = 1
 * Example 2:
 *
 * Input: nums = [1,2,1,2,1,2,1,2]
 * Output: false
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 2000
 * -10^6 <= nums[i] <= 10^6
 *
 */

public class _548_Split_Array_with_Equal_Sum {
    public boolean splitArray(int[] nums) {

        if (nums.length < 7) {
            return false;
        }
        int[] presum = new int[nums.length];
        presum[0] = nums[0];

        for (int i = 1; i< nums.length; i++) {
            presum[i] = presum[i - 1] + nums[i];
        }
        Set<Integer> holder = new HashSet<>();

        for (int j = 3; j < nums.length - 3; j++) {

            for(int i = 1; i < j - 1; i++) {
                int sum0Andi = presum[i - 1]; // (0, i - 1)
                int sumJAndI = presum[j - 1] - presum[i]; // (i + 1, j - 1)

                if (sum0Andi == sumJAndI) {
                    holder.add(sum0Andi);
                }
            }

            for (int k = j + 2; k < nums.length - 1; k++) {
                int sumJToK = presum[k - 1] - presum[j]; // (j + 1, k - 1)
                int sumKToN = presum[nums.length - 1] - presum[k]; // (k + 1, n - 1)

                if (sumJToK == sumKToN && holder.contains(sumJToK)) {
                    return true;
                }
            }
            holder.clear();
        }
        return false;
    }
}
