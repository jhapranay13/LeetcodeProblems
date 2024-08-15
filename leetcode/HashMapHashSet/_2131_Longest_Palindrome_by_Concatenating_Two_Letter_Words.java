package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are given an array of strings words. Each element of words consists of two lowercase English letters.
 *
 * Create the longest possible palindrome by selecting some elements from words and concatenating them in any order. Each element can be selected at most once.
 *
 * Return the length of the longest palindrome that you can create. If it is impossible to create any palindrome, return 0.
 *
 * A palindrome is a string that reads the same forward and backward.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["lc","cl","gg"]
 * Output: 6
 * Explanation: One longest palindrome is "lc" + "gg" + "cl" = "lcggcl", of length 6.
 * Note that "clgglc" is another longest palindrome that can be created.
 * Example 2:
 *
 * Input: words = ["ab","ty","yt","lc","cl","ab"]
 * Output: 8
 * Explanation: One longest palindrome is "ty" + "lc" + "cl" + "yt" = "tylcclyt", of length 8.
 * Note that "lcyttycl" is another longest palindrome that can be created.
 * Example 3:
 *
 * Input: words = ["cc","ll","xx"]
 * Output: 2
 * Explanation: One longest palindrome is "cc", of length 2.
 * Note that "ll" is another longest palindrome that can be created, and so is "xx".
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 10^5
 * words[i].length == 2
 * words[i] consists of lowercase English letters.
 *
 */

public class _2131_Longest_Palindrome_by_Concatenating_Two_Letter_Words {
    public int longestPalindrome(String[] words) {
        Map<String, Integer> freq = new HashMap<>();
        int ans = 0;

        for (String word : words) {
            String revWord = "" + word.charAt(1) + word.charAt(0);

            if (freq.containsKey(revWord)) {
                int frq = freq.get(revWord);
                ans += 4;

                if (frq == 1) {
                    freq.remove(revWord);
                } else {
                    freq.put(revWord, frq - 1);
                }
            } else {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        }
        // Since all the palindromic word will be eliminated by the above step we can get one more
        // Palindromic word if it exists

        for (String word : freq.keySet()) {

            if (word.charAt(1) == word.charAt(0)) {
                ans += 2;
                break;
            }
        }
        return ans;
    }
}
