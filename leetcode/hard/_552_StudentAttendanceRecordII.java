package leetcode.hard;

/**
 *
 * An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:
 *
 * 'A': Absent.
 * 'L': Late.
 * 'P': Present.
 * Any student is eligible for an attendance award if they meet both of the following criteria:
 *
 * The student was absent ('A') for strictly fewer than 2 days total.
 * The student was never late ('L') for 3 or more consecutive days.
 * Given an integer n, return the number of possible attendance records of length n that make a student
 * eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 8
 * Explanation: There are 8 records with length 2 that are eligible for an award:
 * "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 * Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
 * Example 2:
 *
 * Input: n = 1
 * Output: 3
 * Example 3:
 *
 * Input: n = 10101
 * Output: 183236316
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 *
 */

public class _552_StudentAttendanceRecordII {
    //=============================================================================================
    //Top down approach
    public int checkRecord(int n) {
        Long[][][] memo = new Long[n][2][3];
        return (int)recur(n, 0, 0, 0, memo);
    }
    private final int MOD = 1000000007;

    private long recur(int n, int curr, int absent, int late, Long[][][] memo) {
        if (absent == 2 || late == 3) {
            return 0;
        }

        if (n == curr) {
            return 1;
        }

        if (memo[curr][absent][late] != null) {
            return memo[curr][absent][late];
        }
        long pr = recur(n, curr + 1, absent, 0, memo);
        long la = recur(n, curr + 1, absent, late + 1, memo);
        long ab = recur(n, curr + 1, absent + 1, 0, memo);
        return memo[curr][absent][late] = (pr + la + ab) % MOD;
    }
}
