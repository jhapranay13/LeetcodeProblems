package leetcode.SlidingWindow;

/**
 * There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 *
 *
 * Example 1:
 *
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 *
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 *
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 *
 *
 * Constraints:
 *
 * 1 <= cardPoints.length <= 105
 * 1 <= cardPoints[i] <= 104
 * 1 <= k <= cardPoints.length
 *
 */

public class _1423_MaximumPointsYouCanObtainfromCards {


    public int maxScore(int[] cardPoints, int k) {
        int fast = cardPoints.length - k;
        int slow = fast;
        int sum = 0;
        int ans = 0;
        //Circular Sliding window

        while (fast < cardPoints.length + k) {
            sum += cardPoints[fast++ % cardPoints.length];

            if (fast - slow == k) {
                ans = Math.max(ans, sum);
                sum -= cardPoints[slow++ % cardPoints.length];
            }
        }
        return ans;
    }

    //==============================================================================================
    //Top Down approach TLE
    public int maxScore1(int[] cardPoints, int k) {
        int [][] memo = new int[cardPoints.length][cardPoints.length];
        return recur(cardPoints, k, 0, 0, cardPoints.length - 1, memo);
    }

    private int recur(int[] cardPoints, int k, int count, int lo, int hi, int[][] memo) {
        if (cardPoints.length - (hi - lo + 1) == k) {
            return 0;
        }

        if (lo > hi) {
            return Integer.MIN_VALUE;
        }

        if (memo[lo][hi] > 0) {
            return memo[lo][hi];
        }
        int left = cardPoints[lo] + recur(cardPoints, k, count + 1, lo + 1, hi, memo);
        int right = cardPoints[hi] + recur(cardPoints, k, count + 1, lo, hi - 1, memo);
        return memo[lo][hi] = Math.max(left, right);
    }

}
