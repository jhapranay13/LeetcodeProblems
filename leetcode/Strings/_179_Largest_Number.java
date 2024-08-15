package leetcode.Strings;

import java.util.Arrays;

/**
 *
 * Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
 *
 * Since the result may be very large, so you need to return a string instead of an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [10,2]
 * Output: "210"
 * Example 2:
 *
 * Input: nums = [3,30,34,5,9]
 * Output: "9534330"
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 10^9
 *
 */

public class _179_Largest_Number {
    public String largestNumber(int[] nums) {
        String[] holder = new String[nums.length];
        int index = 0;

        for (int num : nums) {
            holder[index++] = "" + num;
        }
        Arrays.sort(holder, (a, b) -> {
            String str1 = a + b;
            String str2 = b + a;
            return str2.compareTo(str1);
        });
        // If Heighest number is 0 return 0;
        if (holder[0].equals("0")) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();

        for (String str : holder) {
            ans.append(str);
        }
        return ans.toString();
    }
}
