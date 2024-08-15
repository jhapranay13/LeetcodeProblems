package leetcode.medium;

/**
 *
 *
 * You are given two 0-indexed strings word1 and word2.
 *
 * A move consists of choosing two indices i and j such that 0 <= i < word1.length and 0 <= j < word2.length and swapping word1[i] with word2[j].
 *
 * Return true if it is possible to get the number of distinct characters in word1 and word2 to be equal with exactly one move. Return false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "ac", word2 = "b"
 * Output: false
 * Explanation: Any pair of swaps would yield two distinct characters in the first string, and one in the second string.
 * Example 2:
 *
 * Input: word1 = "abcc", word2 = "aab"
 * Output: true
 * Explanation: We swap index 2 of the first string with index 0 of the second string. The resulting strings are word1 = "abac" and word2 = "cab", which both have 3 distinct characters.
 * Example 3:
 *
 * Input: word1 = "abcde", word2 = "fghij"
 * Output: true
 * Explanation: Both resulting strings will have 5 distinct characters, regardless of which indices we swap.
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 10^5
 * word1 and word2 consist of only lowercase English letters.
 *
 *
 */

public class _2531_Make_Number_of_Distinct_Characters_Equal {
    public boolean isItPossible(String word1, String word2) {
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];

        for (char ch : word1.toCharArray()) {
            freq1[ch - 'a']++;
        }

        for (char ch : word2.toCharArray()) {
            freq2[ch - 'a']++;
        }

        for (int i = 0; i < 26; i++) {

            if (freq1[i] == 0) {
                continue;
            }

            for (int j = 0; j < 26; j++) {

                if (freq2[j] == 0) {
                    continue;
                }
                //swapping one char
                freq1[i]--;
                freq2[j]--;
                freq1[j]++;
                freq2[i]++;

                if (check(freq1, freq2)) {
                    return true;
                }
                freq1[j]--;
                freq2[i]--;
                freq1[i]++;
                freq2[j]++;
            }
        }
        return false;
    }

    private boolean check(int[] freq1, int[] freq2) {
        int count1 = 0;
        int count2 = 0;

        for (int freq : freq1) {

            if (freq > 0) {
                count1++;
            }
        }

        for (int freq : freq2) {

            if (freq > 0) {
                count2++;
            }
        }
        return count1 == count2;
    }
}
