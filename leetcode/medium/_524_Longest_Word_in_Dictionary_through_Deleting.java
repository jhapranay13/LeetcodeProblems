package leetcode.medium;

import java.util.List;

/**
 *
 * Given a string s and a string array dictionary, return the longest string in the dictionary that can be formed by deleting some of the given string characters. If there is more than one possible result, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * Output: "apple"
 * Example 2:
 *
 * Input: s = "abpcplea", dictionary = ["a","b","c"]
 * Output: "a"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s and dictionary[i] consist of lowercase English letters.
 *
 */

public class _524_Longest_Word_in_Dictionary_through_Deleting {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";

        for (String word : dictionary) {

            if (check(s, word)) {

                if (ans.length() < word.length()) {
                    ans = word;
                } else if (ans.length() == word.length()) {

                    if (ans.compareTo(word) > 0) {
                        ans = word;
                    }
                }
            }
        }
        return ans;
    }

    private boolean check(String s, String word) {
        int j = 0;

        for (int i = 0; i < s.length() && j < word.length(); i++) {

            if (s.charAt(i) == word.charAt(j)) {
                j++;
            }
        }
        return j == word.length();
    }

}
