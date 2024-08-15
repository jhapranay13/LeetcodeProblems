package leetcode.DP;

import java.util.HashMap;
import java.util.Map;

/**
 * The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.
 *
 * Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbb"
 * Output: 3
 * Explanation:
 * All possible variances along with their respective substrings are listed below:
 * - Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
 * - Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
 * - Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
 * - Variance 3 for substring "babbb".
 * Since the largest possible variance is 3, we return it.
 * Example 2:
 *
 * Input: s = "abcde"
 * Output: 0
 * Explanation:
 * No letter occurs more than once in s, so the variance of every substring is 0.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of lowercase English letters.
 *
 */

public class _2272_Substring_With_Largest_Variance {
    public int largestVariance(String s) {
        Map<Character, Integer> charCount = new HashMap<>();

        for (char ch : s.toCharArray()) {
            int freq = charCount.getOrDefault(ch, 0);
            charCount.put(ch, freq + 1);
        }
        int ans = 0;
        //Sort of Kadane answer is global and c1Freq anf c2Freq are local
        //Assuming c2 is greater than c1 always
        for (char c1 : charCount.keySet()) {

            for (char c2 : charCount.keySet()) {

                if (c1 == c2) {
                    continue;
                }
                int c1Freq = 0;
                int c2Freq = 0;
                int remainingC1 = charCount.get(c1);

                for (char c : s.toCharArray()) {

                    if (c == c1) {
                        c1Freq++;
                        remainingC1--;
                    }

                    if (c == c2) {
                        c2Freq++;
                    }

                    if (c2Freq < c1Freq && remainingC1 > 0) {
                        c1Freq = 0;
                        c2Freq = 0;
                    }

                    if (c1Freq > 0 && c2Freq > 0) {
                        ans = Math.max(ans, c2Freq - c1Freq);
                    }
                }
            }
        }
        return ans;
    }
}
