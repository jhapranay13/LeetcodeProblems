package leetcode.Primitive;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * A wonderful string is a string where at most one letter appears an odd number of times.
 *
 * For example, "ccjjc" and "abab" are wonderful, but "ab" is not.
 * Given a string word that consists of the first ten lowercase English letters ('a' through 'j'), return the number of wonderful non-empty substrings in word. If the same substring appears multiple times in word, then count each occurrence separately.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "aba"
 * Output: 4
 * Explanation: The four wonderful substrings are underlined below:
 * - "aba" -> "a"
 * - "aba" -> "b"
 * - "aba" -> "a"
 * - "aba" -> "aba"
 * Example 2:
 *
 * Input: word = "aabb"
 * Output: 9
 * Explanation: The nine wonderful substrings are underlined below:
 * - "aabb" -> "a"
 * - "aabb" -> "aa"
 * - "aabb" -> "aab"
 * - "aabb" -> "aabb"
 * - "aabb" -> "a"
 * - "aabb" -> "abb"
 * - "aabb" -> "b"
 * - "aabb" -> "bb"
 * - "aabb" -> "b"
 * Example 3:
 *
 * Input: word = "he"
 * Output: 2
 * Explanation: The two wonderful substrings are underlined below:
 * - "he" -> "h"
 * - "he" -> "e"
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 10^5
 * word consists of lowercase English letters from 'a' to 'j'.
 *
 *
 */

public class _1915_Number_of_Wonderful_Substrings {
    public long wonderfulSubstrings(String word) {
        Map<Integer, Long> maskCount = new HashMap<>();
        maskCount.put(0, 1L);
        long ans = 0;
        int mask = 0;

        for (char ch : word.toCharArray()) {
            int charNum = ch - 'a';
            //counting when all are even
            mask = mask ^ (1 << charNum);
            ans += maskCount.getOrDefault(mask, 0L);
            // counting odd
            // if we have just one odd so it should be just one flip
            // from even number
            // i < 10 coz chars are a to j which are 10 chars
            for (int i = 0; i < 10; i++) {
                int msk = mask ^ (1 << i);
                ans += maskCount.getOrDefault(msk, 0L);
            }
            maskCount.put(mask, maskCount.getOrDefault(mask, 0L) + 1L);
        }
        return ans;
    }
}
