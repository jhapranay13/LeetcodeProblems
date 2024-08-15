package leetcode.Strings;

/**
 *
 * Given a string s, reverse the string according to the following rules:
 *
 * All the characters that are not English letters remain in the same position.
 * All the English letters (lowercase or uppercase) should be reversed.
 * Return s after reversing it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ab-cd"
 * Output: "dc-ba"
 * Example 2:
 *
 * Input: s = "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 * Example 3:
 *
 * Input: s = "Test1ng-Leet=code-Q!"
 * Output: "Qedo1ct-eeLg=ntse-T!"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 100
 * s consists of characters with ASCII values in the range [33, 122].
 * s does not contain '\"' or '\\'.
 *
 */

public class _917_Reverse_Only_Letters {
    public String reverseOnlyLetters(String s) {
        int lo = 0;
        int hi = s.length() - 1;
        char[] arr = s.toCharArray();

        while (lo < hi) {

            while (lo < hi && !Character.isAlphabetic(arr[lo])) {
                lo++;
            }

            while (lo < hi && !Character.isAlphabetic(arr[hi])) {
                hi--;
            }
            char temp = arr[lo];
            arr[lo++] = arr[hi];
            arr[hi--] = temp;
        }
        return new String(arr);
    }
}
