package leetcode.Math;

/**
 * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
 *
 *
 *
 * Example 1:
 *
 * Input: low = 3, high = 7
 * Output: 3
 * Explanation: The odd numbers between 3 and 7 are [3,5,7].
 * Example 2:
 *
 * Input: low = 8, high = 10
 * Output: 1
 * Explanation: The odd numbers between 8 and 10 are [9].
 *
 *
 * Constraints:
 *
 * 0 <= low <= high <= 10^9
 *
 */

public class _1523_Count_Odd_Numbers_in_an_Interval_Range {
    // The idea is if there are 10 number ten mostly it would be 10 / 2 od number and then we can
    // include and exclude the current number
    public int countOdds(int low, int high) {
        int start = low % 2 == 0 ? low + 1 : low;
        int end = high % 2 == 0 ? high - 1 : high;
        int ans = 0;

        if (start < end && start % 2 != 0) {
            ans++;
        }

        if (end % 2 != 0) {
            ans++;
        }
        int diff = end - start - 1;
        ans += diff / 2;
        return ans;
    }
}
