package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Given an array of integers arr of even length n and an integer k.
 *
 * We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
 *
 * Return true If you can find a way to do that or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
 * Output: true
 * Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5,6], k = 7
 * Output: true
 * Explanation: Pairs are (1,6),(2,5) and(3,4).
 * Example 3:
 *
 * Input: arr = [1,2,3,4,5,6], k = 10
 * Output: false
 * Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.
 *
 *
 * Constraints:
 *
 * arr.length == n
 * 1 <= n <= 10^5
 * n is even.
 * -10^9 <= arr[i] <= 10^9
 * 1 <= k <= 10^5
 *
 */

public class _1497_Check_If_Array_Pairs_Are_Divisible_by_k {
    public boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : arr) {
            int remainder = num % k;

            if (remainder < 0) {
                remainder += k;
            }
            freq.put(remainder, freq.getOrDefault(remainder, 0) + 1);
        }

        for (int a : arr) {
            int num = a % k;

            if (num < 0) {
                num += k;
            }
            int otherNum = num == 0 ? 0 : k - (num % k);

            if (freq.containsKey(otherNum) && freq.containsKey(num)) {

                if (num == otherNum && freq.get(num) < 2) {
                    continue;
                }

                if (freq.get(otherNum) == 1) {
                    freq.remove(otherNum);
                } else {
                    freq.put(otherNum, freq.get(otherNum) - 1);
                }

                if (freq.get(num) == 1) {
                    freq.remove(num);
                } else {
                    freq.put(num, freq.get(num) - 1);
                }
            }
        }
        return freq.isEmpty();
    }
}
