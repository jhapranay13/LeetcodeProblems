package leetcode.Sorting;

import java.util.PriorityQueue;

/**
 *
 * There is a special kind of apple tree that grows apples every day for n days. On the ith day, the tree grows apples[i] apples that will rot after days[i] days, that is on day i + days[i] the apples will be rotten and cannot be eaten. On some days, the apple tree does not grow any apples, which are denoted by apples[i] == 0 and days[i] == 0.
 *
 * You decided to eat at most one apple a day (to keep the doctors away). Note that you can keep eating after the first n days.
 *
 * Given two integer arrays days and apples of length n, return the maximum number of apples you can eat.
 *
 *
 *
 * Example 1:
 *
 * Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * Output: 7
 * Explanation: You can eat 7 apples:
 * - On the first day, you eat an apple that grew on the first day.
 * - On the second day, you eat an apple that grew on the second day.
 * - On the third day, you eat an apple that grew on the second day. After this day, the apples that grew on the third day rot.
 * - On the fourth to the seventh days, you eat apples that grew on the fourth day.
 * Example 2:
 *
 * Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * Output: 5
 * Explanation: You can eat 5 apples:
 * - On the first to the third day you eat apples that grew on the first day.
 * - Do nothing on the fouth and fifth days.
 * - On the sixth and seventh days you eat apples that grew on the sixth day.
 *
 *
 * Constraints:
 *
 * n == apples.length == days.length
 * 1 <= n <= 2 * 10^4
 * 0 <= apples[i], days[i] <= 2 * 10^4
 * days[i] = 0 if and only if apples[i] = 0.
 *
 */

public class _1705_Maximum_Number_of_Eaten_Apples {
    public int eatenApples(int[] apples, int[] days) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int index = 0;
        int day = 0;

        while (index < apples.length || !pq.isEmpty()) {

            if (index < apples.length) {

                if (apples[index] != 0) {
                    pq.offer(new int[] {apples[index], days[index] + index});
                }
            }

            while (!pq.isEmpty() && pq.peek()[1] <= index) {
                pq.poll();
            }

            if (!pq.isEmpty()) {
                int appleDayData[] = pq.poll();
                int numberOfdaysToCover = appleDayData[1] - day;
                int numberOfApples = appleDayData[0];
                int appleGoodDay = Math.min(numberOfApples, numberOfdaysToCover);

                if (appleGoodDay > 1) {
                    pq.offer(new int[] {numberOfApples - 1, appleDayData[1]});
                }
                day++;
            }
            index++;
        }
        return day;
    }
}
