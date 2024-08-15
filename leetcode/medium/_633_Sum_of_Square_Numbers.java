package leetcode.medium;

/**
 *
 * Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
 *
 *
 *
 * Example 1:
 *
 * Input: c = 5
 * Output: true
 * Explanation: 1 * 1 + 2 * 2 = 5
 * Example 2:
 *
 * Input: c = 3
 * Output: false
 *
 *
 * Constraints:
 *
 * 0 <= c <= 2^31 - 1
 *
 */

public class _633_Sum_of_Square_Numbers {
    public boolean judgeSquareSum(int c) {

        for (long i = 0; i * i <= c; i++) {
            int target = c - (int)(i * i);

            if (binarySearch(0, i * i, target)) {
                return true;
            }
        }
        return false;
    }

    private boolean binarySearch(long lo, long hi, int target) {

        while (lo <= hi) {
            long pivot = lo + (hi - lo) / 2;
            long val = (pivot * pivot);

            if (val == target) {
                return true;
            }

            if (val < target) {
                lo = pivot + 1;
            } else {
                hi = pivot -1;
            }
        }
        return false;
    }
}
