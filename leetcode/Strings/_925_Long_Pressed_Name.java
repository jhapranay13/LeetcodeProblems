package leetcode.Strings;

/**
 *
 * Your friend is typing his name into a keyboard. Sometimes, when typing a character c, the key might get long pressed, and the character will be typed 1 or more times.
 *
 * You examine the typed characters of the keyboard. Return True if it is possible that it was your friends name, with some characters (possibly none) being long pressed.
 *
 *
 *
 * Example 1:
 *
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 * Example 2:
 *
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it was not in the typed output.
 *
 *
 * Constraints:
 *
 * 1 <= name.length, typed.length <= 1000
 * name and typed consist of only lowercase English letters.
 *
 */

public class _925_Long_Pressed_Name {
    public boolean isLongPressedName(String name, String typed) {
        int indexName = 0;
        int typedIndex = 0;

        while (indexName < name.length() && typedIndex < typed.length()) {
            int count1 = 1;

            while (indexName < name.length() - 1 && name.charAt(indexName) == name.charAt(indexName + 1)) {
                count1++;
                indexName++;
            }
            int count2 = 1;

            while (typedIndex < typed.length() - 1 && typed.charAt(typedIndex) == typed.charAt(typedIndex + 1)) {
                count2++;
                typedIndex++;
            }

            if (name.charAt(indexName) != typed.charAt(typedIndex)) {
                return false;
            }

            if (count1 > count2) {
                return false;
            }
            indexName++;
            typedIndex++;
        }

        if (indexName < name.length() || typedIndex < typed.length()) {
            return false;
        }
        return true;
    }
}
