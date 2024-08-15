package leetcode.TwoPointer;

/**
 *
 * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
 *
 * If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then reverse the first k characters and leave the other as original.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * Example 2:
 *
 * Input: s = "abcd", k = 2
 * Output: "bacd"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^4
 *
 */

public class _541_Reverse_String_II {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();

        for (int i = 0; i < s.length(); i += 2 * k) {
            int lo = i;
            int hi = Math.min(i + k - 1, s.length() - 1);

            while (lo <= hi) {
                char temp = arr[lo];
                arr[lo++] = arr[hi];
                arr[hi--] = temp;
            }
        }
        return new String(arr);
    }
}
