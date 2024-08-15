package leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 *
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 *
 *
 *
 * Example 1:
 *
 * Input: low = 100, high = 300
 * Output: [123,234]
 * Example 2:
 *
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 *
 *
 * Constraints:
 *
 * 10 <= low <= high <= 10^9
 *
 */


public class _1291_Sequential_Digit {
    public List<Integer> sequentialDigits(int low, int high) {
        String sample = "123456789";
        List<Integer> ans = new ArrayList<>();
        int lowSize = ("" + low).length();
        int highSize = ("" + high).length();

        for (int length = lowSize; length <= highSize; length++) {

            for (int start = 0; start <= sample.length() - length; start++) {
                int num = Integer.parseInt(sample.substring(start, start + length));

                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }
        return ans;
    }
}
