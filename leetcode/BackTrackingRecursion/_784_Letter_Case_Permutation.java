package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given a string s, you can transform every letter individually to be lowercase or uppercase to create another string.
 *
 * Return a list of all possible strings we could create. Return the output in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "a1b2"
 * Output: ["a1b2","a1B2","A1b2","A1B2"]
 * Example 2:
 *
 * Input: s = "3z4"
 * Output: ["3z4","3Z4"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 12
 * s consists of lowercase English letters, uppercase English letters, and digits.
 *
 */

public class _784_Letter_Case_Permutation {
    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        recur(ans, new StringBuilder(), s, 0);
        return ans;
    }

    private void recur(List<String> ans, StringBuilder holder, String s, int index) {

        if (index == s.length()) {
            ans.add(holder.toString());
            return;
        }

        if (Character.isAlphabetic(s.charAt(index))) {
            holder.append(Character.toLowerCase(s.charAt(index)));
            recur(ans, holder, s, index + 1);
            holder.deleteCharAt(holder.length() - 1);
            holder.append(Character.toUpperCase(s.charAt(index)));
            recur(ans, holder, s, index + 1);
            holder.deleteCharAt(holder.length() - 1);
        } else {
            holder.append(s.charAt(index));
            recur(ans, holder, s, index + 1);
            holder.deleteCharAt(holder.length() - 1);
        }
    }

}
