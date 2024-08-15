package leetcode.StackAndQueues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * You are given a binary string s and a positive integer k.
 *
 * Return the length of the longest subsequence of s that makes up a binary number less than or equal to k.
 *
 * Note:
 *
 * The subsequence can contain leading zeroes.
 * The empty string is considered to be equal to 0.
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 *
 * Example 1:
 *
 * Input: s = "1001010", k = 5
 * Output: 5
 * Explanation: The longest subsequence of s that makes up a binary number less than or equal to 5 is "00010", as this number is equal to 2 in decimal.
 * Note that "00100" and "00101" are also possible, which are equal to 4 and 5 in decimal, respectively.
 * The length of this subsequence is 5, so 5 is returned.
 * Example 2:
 *
 * Input: s = "00101001", k = 1
 * Output: 6
 * Explanation: "000001" is the longest subsequence of s that makes up a binary number less than or equal to 1, as this number is equal to 1 in decimal.
 * The length of this subsequence is 6, so 6 is returned.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s[i] is either '0' or '1'.
 * 1 <= k <= 10^9
 *
 */

public class _2311_Longest_Binary_Subsequence_Less_Than_or_Equal_to_K {
    //Similar To LIS
    public int longestSubsequence(String s, int k) {

        if (s.length() == 1) {

            if (s.charAt(0) == '0') {
                return 1;
            }
        }
        int max = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int calc = (int) Math.pow(2, s.length() - 1 - i);

            if (s.charAt(i) == '1') {
                max += calc;
            }
        }
        int[] memo = new int[s.length()];
        return recur(s, k, s.length() - 1, 0, memo);
    }

    private int recur(String s, int k, int index, int sum, int[] memo) {

        if (index < 0) {
            return 0;
        }

        if (memo[index] != 0) {
            return memo[index];
        }
        int ans = 0;

        if (s.charAt(index) == '0') {
            ans = 1 + recur(s, k, index - 1, sum, memo);
        } else if (s.charAt(index) == '1') {

            if (sum <= k) {
                double power = Math.pow(2, (s.length() - index - 1));

                if (sum + power <= k) {
                    ans = 1 + recur(s, k, index - 1, (int)(sum + power), memo);
                }
            }
            ans = Math.max(ans, recur(s, k, index - 1, sum, memo));
        }
        return memo[index] = ans;
    }
    //=============================================================================================
    //Different Solution More like fuel station Problem Top Down Approach
    public int longestSubsequence1(String s, int k) {

        if (s.length() == 1) {

            if (s.charAt(0) == '0') {
                return 1;
            }
        }
        double[][] memo = new double[s.length()][2];
        int firstPass = (int)recur(s, k, 0, memo)[1];
        int secondPass = (int)recur(s, k, 1, memo)[1];
        return Math.max(firstPass, secondPass);
    }

    private double[] recur(String s, int k, int index, double[][] memo) {

        if (index >= s.length()) {
            return new double[] {0, 0};
        }

        if (memo[index][1] > 0) {
            return memo[index];
        }
        double[] include = recur(s, k, index + 1, memo);
        double[] ignore = recur(s, k, index + 2, memo);

        double calculationInc = 0;
        double calculationIgn = 0;

        if (s.charAt(index) == '1') {
            calculationInc = Math.pow(2, include[1]);
            calculationIgn = Math.pow(2, ignore[1]);
        }
        double[] ans = {0, 1};

        if (include[0] + calculationInc <= k) {
            ans[0] = include[0] + calculationInc;
            ans[1] += include[1];
        } else {
            ans[0] = include[0];
            ans[1] = include[1];
        }

        if (ignore[0] + calculationIgn <= k && ignore[1] + 1 > ans[1]) {
            ans[0] = ignore[0] + calculationIgn;
            ans[1] = ignore[1] + 1;
        }
        return memo[index] = ans;
    }
    //=============================================================================================
    //Bottom up approach
    public int longestSubsequence3(String s, int k) {

        if (s.length() == 1) {

            if (s.charAt(0) == '0') {
                return 1;
            }
        }
        double[][] memo = new double[s.length() + 2][2];

        for (int index = s.length() - 1; index >= 0; index--) {
            double[] include = memo[index + 1];
            double[] ignore = memo[index + 2];

            double calculationInc = 0;
            double calculationIgn = 0;

            if (s.charAt(index) == '1') {
                calculationInc = Math.pow(2, include[1]);
                calculationIgn = Math.pow(2, ignore[1]);
            }
            double[] ans = {0, 1};

            if (include[0] + calculationInc <= k) {
                ans[0] = include[0] + calculationInc;
                ans[1] += include[1];
            } else {
                ans[0] = include[0];
                ans[1] = include[1];
            }

            if (ignore[0] + calculationIgn <= k && ignore[1] + 1 > ans[1]) {
                ans[0] = ignore[0] + calculationIgn;
                ans[1] = ignore[1] + 1;
            }
            memo[index] = ans;
        }
        int firstPass = (int)memo[0][1];
        //int secondPass = (int)recur(s, k, 1, memo)[1];
        return firstPass;//Math.max(firstPass, secondPass);
    }
    //=============================================================================================
    //Greedy Approach
    /* The whole idea is to keep the one in the order by which it appears and calculate the sum
        if  the sum is greater than k then we calculate the value of the first one that appears
        remove it from the sum and decrease the length. Then calculate the maxLength
    */
    public int longestSubsequence4(String s, int k) {
        int ans = 0;
        int currLength = 0;
        int currSum = 0;
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            currSum *= 2;

            if (s.charAt(i) == '1') {
                currSum += 1;
                q.offerLast(i);
            }
            currLength++;

            if (currSum > k) {
                int index = q.pollFirst();
                int calculation = (int)Math.pow(2, i - index);
                currSum -= calculation;
                currLength--;
            }
            ans = Math.max(ans, currLength);
        }
        return ans;
    }

}
