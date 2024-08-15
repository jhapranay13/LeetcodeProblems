package leetcode.Arrays;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 *
 * You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval [lefti, righti].
 *
 * You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no two intervals that are in the same group intersect each other.
 *
 * Return the minimum number of groups you need to make.
 *
 * Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5] and [5, 8] intersect.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
 * Output: 3
 * Explanation: We can divide the intervals into the following groups:
 * - Group 1: [1, 5], [6, 8].
 * - Group 2: [2, 3], [5, 10].
 * - Group 3: [1, 10].
 * It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
 * Example 2:
 *
 * Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
 * Output: 1
 * Explanation: None of the intervals overlap, so we can put all of them in one group.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^6
 *
 */

public class _2406_Divide_Intervals_Into_Minimum_Number_of_Groups {
    //Line sweep Treemap
    public int minGroups(int[][] intervals) {
        TreeMap<Integer, Integer> lineSweep = new TreeMap<>();

        for (int[] interval : intervals) {
            lineSweep.put(interval[0], lineSweep.getOrDefault(interval[0], 0) + 1);
            lineSweep.put(interval[1] + 1, lineSweep.getOrDefault(interval[1] + 1, 0) - 1); // +1 to trigger the overlap
        }
        int ans = 0;
        int curr = 0;

        for (int key : lineSweep.keySet()) {
            curr += lineSweep.get(key);
            ans = Math.max(ans, curr);
        }
        return ans;
    }
    //=============================================================================================
    // Line Sweep PriorityQueue
    public int minGroups1(int[][] intervals) {

        if(intervals.length == 1) {
            return 1;
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> q = new PriorityQueue<>();

        for(int[] interval : intervals){

            if(q.isEmpty() || q.peek() >= interval[0]){
                q.add(interval[1]);
                continue;
            }
            q.remove();
            q.add(interval[1]);
        }
        return q.size();
    }
    //=============================================================================================
    // Another opproach
    public int minGroups2(int[][] intervals) {

        if(intervals.length == 1) {
            return 1;
        }
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for(int[] interval : intervals){

            if(q.isEmpty() || q.peek()[1] >= interval[0]){
                q.add(interval);
                continue;
            }
            q.remove();
            q.add(interval);
        }
        return q.size();
    }
}
