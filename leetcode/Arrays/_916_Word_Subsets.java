package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * You are given two string arrays words1 and words2.
 *
 * A string b is a subset of string a if every letter in b occurs in a including multiplicity.
 *
 * For example, "wrr" is a subset of "warrior" but is not a subset of "world".
 * A string a from words1 is universal if for every string b in words2, b is a subset of a.
 *
 * Return an array of all the universal strings in words1. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
 * Output: ["facebook","google","leetcode"]
 * Example 2:
 *
 * Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
 * Output: ["apple","google","leetcode"]
 *
 *
 * Constraints:
 *
 * 1 <= words1.length, words2.length <= 10^4
 * 1 <= words1[i].length, words2[i].length <= 10
 * words1[i] and words2[i] consist only of lowercase English letters.
 * All the strings of words1 are unique.
 *
 */

public class _916_Word_Subsets {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] maxCount = new int[26];
        // The whole Idea is that is every word in 1 has a char count greater than or equal to the maxCount of word2  then it is contained in all the words1

        for (String word : words2) {
            int[] count = getCount(word);

            for (int i = 0; i < 26; i++) {
                maxCount[i] = Math.max(maxCount[i], count[i]);
            }
        }
        List<String> ans = new ArrayList<>();

        outer :
        for (String word : words1) {
            int[] count = getCount(word);

            for (int i = 0; i < 26; i++) {

                if (maxCount[i] > count[i]) {
                    continue outer;
                }
            }
            ans.add(word);
        }
        return ans;
    }

    private int[] getCount(String word) {
        int[] count = new int[26];

        for (char ch : word.toCharArray()) {
            int pos = ch - 'a';
            count[pos]++;
        }
        return count;
    }
}
