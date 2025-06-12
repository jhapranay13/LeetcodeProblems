package leetcode.Arrays;

/**
 *
 * You are given an integer n representing an array colors of length n where all elements are set to 0's meaning uncolored. You are also given a 2D integer array queries where queries[i] = [indexi, colori]. For the ith query:
 *
 * Set colors[indexi] to colori.
 * Count the number of adjacent pairs in colors which have the same color (regardless of colori).
 * Return an array answer of the same length as queries where answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4, queries = [[0,2],[1,2],[3,1],[1,1],[2,1]]
 *
 * Output: [0,1,1,0,2]
 *
 * Explanation:
 *
 * Initially array colors = [0,0,0,0], where 0 denotes uncolored elements of the array.
 * After the 1st query colors = [2,0,0,0]. The count of adjacent pairs with the same color is 0.
 * After the 2nd query colors = [2,2,0,0]. The count of adjacent pairs with the same color is 1.
 * After the 3rd query colors = [2,2,0,1]. The count of adjacent pairs with the same color is 1.
 * After the 4th query colors = [2,1,0,1]. The count of adjacent pairs with the same color is 0.
 * After the 5th query colors = [2,1,1,1]. The count of adjacent pairs with the same color is 2.
 * Example 2:
 *
 * Input: n = 1, queries = [[0,100000]]
 *
 * Output: [0]
 *
 * Explanation:
 *
 * After the 1st query colors = [100000]. The count of adjacent pairs with the same color is 0.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 10^5
 * 1 <= queries.length <= 10^5
 * queries[i].length == 2
 * 0 <= indexi <= n - 1
 * 1 <=  colori <= 10^5
 *
 */

public class _2672_Number_Adjacent_Elements_With_Same_Color {

    public int[] colorTheArray(int n, int[][] queries) {
        int ans[] = new int[queries.length];
        int[] holder = new int[n];
        int pair = 0;  // total pair possible initially

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            int index = query[0];
            int color = query[1];

            if (color == holder[index]) {
                ans[i] = pair;
                continue;
            }
            int count = 0;

            if (index > 0 && holder[index] == holder[index - 1] && holder[index] != 0) {
                count++;
            }

            if (index < n - 1 && holder[index] == holder[index + 1] && holder[index] != 0) {
                count++;
            }
            pair -= count;
            holder[index] = color;

            if (index > 0 && holder[index] == holder[index - 1] && holder[index] != 0) {
                pair++;
            }

            if (index < n - 1 && holder[index] == holder[index + 1] && holder[index] != 0) {
                pair++;
            }
            ans[i] = pair;
        }
        return ans;
    }
}
