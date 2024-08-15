package leetcode.SlidingWindow;

/**
 *
 * You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:
 *
 * Type-1: Remove the character at the start of the string s and append it to the end of the string.
 * Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
 * Return the minimum number of type-2 operations you need to perform such that s becomes alternating.
 *
 * The string is called alternating if no two adjacent characters are equal.
 *
 * For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 *
 *
 * Example 1:
 *
 * Input: s = "111000"
 * Output: 2
 * Explanation: Use the first operation two times to make s = "100011".
 * Then, use the second operation on the third and sixth elements to make s = "101010".
 * Example 2:
 *
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating.
 * Example 3:
 *
 * Input: s = "1110"
 * Output: 1
 * Explanation: Use the second operation on the second element to make s = "1010".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s[i] is either '0' or '1'.
 *
 */

public class _1888_Minimum_Number_of_Flips_to_Make_the_Binary_String_Alternating {
    public int minFlips(String s) {
        //Adding it twice to simulate type 1
        StringBuilder holder = new StringBuilder(s);
        holder.append(s);
        StringBuilder flip1 = new StringBuilder();
        StringBuilder flip2 = new StringBuilder();
        // Creating 2 type of flip alternating i.e. 010101.. and 101010...
        for (int i = 0; i < holder.length(); i++) {

            if (i % 2 == 0) {
                flip1.append("0");
                flip2.append("1");
            } else {
                flip1.append("1");
                flip2.append("0");
            }
        }
        int window = s.length();
        int fast = 0;
        int slow = 0;
        int diff1 = 0;
        int diff2 = 0;
        int ans = Integer.MAX_VALUE;

        while (fast < holder.length()) {

            if (holder.charAt(fast) != flip1.charAt(fast)) {
                diff1++;
            }

            if (holder.charAt(fast) != flip2.charAt(fast)) {
                diff2++;
            }

            if (fast - slow + 1 > window) {
                if (holder.charAt(slow) != flip1.charAt(slow)) {
                    diff1--;
                }

                if (holder.charAt(slow) != flip2.charAt(slow)) {
                    diff2--;
                }
                slow++;
            }

            if (fast - slow + 1 == window) {
                ans = Math.min(ans, Math.min(diff1, diff2));
            }
            fast++;
        }
        return ans;
    }
}
