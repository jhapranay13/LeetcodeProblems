package leetcode.DP;

/**
 *
 * There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
 *
 * You have to form a team of 3 soldiers amongst them under the following rules:
 *
 * Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
 * A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
 * Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
 *
 *
 *
 * Example 1:
 *
 * Input: rating = [2,5,3,4,1]
 * Output: 3
 * Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
 * Example 2:
 *
 * Input: rating = [2,1,3]
 * Output: 0
 * Explanation: We can't form any team given the conditions.
 * Example 3:
 *
 * Input: rating = [1,2,3,4]
 * Output: 4
 *
 *
 * Constraints:
 *
 * n == rating.length
 * 3 <= n <= 1000
 * 1 <= rating[i] <= 10^5
 * All the integers in rating are unique.
 *
 */

public class _1395_Count_Number_of_Teams {
    public int numTeams(int[] rating) {
        int sum = 0;
        Integer[][] memo1 = new Integer[rating.length][4];
        Integer[][] memo2 = new Integer[rating.length][4];


        for (int i = 0; i < rating.length; i ++) {
            sum += increasingRecur(rating, i, 1, memo1);
            sum += decreasingRecur(rating, i, 1, memo2);
        }
        return sum;
    }

    private int increasingRecur(int[] rating, int index, int count, Integer[][] memo) {

        if (count == 3) {
            return 1;
        }

        if (index == rating.length) {
            return 0;
        }

        if (memo[index][count] != null) {
            return memo[index][count];
        }
        int ans = 0;

        for (int i = index + 1; i < rating.length; i++) {

            if (rating[i] > rating[index]) {
                ans += increasingRecur(rating, i, count + 1, memo);
            }
        }

        return memo[index][count] = ans;
    }

    private int decreasingRecur(int[] rating, int index, int count, Integer[][] memo) {

        if (count == 3) {
            return 1;
        }

        if (index == rating.length) {
            return 0;
        }

        if (memo[index][count] != null) {
            return memo[index][count];
        }
        int ans = 0;

        for (int i = index + 1; i < rating.length; i++) {

            if (rating[i] < rating[index]) {
                ans += decreasingRecur(rating, i, count + 1, memo);
            }
        }

        return memo[index][count] = ans;
    }

}
