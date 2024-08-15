package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Given a list of dominoes, dominoes[i] = [a, b] is equivalent to dominoes[j] = [c, d] if and only if either (a == c and b == d), or (a == d and b == c) - that is, one domino can be rotated to be equal to another domino.
 *
 * Return the number of pairs (i, j) for which 0 <= i < j < dominoes.length, and dominoes[i] is equivalent to dominoes[j].
 *
 *
 *
 * Example 1:
 *
 * Input: dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * Output: 1
 * Example 2:
 *
 * Input: dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= dominoes.length <= 4 * 104
 * dominoes[i].length == 2
 * 1 <= dominoes[i][j] <= 9
 *
 *
 */

public class _1128_Number_of_Equivalent_Domino_Pairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<String, Integer> countMap = new HashMap<>();
        int ans = 0;

        for (int[] domino : dominoes) {
            String key = "";

            if (domino[0] <= domino[1]) {
                key = domino[0] + "|" + domino[1];
            } else {
                key = domino[1] + "|" + domino[0];
            }
            ans += countMap.getOrDefault(key, 0);
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }
        return ans;
    }
}
