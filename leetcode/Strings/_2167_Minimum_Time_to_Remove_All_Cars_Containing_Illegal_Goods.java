package leetcode.Strings;

/**
 *
 *
 *
 * You are given a 0-indexed binary string s which represents a sequence of train cars. s[i] = '0' denotes that the ith car does not contain illegal goods and s[i] = '1' denotes that the ith car does contain illegal goods.
 *
 * As the train conductor, you would like to get rid of all the cars containing illegal goods. You can do any of the following three operations any number of times:
 *
 * Remove a train car from the left end (i.e., remove s[0]) which takes 1 unit of time.
 * Remove a train car from the right end (i.e., remove s[s.length - 1]) which takes 1 unit of time.
 * Remove a train car from anywhere in the sequence which takes 2 units of time.
 * Return the minimum time to remove all the cars containing illegal goods.
 *
 * Note that an empty sequence of cars is considered to have no cars containing illegal goods.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "1100101"
 * Output: 5
 * Explanation:
 * One way to remove all the cars containing illegal goods from the sequence is to
 * - remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
 * - remove a car from the right end. Time taken is 1.
 * - remove the car containing illegal goods found in the middle. Time taken is 2.
 * This obtains a total time of 2 + 1 + 2 = 5.
 *
 * An alternative way is to
 * - remove a car from the left end 2 times. Time taken is 2 * 1 = 2.
 * - remove a car from the right end 3 times. Time taken is 3 * 1 = 3.
 * This also obtains a total time of 2 + 3 = 5.
 *
 * 5 is the minimum time taken to remove all the cars containing illegal goods.
 * There are no other ways to remove them with less time.
 * Example 2:
 *
 * Input: s = "0010"
 * Output: 2
 * Explanation:
 * One way to remove all the cars containing illegal goods from the sequence is to
 * - remove a car from the left end 3 times. Time taken is 3 * 1 = 3.
 * This obtains a total time of 3.
 *
 * Another way to remove all the cars containing illegal goods from the sequence is to
 * - remove the car containing illegal goods found in the middle. Time taken is 2.
 * This obtains a total time of 2.
 *
 * Another way to remove all the cars containing illegal goods from the sequence is to
 * - remove a car from the right end 2 times. Time taken is 2 * 1 = 2.
 * This obtains a total time of 2.
 *
 * 2 is the minimum time taken to remove all the cars containing illegal goods.
 * There are no other ways to remove them with less time.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 2 * 10^5
 * s[i] is either '0' or '1'.
 *
 */

public class _2167_Minimum_Time_to_Remove_All_Cars_Containing_Illegal_Goods {
    public int minimumTime(String s) {
        int[] leftToRight = new int[s.length()];
        leftToRight[0] = s.charAt(0) == '1' ? 1 : 0;

        for (int i = 1; i < s.length(); i++) {
            leftToRight[i] = leftToRight[i - 1];

            if (s.charAt(i) == '1') {
                leftToRight[i] = Math.min(i + 1, leftToRight[i] + 2);
            }
        }
        int n = s.length();
        int[] rightToLeft = new int[n];
        rightToLeft[n - 1] = s.charAt(n - 1) == '1' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--) {
            rightToLeft[i] = rightToLeft[i + 1];

            if (s.charAt(i) == '1') {
                rightToLeft[i] = Math.min(n - i, rightToLeft[i] + 2);
            }
        }
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            int temp = 0;

            if (i + 1 < n) {
                temp = rightToLeft[i + 1];
            }
            ans = Math.min(ans, leftToRight[i] + temp);
        }
        return ans;
    }
}
