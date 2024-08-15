package leetcode.Strings;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums. If there are multiple answers, you may return any of them.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = ["01","10"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "00" would also be correct.
 * Example 2:
 *
 * Input: nums = ["00","01"]
 * Output: "11"
 * Explanation: "11" does not appear in nums. "10" would also be correct.
 * Example 3:
 *
 * Input: nums = ["111","011","001"]
 * Output: "101"
 * Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 16
 * nums[i].length == n
 * nums[i] is either '0' or '1'.
 * All the strings of nums are unique.
 */

public class _1980_Find_Unique_Binary_String {
    public String findDifferentBinaryString(String[] nums) {
        Set<String> numset = new HashSet<>();

        for (String num : nums) {
            numset.add(num);
        }
        return recur(numset, nums[0].length(), new StringBuilder());
    }

    private String recur(Set<String> numset, int length, StringBuilder holder) {

        if (length == holder.length()) {

            if (!numset.contains(holder.toString())) {
                return holder.toString();
            }
            return "";
        }
        holder.append("0");
        String ans = recur(numset, length, holder);
        holder.deleteCharAt(holder.length() - 1);

        if (ans.length() > 0) {
            return ans;
        }
        holder.append("1");
        ans = recur(numset, length, holder);
        holder.deleteCharAt(holder.length() - 1);
        return ans;
    }
}
