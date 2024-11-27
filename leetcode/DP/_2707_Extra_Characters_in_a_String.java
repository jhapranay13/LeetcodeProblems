package leetcode.DP;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
 *
 * Return the minimum number of extra characters left over if you break up s optimally.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
 * Output: 1
 * Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.
 *
 * Example 2:
 *
 * Input: s = "sayhelloworld", dictionary = ["hello","world"]
 * Output: 3
 * Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] and s consists of only lowercase English letters
 * dictionary contains distinct words
 *
 *
 */

public class _2707_Extra_Characters_in_a_String {
    // Top down
    public int minExtraChar(String s, String[] dictionary) {
        Set<String> dict = new HashSet<>();

        for (String word : dictionary) {
            dict.add(word);
        }
        int[] memo = new int[s.length()];
        Arrays.fill(memo, -1);
        return recur(s, dict, 0, memo);
    }

    private int recur(String s, Set<String> dict, int index, int[] memo) {

        if (index == s.length()) {
            return 0;
        }

        if (memo[index] != -1) {
            return memo[index];
        }
        // Skipping this so adding it to skip
        int ans = 1 + recur(s, dict, index + 1, memo);

        for (int i = index; i < s.length(); i++) {
            String str = s.substring(index, i + 1);
            int countNotUsed = 0;

            if (!dict.contains(str)) {
                countNotUsed = str.length();
            }
            ans = Math.min(ans, countNotUsed + recur(s, dict, i + 1, memo));
        }
        return memo[index] = ans;
    }
    //=============================================================================================
    // Bottom up
    public int minExtraChar1(String s, String[] dictionary) {
        Set<String> dict = new HashSet<>();

        for (String word : dictionary) {
            dict.add(word);
        }
        int[] memo = new int[s.length() + 1];
        Arrays.fill(memo, -1);
        memo[s.length()] = 0;

        for (int index = s.length() - 1; index >= 0; index--) {
            int ans = 1 + memo[index + 1];

            for (int i = index; i < s.length(); i++) {
                String str = s.substring(index, i + 1);
                int countNotUsed = 0;

                if (!dict.contains(str)) {
                    countNotUsed = str.length();
                }
                ans = Math.min(ans, countNotUsed + memo[i + 1]);
            }
            memo[index] = ans;
        }
        return memo[0];
    }
}
