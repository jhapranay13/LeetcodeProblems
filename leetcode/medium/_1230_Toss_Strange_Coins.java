package leetcode.medium;

import java.util.Arrays;

/**
 *
 *
 * You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
 *
 * Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: prob = [0.4], target = 1
 * Output: 0.40000
 * Example 2:
 *
 * Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
 * Output: 0.03125
 *
 *
 * Constraints:
 *
 * 1 <= prob.length <= 1000
 * 0 <= prob[i] <= 1
 * 0 <= target <= prob.length
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 *
 *
 */

public class _1230_Toss_Strange_Coins {
    public double probabilityOfHeads(double[] prob, int target) {
        double[][] memo = new double[target + 1][prob.length + 1];

        for (double[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return recur(prob, target, 0, memo);
    }

    private double recur(double[] prob, int target, int index, double[][] memo) {

        if (target < 0) {
            return 0;
        }

        if (index == prob.length) {
            return target == 0 ? 1 : 0;
        }

        if (memo[target][index] > -1) {
            return memo[target][index];
        }
        double headProbability = recur(prob, target - 1, index + 1, memo) * prob[index];
        double tailProbability = recur(prob, target, index + 1, memo) * (1 - prob[index]);
        return memo[target][index] = headProbability + tailProbability;
    }
}
