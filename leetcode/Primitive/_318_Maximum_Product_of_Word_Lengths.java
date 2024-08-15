package leetcode.Primitive;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.
 *
 *
 *
 * Example 1:
 *
 * Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
 * Output: 16
 * Explanation: The two words can be "abcw", "xtfn".
 * Example 2:
 *
 * Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
 * Output: 4
 * Explanation: The two words can be "ab", "cd".
 * Example 3:
 *
 * Input: words = ["a","aa","aaa","aaaa"]
 * Output: 0
 * Explanation: No such pair of words.
 *
 *
 * Constraints:
 *
 * 2 <= words.length <= 1000
 * 1 <= words[i].length <= 1000
 * words[i] consists only of lowercase English letters.
 *
 */

public class _318_Maximum_Product_of_Word_Lengths {
    // can be done using normal method doing with bitmask
    public int maxProduct(String[] words) {
        Map<Integer, Integer> bitLength = new HashMap<>();

        for (String word : words) {
            int bitSet = getBits(word);
            // For cases like ab and aabb. Bit set will be same but will take max length
            bitLength.put(bitSet, Math.max(word.length(), bitLength.getOrDefault(bitSet, 0)));
        }
        int ans = 0;

        for (int bitSet1 : bitLength.keySet()) {

            for (int bitSet2 : bitLength.keySet()) {

                if (bitSet1 == bitSet2) {
                    continue;
                }
                // if no common bit
                if ((bitSet1 & bitSet2) == 0) {
                    ans = Math.max(ans, bitLength.get(bitSet1) * bitLength.get(bitSet2));
                }
            }
        }
        return ans;
    }

    private int getBits(String word) {
        int mask = 0;

        for (char ch : word.toCharArray()) {
            int pos = ch - 'a';
            mask |= 1 << pos;
        }
        return mask;
    }
}
