package leetcode.EquationPreviousCurrentNext;

/**
 *
 * You are given an integer array values where values[i] represents the value of the ith sightseeing spot. Two sightseeing spots i and j have a distance j - i between them.
 *
 * The score of a pair (i < j) of sightseeing spots is values[i] + values[j] + i - j: the sum of the values of the sightseeing spots, minus the distance between them.
 *
 * Return the maximum score of a pair of sightseeing spots.
 *
 *
 *
 * Example 1:
 *
 * Input: values = [8,1,5,2,6]
 * Output: 11
 * Explanation: i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
 * Example 2:
 *
 * Input: values = [1,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * 2 <= values.length <= 5 * 104
 * 1 <= values[i] <= 1000
 */

public class _1014_BestSigtSeeingPair {
    // Given condition is values[i] + values[j] + i - j
    // Grouping it values[i] + i + values[j] - j;
    // since I < j and if j is current hen we will need the maximum values if values[i] + i
    public int maxScoreSightseeingPair(int[] values) {
        int max = values[0] + 0; // mimicing values[i] + i
        int ans = 0;

        for (int j = 1; j < values.length; j++) {
            ans = Math.max(ans, max + values[j] - j);
            max = Math.max(values[j] + j, max);
        }
        return ans;
    }
}
