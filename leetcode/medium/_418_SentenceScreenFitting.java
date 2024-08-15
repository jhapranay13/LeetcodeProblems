package leetcode.medium;

/**
 *
 * Given a rows x cols screen and a sentence represented as a list of strings, return the number of times the given sentence can be fitted on the screen.
 *
 * The order of words in the sentence must remain unchanged, and a word cannot be split into two lines. A single space must separate two consecutive words in a line.
 *
 *
 *
 * Example 1:
 *
 * Input: sentence = ["hello","world"], rows = 2, cols = 8
 * Output: 1
 * Explanation:
 * hello---
 * world---
 * The character '-' signifies an empty space on the screen.
 * Example 2:
 *
 * Input: sentence = ["a", "bcd", "e"], rows = 3, cols = 6
 * Output: 2
 * Explanation:
 * a-bcd-
 * e-a---
 * bcd-e-
 * The character '-' signifies an empty space on the screen.
 * Example 3:
 *
 * Input: sentence = ["i","had","apple","pie"], rows = 4, cols = 5
 * Output: 1
 * Explanation:
 * i-had
 * apple
 * pie-i
 * had--
 * The character '-' signifies an empty space on the screen.
 *
 *
 * Constraints:
 *
 * 1 <= sentence.length <= 100
 * 1 <= sentence[i].length <= 10
 * sentence[i] consists of lowercase English letters.
 * 1 <= rows, cols <= 2 * 10^4
 */

public class _418_SentenceScreenFitting {

    public int wordsTyping(String[] sentence, int rows, int cols) {
        int ans = 0;
        int index = 0;
        int c = cols;
        int r = 0;

        for (String w : sentence) {
            if (w.length() > cols) {
                return 0;
            }
        }

        while (r < rows) {
            int sentenceLength = sentence[index].length();
            c = c == cols ? c : c - 1; //accounting for the space;

            if (sentenceLength <= c) {
                c -= sentenceLength;
                index++;
            } else {
                r++;
                c = cols;
            }

            if (index == sentence.length) {
                index = 0;
                ans++;
            }
        }
        return ans;
    }
}
