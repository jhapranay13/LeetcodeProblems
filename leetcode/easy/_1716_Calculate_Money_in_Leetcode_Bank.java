package leetcode.easy;

/**
 *
 *
 * Hercy wants to save money for his first car. He puts money in the Leetcode bank every day.
 *
 * He starts by putting in $1 on Monday, the first day. Every day from Tuesday to Sunday, he will put in $1 more than the day before. On every subsequent Monday, he will put in $1 more than the previous Monday.
 *
 * Given n, return the total amount of money he will have in the Leetcode bank at the end of the nth day.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 10
 * Explanation: After the 4th day, the total is 1 + 2 + 3 + 4 = 10.
 * Example 2:
 *
 * Input: n = 10
 * Output: 37
 * Explanation: After the 10th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4) = 37. Notice that on the 2nd Monday, Hercy only puts in $2.
 * Example 3:
 *
 * Input: n = 20
 * Output: 96
 * Explanation: After the 20th day, the total is (1 + 2 + 3 + 4 + 5 + 6 + 7) + (2 + 3 + 4 + 5 + 6 + 7 + 8) + (3 + 4 + 5 + 6 + 7 + 8) = 96.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1000
 *
 */

public class _1716_Calculate_Money_in_Leetcode_Bank {
    public int totalMoney(int n) {
        int numWeeks = n / 7;
        int extraDays = n % 7;
        int weekCount = 1;
        int ans = 0;
        int endAmtWeek = 7;
        // Calculating for number of weeks
        while (weekCount <= numWeeks) {
            ans += (endAmtWeek * (endAmtWeek + 1) / 2) -
                    (weekCount * (weekCount - 1) / 2);
            weekCount++;
            endAmtWeek++;
        }
        // For extra days we have to check the range so we calculate end endAmt
        // and substract starting  amt.
        if (extraDays > 0) {
            int endAmt = weekCount + extraDays;
            ans += (endAmt * (endAmt - 1) / 2) - (weekCount * (weekCount - 1) / 2);
        }
        return ans;
    }
}
