package leetcode.easy;

/**
 *
 * Given two positive integers a and b, return the count of numbers having unique digits in the range [a, b] (inclusive).
 *
 *
 * Example 1:
 *
 * Input: a = 1, b = 20
 * Output: 19
 * Explanation: All the numbers in the range [1, 20] have unique digits except 11. Hence, the answer is 19.
 * Example 2:
 *
 * Input: a = 9, b = 19
 * Output: 10
 * Explanation: All the numbers in the range [9, 19] have unique digits except 11. Hence, the answer is 10.
 * Example 3:
 *
 * Input: a = 80, b = 120
 * Output: 27
 * Explanation: There are 41 numbers in the range [80, 120], 27 of which have unique digits.
 *
 *
 * Constraints:
 *
 * 1 <= a <= b <= 1000
 *
 *
 */

public class _3032_Count_Numbers_With_Unique_Digits_II {
    // can be done in a simple ways. Here the idea is check for uniqueness when transition
    // from one state to another
    public int numberCount(int a, int b) {
        int freq[] = new int[10];
        int num = a;
        int notFormed = 0;
        int ans = 0;

        while (num > 0) {
            int digit = num % 10;
            num /= 10;

            if (freq[digit] > 0) {
                notFormed++;
            }
            freq[digit]++;
        }

        if (notFormed == 0) {
            ans++;
        }

        while (a < b) {

            int tempA1 = a;
            int tempA2 = ++a;
            int lastDigitOfA1 = tempA1 % 10;
            int lastDigitOfA2 = tempA2 % 10;

            while (lastDigitOfA1 != lastDigitOfA2 && tempA2 != 0) {

                if (tempA1 != 0) {

                    if (freq[lastDigitOfA1] > 1 ) {
                        notFormed--;
                    }
                    freq[lastDigitOfA1]--;
                }

                if (freq[lastDigitOfA2] >= 1) {
                    notFormed++;
                }
                freq[lastDigitOfA2]++;
                tempA1 /= 10;
                tempA2 /= 10;
                lastDigitOfA1 = tempA1 % 10;
                lastDigitOfA2 = tempA2 % 10;
            }

            if (notFormed == 0) {
                ans++;
            }
        }
        return ans;
    }
}
