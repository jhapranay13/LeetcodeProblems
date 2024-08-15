package leetcode.Math;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
 *
 * Given an integer n, return the nth ugly number.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 12
 * Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
 * Example 2:
 *
 * Input: n = 1
 * Output: 1
 * Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 1690
 *
 */

public class _264_Ugly_Number_II {
    public int nthUglyNumber(int n) {
        int x = 0;
        int y = 0;
        int z = 0;
        List<Integer> holder = new ArrayList<>();
        holder.add(1);

        for (int i = 1; i < n; i++) {
            int nextNum = Math.min(holder.get(x) * 2, Math.min(holder.get(y) * 3, holder.get(z) * 5));
            holder.add(nextNum);

            if (holder.get(x) * 2 == nextNum) {
                x++;
            }

            if (holder.get(y) * 3 == nextNum) {
                y++;
            }

            if (holder.get(z) * 5 == nextNum) {
                z++;
            }
        }
        return holder.get(holder.size() - 1);
    }
}
