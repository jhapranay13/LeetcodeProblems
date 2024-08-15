package leetcode.medium;

/**
 * Given a character array s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.
 *
 * Your code must solve the problem in-place, i.e. without allocating extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: s = ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 * Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 * Example 2:
 *
 * Input: s = ["a"]
 * Output: ["a"]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is an English letter (uppercase or lowercase), digit, or space ' '.
 * There is at least one word in s.
 * s does not contain leading or trailing spaces.
 * All the words in s are guaranteed to be separated by a single space.
 *
 *
 */

public class _186_Reverse_Words_in_a_String_II {
    public void reverseWords(char[] s) {
        reverse(s, 0, s.length - 1);
        int start = 0;

        for (int i = 0; i <= s.length; i++) {

            if (i == s.length || s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
        }
    }

    private void reverse(char[] s, int lo, int hi) {

        while (lo < hi) {
            char temp = s[lo];
            s[lo] = s[hi];
            s[hi] = temp;
            lo++;
            hi--;
        }
    }
}
