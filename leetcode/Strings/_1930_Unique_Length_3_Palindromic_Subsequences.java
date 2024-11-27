package leetcode.Strings;

import java.util.*;

/**
 *
 *
 * Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
 *
 * Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
 *
 * A palindrome is a string that reads the same forwards and backwards.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 *
 *
 * Example 1:
 *
 * Input: s = "aabca"
 * Output: 3
 * Explanation: The 3 palindromic subsequences of length 3 are:
 * - "aba" (subsequence of "aabca")
 * - "aaa" (subsequence of "aabca")
 * - "aca" (subsequence of "aabca")
 * Example 2:
 *
 * Input: s = "adc"
 * Output: 0
 * Explanation: There are no palindromic subsequences of length 3 in "adc".
 * Example 3:
 *
 * Input: s = "bbcbaba"
 * Output: 4
 * Explanation: The 4 palindromic subsequences of length 3 are:
 * - "bbb" (subsequence of "bbcbaba")
 * - "bcb" (subsequence of "bbcbaba")
 * - "bab" (subsequence of "bbcbaba")
 * - "aba" (subsequence of "bbcbaba")
 *
 *
 * Constraints:
 *
 * 3 <= s.length <= 105
 * s consists of only lowercase English letters.
 *
 *
 */

public class _1930_Unique_Length_3_Palindromic_Subsequences {
    public int countPalindromicSubsequence(String s) {
        Map<Character, List<Integer>> posMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            List<Integer> list = posMap.getOrDefault(ch, new ArrayList<>());
            list.add(i);
            posMap.put(ch, list);
        }
        int ans = 0;

        for (Map.Entry<Character, List<Integer>> entry : posMap.entrySet()) {
            List<Integer> list = entry.getValue();

            if (list.size() > 0 && list.get(list.size() - 1) - list.get(0) >= 2) {
                Set<Character> holder = new HashSet<>();
                int start = list.get(0) + 1;
                int end = list.get(list.size() - 1);

                for (;start < end; start++) {
                    holder.add(s.charAt(start));
                }

                ans +=  holder.size();
            }
        }
        return ans;
    }
}
