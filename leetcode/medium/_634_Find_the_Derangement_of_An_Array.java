package leetcode.medium;

import java.util.Arrays;

/**
 *
 * In combinatorial mathematics, a derangement is a permutation of the elements of a set, such that no element appears in its original position.
 *
 * You are given an integer n. There is originally an array consisting of n integers from 1 to n in ascending order, return the number of derangements it can generate. Since the answer may be huge, return it modulo 109 + 7.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: The original array is [1,2,3]. The two derangements are [2,3,1] and [3,1,2].
 * Example 2:
 *
 * Input: n = 2
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^6
 *
 */

public class _634_Find_the_Derangement_of_An_Array {
    //Top Down
    public int findDerangement(int n) {
        int memo[] = new int[n + 1];
        Arrays.fill(memo, -1);
        return recur(n, memo);
    }

    private int recur(int n, int[] memo) {
        // no elements means not considering any elements in rearrangement.
        //Hence there is only 1 way of rearrangement.
        if (n <= 0) {
            return 1;
        }

        if (n == 1) {
            return 0;
        }

        if (n == 2) {
            return 1;
        }

        if (memo[n] > -1) {
            return memo[n];
        }
        // every element can have n - 1 position to swap with
        // the element which is swapped will also have n - 1 if it chosses to be swapped again
        // or n - 2 if it chooses not to be swapped
        return memo[n] = (int)(((n - 1L) * (recur(n - 1, memo) + recur(n - 2, memo))) % 1000000007);
    }
    //=============================================================================================
    //Bottom up approach
    public int findDerangement1(int n) {
        int memo[] = new int[n + 1];
        Arrays.fill(memo, -1);
        int N = n;
        n = 0;
        for ( ; n <= N; n++) {
            if (n <= 0) {
                memo[n] = 1;
                continue;
            }

            if (n == 1) {
                memo[n] = 0;
                continue;
            }

            if (n == 2) {
                memo[n] = 1;
                continue;
            }
            // every element can have n - 1 position to swap with
            // the element which is swapped will also have n - 1 if it chosses to be swapped again
            // or n - 2 if it chooses not to be swapped
            memo[n] = (int)(((n - 1L) * (recur(n - 1, memo) + recur(n - 2, memo))) % 1000000007);

        }
        return memo[N];
    }
}
