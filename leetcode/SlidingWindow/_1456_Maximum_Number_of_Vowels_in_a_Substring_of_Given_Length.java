package leetcode.SlidingWindow;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
 *
 * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abciiidef", k = 3
 * Output: 3
 * Explanation: The substring "iii" contains 3 vowel letters.
 * Example 2:
 *
 * Input: s = "aeiou", k = 2
 * Output: 2
 * Explanation: Any substring of length 2 contains 2 vowels.
 * Example 3:
 *
 * Input: s = "leetcode", k = 3
 * Output: 2
 * Explanation: "lee", "eet" and "ode" contain 2 vowels.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of lowercase English letters.
 * 1 <= k <= s.length
 *
 *
 */

public class _1456_Maximum_Number_of_Vowels_in_a_Substring_of_Given_Length {
    public int maxVowels(String s, int k) {
        Set<Character> holder = new HashSet<>();
        holder.add('a');
        holder.add('e');
        holder.add('i');
        holder.add('o');
        holder.add('u');
        int slow = 0;
        int fast = 0;
        int ans = 0;
        int count = 0;

        while (fast < s.length()) {
            char fch = s.charAt(fast);

            if (holder.contains(fch)) {
                count++;
            }
            ans = Math.max(ans, count);

            if (fast - slow + 1 == k) {
                char sch = s.charAt(slow++);

                if (holder.contains(sch)) {
                    count--;
                }
            }
            fast++;
        }
        return ans;
    }
}
