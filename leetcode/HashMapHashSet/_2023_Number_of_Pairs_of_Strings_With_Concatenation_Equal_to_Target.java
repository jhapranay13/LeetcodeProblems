package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given an array of digit strings nums and a digit string target, return the number of pairs of indices (i, j) (where i != j) such that the concatenation of nums[i] + nums[j] equals target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["777","7","77","77"], target = "7777"
 * Output: 4
 * Explanation: Valid pairs are:
 * - (0, 1): "777" + "7"
 * - (1, 0): "7" + "777"
 * - (2, 3): "77" + "77"
 * - (3, 2): "77" + "77"
 * Example 2:
 *
 * Input: nums = ["123","4","12","34"], target = "1234"
 * Output: 2
 * Explanation: Valid pairs are:
 * - (0, 1): "123" + "4"
 * - (2, 3): "12" + "34"
 * Example 3:
 *
 * Input: nums = ["1","1","1"], target = "11"
 * Output: 6
 * Explanation: Valid pairs are:
 * - (0, 1): "1" + "1"
 * - (1, 0): "1" + "1"
 * - (0, 2): "1" + "1"
 * - (2, 0): "1" + "1"
 * - (1, 2): "1" + "1"
 * - (2, 1): "1" + "1"
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 100
 * 1 <= nums[i].length <= 100
 * 2 <= target.length <= 100
 * nums[i] and target consist of digits.
 * nums[i] and target do not have leading zeros.
 *
 */

public class _2023_Number_of_Pairs_of_Strings_With_Concatenation_Equal_to_Target {
    public int numOfPairs(String[] nums, String target) {
        Map<String, Integer> freq = new HashMap<>();

        for (String num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = 0;

        for (int i = 0; i < target.length(); i++) {
            String prefix = target.substring(0, i + 1);
            String sufix = target.substring(i + 1);
            int count1 = freq.getOrDefault(prefix, 0);
            int count2 = freq.getOrDefault(sufix, 0);

            if (prefix.equals(sufix)) {
                ans += count1 * (count1 - 1); // Number of combination formula if values are same
            } else {
                ans += count1 * count2;
            }
        }
        return ans;
    }
    
}
