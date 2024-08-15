package leetcode.DP.TwoDimension;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * You are given an array of binary strings strs and two integers m and n.
 *
 * Return the size of the largest subset of strs such that there are at most m 0's and n 1's in the subset.
 *
 * A set x is a subset of a set y if all elements of x are also elements of y.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["10","0001","111001","1","0"], m = 5, n = 3
 * Output: 4
 * Explanation: The largest subset with at most 5 0's and 3 1's is {"10", "0001", "1", "0"}, so the answer is 4.
 * Other valid but smaller subsets include {"0001", "1"} and {"10", "1", "0"}.
 * {"111001"} is an invalid subset because it contains 4 1's, greater than the maximum of 3.
 * Example 2:
 *
 * Input: strs = ["10","0","1"], m = 1, n = 1
 * Output: 2
 * Explanation: The largest subset is {"0", "1"}, so the answer is 2.
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 600
 * 1 <= strs[i].length <= 100
 * strs[i] consists only of digits '0' and '1'.
 * 1 <= m, n <= 100
 *
 */

public class _474_Ones_and_Zeroes {
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] memo = new int[m + 1][n + 1][strs.length];
        List<int[]> zeroAndOneCountList = new ArrayList<>();

        for (String str : strs) {
            zeroAndOneCountList.add(getZeroOneCount(str));
        }
        int ans = recur(zeroAndOneCountList, m, n, 0, memo);

        return ans < 0  ? 0 : ans;
    }

    private int recur(List<int[]> zeroAndOneCountList, int m, int n, int index, int[][][] memo) {

        if (m >= 0 && n >= 0 && index >= zeroAndOneCountList.size()) {
            return 0;
        }

        if (m < 0 || n < 0 || index >= zeroAndOneCountList.size()) {
            return Integer.MIN_VALUE;
        }

        if (memo[m][n][index] > 0) {
            return memo[m][n][index];
        }
        int[] zeroOneCount = zeroAndOneCountList.get(index);
        int include = Integer.MIN_VALUE;

        if (m - zeroOneCount[0] >= 0 && n - zeroOneCount[1] >= 0) {
            include = 1 + recur(zeroAndOneCountList, m - zeroOneCount[0], n - zeroOneCount[1], index + 1, memo);
        }
        int exclude = recur(zeroAndOneCountList, m, n, index + 1, memo);
        return memo[m][n][index] = Math.max(include, exclude);
    }

    private int[] getZeroOneCount(String str) {
        int[] zeroOneCount = new int[2];

        for (char ch : str.toCharArray()) {
            zeroOneCount[ch - '0']++;
        }
        return zeroOneCount;
    }
    //=============================================================================================
    //Another approach
    public int findMaxForm1(String[] strs, int m, int n) {
        int[][][] memo = new int[m + 1][n + 1][strs.length];
        int ans = recur(strs, m, n, 0, memo);
        return ans < 0  ? 0 : ans;
    }

    private int recur(String[] strs, int m, int n, int index, int[][][] memo) {

        if (m >= 0 && n >= 0 && index >= strs.length) {
            return 0;
        }

        if (m < 0 || n < 0 || index >= strs.length) {
            return Integer.MIN_VALUE;
        }

        if (strs[index].equals("111001")) {
            System.out.println();
        }
        if (memo[m][n][index] > 0) {
            return memo[m][n][index];
        }
        String str = strs[index];
        int[] zeroOneCount = getZeroOneCount(str);
        int include = Integer.MIN_VALUE;

        if (m - zeroOneCount[0] >= 0 && n - zeroOneCount[1] >= 0) {
            include = 1 + recur(strs, m - zeroOneCount[0], n - zeroOneCount[1], index + 1, memo);
        }
        int exclude = recur(strs, m, n, index + 1, memo);
        return memo[m][n][index] = Math.max(include, exclude);
    }
}
