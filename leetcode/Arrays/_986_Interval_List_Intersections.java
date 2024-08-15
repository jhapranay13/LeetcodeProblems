package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 *
 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 *
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 *
 *
 * Example 1:
 *
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 * Example 2:
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *
 *
 * Constraints:
 *
 * 0 <= firstList.length, secondList.length <= 1000
 * firstList.length + secondList.length >= 1
 * 0 <= starti < endi <= 10^9
 * endi < starti+1
 * 0 <= startj < endj <= 10^9
 * endj < startj+1
 *
 */

public class _986_Interval_List_Intersections {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int firstIndex = 0;
        int secondIndex = 0;
        List<int[]> ansHolder = new ArrayList<>();

        while (firstIndex < firstList.length && secondIndex < secondList.length) {
            int lo = Math.max(firstList[firstIndex][0], secondList[secondIndex][0]);
            int hi = Math.min(firstList[firstIndex][1], secondList[secondIndex][1]);

            if (lo <= hi) {
                ansHolder.add(new int[] {lo, hi});
            }

            if (firstList[firstIndex][1] < secondList[secondIndex][1]) {
                firstIndex++;
            } else {
                secondIndex++;
            }
        }
        return ansHolder.toArray(new int[ansHolder.size()][]);
    }
}
