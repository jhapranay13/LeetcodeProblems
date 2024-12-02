package leetcode.DP;

import java.util.Arrays;

/**
 *
 *
 * You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with the highest overall score. The score of the team is the sum of scores of all the players in the team.
 *
 * However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has a strictly higher score than an older player. A conflict does not occur between players of the same age.
 *
 * Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the ith player, respectively, return the highest overall score of all possible basketball teams.
 *
 *
 *
 * Example 1:
 *
 * Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
 * Output: 34
 * Explanation: You can choose all the players.
 * Example 2:
 *
 * Input: scores = [4,5,6,5], ages = [2,1,2,1]
 * Output: 16
 * Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of the same age.
 * Example 3:
 *
 * Input: scores = [1,2,3,5], ages = [8,9,10,1]
 * Output: 6
 * Explanation: It is best to choose the first 3 players.
 *
 *
 * Constraints:
 *
 * 1 <= scores.length, ages.length <= 1000
 * scores.length == ages.length
 * 1 <= scores[i] <= 10^6
 * 1 <= ages[i] <= 1000
 *
 *
 */

public class _1626_Best_Team_With_No_Conflicts {
    // Top down
    public int bestTeamScore(int[] scores, int[] ages) {
        int[][] scoreAge = new int[scores.length][];

        for (int i = 0; i < scores.length; i++) {
            scoreAge[i] = new int[]{scores[i], ages[i]};
        }
        Arrays.sort(scoreAge, (arr1, arr2) -> {
            return arr1[1] == arr2[1] ? arr1[0] - arr2[0] : arr1[1] - arr2[1];
        });
        int[][] memo = new int[scores.length][scores.length];
        return recur(scoreAge, -1, 0, memo);
    }

    private int recur(int[][] scoreAge, int prevIndex, int index, int[][] memo) {

        if (index >= scoreAge.length) {
            return 0;
        }

        if (memo[prevIndex + 1][index] > 0) {
            return memo[prevIndex + 1][index];
        }
        int ans = 0;

        if (prevIndex == -1 || (scoreAge[prevIndex][0] <= scoreAge[index][0])) {
            int currScore = scoreAge[index][0];
            currScore += recur(scoreAge, index, index + 1, memo);
            int skip = recur(scoreAge, prevIndex, index + 1, memo);
            ans = Math.max(currScore, skip);
        }
        int skip = recur(scoreAge, prevIndex, index + 1, memo);
        return memo[prevIndex + 1][index] = Math.max(ans, skip);
    }
}
