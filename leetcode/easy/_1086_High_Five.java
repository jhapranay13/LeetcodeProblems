package leetcode.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Given a list of the scores of different students, items, where items[i] = [IDi, scorei] represents one score from a student with IDi, calculate each student's top five average.
 *
 * Return the answer as an array of pairs result, where result[j] = [IDj, topFiveAveragej] represents the student with IDj and their top five average. Sort result by IDj in increasing order.
 *
 * A student's top five average is calculated by taking the sum of their top five scores and dividing it by 5 using integer division.
 *
 *
 *
 * Example 1:
 *
 * Input: items = [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],[1,87],[1,100],[2,100],[2,76]]
 * Output: [[1,87],[2,88]]
 * Explanation:
 * The student with ID = 1 got scores 91, 92, 60, 65, 87, and 100. Their top five average is (100 + 92 + 91 + 87 + 65) / 5 = 87.
 * The student with ID = 2 got scores 93, 97, 77, 100, and 76. Their top five average is (100 + 97 + 93 + 77 + 76) / 5 = 88.6, but with integer division their average converts to 88.
 * Example 2:
 *
 * Input: items = [[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100],[1,100],[7,100]]
 * Output: [[1,100],[7,100]]
 *
 *
 * Constraints:
 *
 * 1 <= items.length <= 1000
 * items[i].length == 2
 * 1 <= IDi <= 1000
 * 0 <= scorei <= 100
 * For each IDi, there will be at least five scores.
 *
 */

public class _1086_High_Five {
    // Can be done with HashMap and priorityQueue
    public int[][] highFive(int[][] items) {
        Arrays.sort(items, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int index = 0;
        List<int[]> holder = new ArrayList<>();

        while (index < items.length) {
            int id = items[index][0];
            int sum = 0;
            int anchor = index;

            while (index < items.length && items[index][0] == id) {

                if (index - anchor < 5) {
                    sum += items[index][1];
                }
                index++;
            }
            holder.add(new int[] {id, sum / 5});
        }
        int[][] ans = new int[holder.size()][2];
        index = 0;

        for (int[] res : holder) {
            ans[index++] = res;
        }
        return ans;
    }
}
