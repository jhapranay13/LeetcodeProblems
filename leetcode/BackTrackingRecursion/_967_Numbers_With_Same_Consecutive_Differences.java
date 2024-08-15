package leetcode.BackTrackingRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Return all non-negative integers of length n such that the absolute difference between every two consecutive digits is k.
 *
 * Note that every number in the answer must not have leading zeros. For example, 01 has one leading zero and is invalid.
 *
 * You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, k = 7
 * Output: [181,292,707,818,929]
 * Explanation: Note that 070 is not a valid number, because it has leading zeroes.
 * Example 2:
 *
 * Input: n = 2, k = 1
 * Output: [10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
 *
 *
 * Constraints:
 *
 * 2 <= n <= 9
 * 0 <= k <= 9
 *
 */

public class _967_Numbers_With_Same_Consecutive_Differences {
    //BFS
    public int[] numsSameConsecDiff(int n, int k) {

        if (n == 1) {
            return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
        List<Integer> holder = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            holder.add(i);
        }

        while (n > 1) {
            List<Integer> temp = new ArrayList<>();

            for (int num : holder) {
                int lastDigit = num % 10;
                List<Integer> list = new ArrayList<>();
                list.add(lastDigit + k);

                if (k != 0) {
                    list.add(lastDigit - k);
                }

                for (int digit : list) {

                    if (digit >= 0 && digit <= 9) {
                        temp.add(num * 10 + digit);
                    }
                }
            }
            holder = temp;
            n--;
        }
        int[] ans = new int[holder.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = holder.get(i);
        }
        return ans;
    }
    //=============================================================================================
    //DFS
    public int[] numsSameConsecDiff1(int n, int k) {

        if (n == 1) {
            return new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        }
        List<Integer> holder = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            recur(n - 1, k, holder, i);
        }
        int[] ans = new int[holder.size()];

        for (int i = 0; i < ans.length; i++) {
            ans[i] = holder.get(i);
        }
        return ans;
    }

    private void recur(int n, int k, List<Integer> ans, int num) {

        if (n == 0) {
            ans.add(num);
            return;
        }
        List<Integer> holder = new ArrayList<>();
        int lastDigit = num % 10;
        holder.add(lastDigit + k);

        if (k != 0) {
            holder.add(lastDigit - k);
        }

        for (int nextNum : holder) {

            if (nextNum >= 0 && nextNum <= 9) {
                recur(n - 1, k, ans, num * 10 + nextNum);
            }
        }
    }
}
