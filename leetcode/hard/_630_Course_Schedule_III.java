package leetcode.hard;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *
 * There are n different online courses numbered from 1 to n. You are given an array courses where courses[i] = [durationi, lastDayi] indicate that the ith course should be taken continuously for durationi days and must be finished before or on lastDayi.
 *
 * You will start on the 1st day and you cannot take two or more courses simultaneously.
 *
 * Return the maximum number of courses that you can take.
 *
 *
 *
 * Example 1:
 *
 * Input: courses = [[100,200],[200,1300],[1000,1250],[2000,3200]]
 * Output: 3
 * Explanation:
 * There are totally 4 courses, but you can take 3 courses at most:
 * First, take the 1st course, it costs 100 days so you will finish it on the 100th day, and ready to take the next course on the 101st day.
 * Second, take the 3rd course, it costs 1000 days so you will finish it on the 1100th day, and ready to take the next course on the 1101st day.
 * Third, take the 2nd course, it costs 200 days so you will finish it on the 1300th day.
 * The 4th course cannot be taken now, since you will finish it on the 3300th day, which exceeds the closed date.
 * Example 2:
 *
 * Input: courses = [[1,2]]
 * Output: 1
 * Example 3:
 *
 * Input: courses = [[3,2],[4,3]]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= courses.length <= 10^4
 * 1 <= durationi, lastDayi <= 10^4
 *
 */

public class _630_Course_Schedule_III {
    // PQ Approach
    public int scheduleCourse1(int[][] courses) {
        // Sorting my earliest end time
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        int day = 0;

        for (int[] course : courses) {

            if (course[1] >= day + course[0]) {
                pq.offer(new int[] {course[0], day + course[0]});
                day = day + course[0];
            } else if (!pq.isEmpty() && course[0] < pq.peek()[0]){
                // Taking out the last course with max duration
                day -= pq.peek()[0];
                day += course[0];
                pq.poll();
                pq.offer(new int[] {course[0],  day + course[0]});
            }
        }
        return pq.size();
    }

    //=============================================================================================
    // Top Down TLE
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        Integer[][] memo = new Integer[courses.length][courses[courses.length - 1][1] + 1];
        return recur(courses, 0, 0, memo);
    }

    private int recur(int[][] courses, int index, int time, Integer[][] memo) {

        if (index == courses.length) {
            return 0;
        }

        if (memo[index][time] != null) {
            return memo[index][time];
        }
        int include = 0;

        if (time + courses[index][0] <= courses[index][1]) {
            include = 1 + recur(courses, index + 1, time + courses[index][0], memo);
        }
        int exclude = recur(courses, index + 1, time, memo);
        return memo[index][time] = Math.max(include, exclude);
    }
}
