package leetcode.DP;

/**
 *
 *
 * You have n boxes. You are given a binary string boxes of length n, where boxes[i] is '0' if the ith box is empty, and '1' if it contains one ball.
 *
 * In one operation, you can move one ball from a box to an adjacent box. Box i is adjacent to box j if abs(i - j) == 1. Note that after doing so, there may be more than one ball in some boxes.
 *
 * Return an array answer of size n, where answer[i] is the minimum number of operations needed to move all the balls to the ith box.
 *
 * Each answer[i] is calculated considering the initial state of the boxes.
 *
 *
 *
 * Example 1:
 *
 * Input: boxes = "110"
 * Output: [1,1,3]
 * Explanation: The answer for each box is as follows:
 * 1) First box: you will have to move one ball from the second box to the first box in one operation.
 * 2) Second box: you will have to move one ball from the first box to the second box in one operation.
 * 3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
 * Example 2:
 *
 * Input: boxes = "001011"
 * Output: [11,8,5,4,3,4]
 *
 *
 * Constraints:
 *
 * n == boxes.length
 * 1 <= n <= 2000
 * boxes[i] is either '0' or '1'.
 *
 */

public class _1769_Minimum_Number_of_Operations_to_Move_All_Balls_to_Each_Box {
    public int[] minOperations(String boxes) {
        int suffix[] = new int[boxes.length()];
        int prefix[] = new int[boxes.length()];
        int prevBalls = boxes.charAt(0) - '0';

        for (int i = 1; i < boxes.length(); i++) {
            int balls = boxes.charAt(i) - '0' + prevBalls; // Number of Balls in current boxes
            int prevMoves = prefix[i - 1]; // previous moves
            prefix[i] = prevBalls + prevMoves; // Moves to bring all balls from previous box to current box
            prevBalls = balls;
        }
        prevBalls = boxes.charAt(boxes.length() - 1) - '0';

        for (int i = boxes.length() - 2; i >= 0; i--) {
            int balls = boxes.charAt(i) - '0' + prevBalls;
            int prevMoves = suffix[i + 1];
            suffix[i] = prevBalls + prevMoves;
            prevBalls = balls;
        }
        int[] ans = new int[boxes.length()];

        for (int i = 0; i < boxes.length(); i++) {
            ans[i] = prefix[i] + suffix[i];
        }
        return ans;
    }
}
