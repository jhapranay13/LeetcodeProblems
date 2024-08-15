package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string s, return true if a permutation of the string could form a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "code"
 * Output: false
 * Example 2:
 *
 * Input: s = "aab"
 * Output: true
 * Example 3:
 *
 * Input: s = "carerac"
 * Output: true
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 5000
 * s consists of only lowercase English letters.
 *
 */

public class _266_Palindrome_Permutation {
    public boolean canPermutePalindrome(String s) {
        Map<Character, Integer> countMap = new HashMap<>();

        for (char ch : s.toCharArray()) {
            countMap.put(ch, countMap.getOrDefault(ch, 0) + 1);
        }
        int countOdd = 0;

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            countOdd += entry.getValue() % 2 == 1 ? 1 : 0;

            if (countOdd > 1) {
                return false;
            }
        }
        return true;
    }
}
