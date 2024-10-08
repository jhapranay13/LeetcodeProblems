package leetcode.DP.TwoDimension;

/**
 *
 * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
 *
 * In one step, you can delete exactly one character in either string.
 *
 *
 *
 * Example 1:
 *
 * Input: word1 = "sea", word2 = "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
 * Example 2:
 *
 * Input: word1 = "leetcode", word2 = "etco"
 * Output: 4
 *
 *
 * Constraints:
 *
 * 1 <= word1.length, word2.length <= 500
 * word1 and word2 consist of only lowercase English letters.
 *
 */

public class _583_Delete_Operation_for_Two_Strings {
    //Top down approach
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        //Check for common subsequence. All the other chars in both will be deleted
        // So into two because they are common in both of them.
        return word1.length() + word2.length() - 2 * recur(word1, word2, 0, 0, memo);
    }

    private int recur(String word1, String word2, int index1, int index2, int[][] memo) {

        if (index1 == word1.length() || index2 == word2.length()) {
            return 0;
        }

        if (memo[index1][index2] > 0) {
            return memo[index1][index2];
        }
        int ans = 0;

        if (word1.charAt(index1) == word2.charAt(index2)) {
            ans = 1 + recur(word1, word2, index1 + 1, index2 + 1, memo);
        } else {
            ans = Math.max(recur(word1, word2, index1 + 1, index2, memo),
                    recur(word1, word2, index1, index2 + 1, memo));
        }
        return memo[index1][index2] = ans;
    }

    //=============================================================================================
    //Bottom up approach
    public int minDistance1(String word1, String word2) {
        int[][] memo = new int[word1.length() + 1][word2.length() + 1];
        //Check for common subsequence. All the other chars in both will be deleted
        // So into two because they are common in both of them.

        for (int index1 = word1.length() - 1; index1 >= 0; index1--) {

            for (int index2 = word2.length() - 1; index2 >= 0; index2--) {
                int ans = 0;

                if (word1.charAt(index1) == word2.charAt(index2)) {
                    ans = 1 + memo[index1 + 1][index2 + 1];
                } else {
                    ans = Math.max(memo[index1 + 1][index2], memo[index1][index2 + 1]);
                }
                memo[index1][index2] = ans;
            }
        }
        return word1.length() + word2.length() - 2 * memo[0][0];
    }
}
