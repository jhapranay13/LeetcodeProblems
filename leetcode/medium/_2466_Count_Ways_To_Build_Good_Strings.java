package leetcode.medium;

import java.util.Arrays;

/**
 *
 *
 * Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:
 *
 * Append the character '0' zero times.
 * Append the character '1' one times.
 * This can be performed any number of times.
 *
 * A good string is a string constructed by the above process having a length between low and high (inclusive).
 *
 * Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: low = 3, high = 3, zero = 1, one = 1
 * Output: 8
 * Explanation:
 * One possible valid good string is "011".
 * It can be constructed as follows: "" -> "0" -> "01" -> "011".
 * All binary strings from "000" to "111" are good strings in this example.
 * Example 2:
 *
 * Input: low = 2, high = 3, zero = 1, one = 2
 * Output: 5
 * Explanation: The good strings are "00", "11", "000", "110", and "011".
 *
 *
 * Constraints:
 *
 * 1 <= low <= high <= 10^5
 * 1 <= zero, one <= low
 *
 */

public class _2466_Count_Ways_To_Build_Good_Strings {
    private int mod = 1000000007;

    public int countGoodStrings(int low, int high, int zero, int one) {
        int ans = 0;
        int[] memo = new int[high + 1];
        Arrays.fill(memo, -1);
        memo[0] = 1;

        for (int i = low; i <= high; i++) {
            ans += recur(zero, one, i, memo);
            ans %= mod;
        }
        return ans;
    }

    private int recur(int zero, int one, int length, int[] memo) {

        if (length == 0) {
            return 1;
        }

        if (memo[length] > -1) {
            return memo[length];
        }
        int count = 0;

        if (length >= one) {
            count += recur(zero, one, length - one, memo);
        }
        count %= mod;

        if (length >= zero) {
            count += recur(zero, one, length - zero, memo);
        }
        count %= mod;
        return memo[length] = count;
    }
}
