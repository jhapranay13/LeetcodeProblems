package leetcode.easy;

/**
 *
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 *
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "USA"
 * Output: true
 * Example 2:
 *
 * Input: word = "FlaG"
 * Output: false
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 100
 * word consists of lowercase and uppercase English letters.
 *
 */

public class _520_Detect_Capital {
    public boolean detectCapitalUse(String word) {

        if (word.length() == 1) {
            return true;
        }
        int firstSmallerIndex = -1;
        int lastCapitalIndex = -1;
        int index = 0;

        for (char ch : word.toCharArray()) {

            if (Character.isLowerCase(ch) && firstSmallerIndex == -1) {
                firstSmallerIndex = index;
            }

            if (Character.isUpperCase(ch)) {
                lastCapitalIndex = index;
            }
            index++;
        }

        if (firstSmallerIndex == -1 && lastCapitalIndex == -1) {
            return true;
        }

        if ((firstSmallerIndex != -1 && lastCapitalIndex != -1 && firstSmallerIndex < lastCapitalIndex) ||
                (firstSmallerIndex != -1 && lastCapitalIndex != -1 && lastCapitalIndex != 0)) {
            return false;
        }
        return true;
    }
}
