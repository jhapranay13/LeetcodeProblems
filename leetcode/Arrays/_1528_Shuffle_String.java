package leetcode.Arrays;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a string s and an integer array indices of the same length. The string s will be shuffled such that the character at the ith position moves to indices[i] in the shuffled string.
 *
 * Return the shuffled string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s = "codeleet", indices = [4,5,6,7,0,2,1,3]
 * Output: "leetcode"
 * Explanation: As shown, "codeleet" becomes "leetcode" after shuffling.
 * Example 2:
 *
 * Input: s = "abc", indices = [0,1,2]
 * Output: "abc"
 * Explanation: After shuffling, each character remains in its position.
 *
 *
 * Constraints:
 *
 * s.length == indices.length == n
 * 1 <= n <= 100
 * s consists of only lowercase English letters.
 * 0 <= indices[i] < n
 * All values of indices are unique.
 *
 */

public class _1528_Shuffle_String {
    public String restoreString(String s, int[] indices) {
        Set<Integer> touched = new HashSet<>();
        char[] holder = new char[s.length()];

        for (int i = 0; i < indices.length; i++) {
            int index = indices[i];
            char ch = s.charAt(i);

            while (!touched.contains(index)) {
                holder[index] = ch;
                touched.add(index);
                ch = s.charAt(index);
                index = indices[index];
            }
        }
        StringBuilder ans = new StringBuilder();

        for (char ch : holder) {
            ans.append(ch);
        }
        return ans.toString();
    }
}
