package leetcode.Strings;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * You are given a string s, an integer k, a letter letter, and an integer repetition.
 *
 * Return the lexicographically smallest subsequence of s of length k that has the letter letter appear at least repetition times. The test cases are generated so that the letter appears in s at least repetition times.
 *
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * A string a is lexicographically smaller than a string b if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "leet", k = 3, letter = "e", repetition = 1
 * Output: "eet"
 * Explanation: There are four subsequences of length 3 that have the letter 'e' appear at least 1 time:
 * - "lee" (from "leet")
 * - "let" (from "leet")
 * - "let" (from "leet")
 * - "eet" (from "leet")
 * The lexicographically smallest subsequence among them is "eet".
 * Example 2:
 *
 * example-2
 *                  e   c   d e
 *              l e e t c o d e
 *
 * Input: s = "leetcode", k = 4, letter = "e", repetition = 2
 * Output: "ecde"
 * Explanation: "ecde" is the lexicographically smallest subsequence of length 4 that has the letter "e" appear at least 2 times.
 * Example 3:
 *
 * Input: s = "bb", k = 2, letter = "b", repetition = 2
 * Output: "bb"
 * Explanation: "bb" is the only subsequence of length 2 that has the letter "b" appear at least 2 times.
 *
 *
 * Constraints:
 *
 * 1 <= repetition <= k <= s.length <= 5 * 104
 * s consists of lowercase English letters.
 * letter is a lowercase English letter, and appears in s at least repetition times.
 *
 */

public class _2030_Smallest_K_Length_Subsequence_With_Occurrences_of_a_Letter {
    public String smallestSubsequence(String s, int k, char letter, int repetition) {
        int letterCount = 0;

        for (char ch : s.toCharArray()) {

            if (ch == letter) {
                letterCount++;
            }
        }
        Deque<Character> monoStack = new LinkedList<>();
        int repeatCanBeDeleted = letterCount - repetition;
        int charCanBeDeleted = s.length() - k;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            while (!monoStack.isEmpty() && ch < monoStack.peek() && charCanBeDeleted > 0) {

                if (monoStack.peek() == letter && repeatCanBeDeleted <= 0) {
                    break;
                }

                if (monoStack.peek() == letter && repeatCanBeDeleted > 0) {
                    repeatCanBeDeleted--;
                }
                charCanBeDeleted--;
                monoStack.pop();
            }
            monoStack.push(ch);
        }
        StringBuilder holder = new StringBuilder();

        while (!monoStack.isEmpty()) {
            char ch = monoStack.poll();

            if (ch == letter) {

                if (charCanBeDeleted > 0) {

                    if (repeatCanBeDeleted > 0) {
                        repeatCanBeDeleted--;
                        charCanBeDeleted--;
                    } else {
                        holder.insert(0, ch);
                    }
                } else {
                    holder.insert(0, ch);
                }
            } else {

                if (charCanBeDeleted > 0) {
                    charCanBeDeleted--;
                } else {
                    holder.insert(0, ch);
                }
            }
        }
        return holder.toString();
    }
}
