package LeetcodeDiscuss;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * A student at HackerSchool is provided with a schedule of n days,
 * where each day can have up to m hours of lecture classes.
 *
 * The schedule is represented by a binary matrix schedule[][], where schedule[i][j] = '1'
 * means there is a lecture at the jth hour of the ith day, and schedule[i][j] = '0' means
 * there is no lecture at that time.
 *
 * If the student attends the first lecture at the xth hour and the last lecture at the yth
 * hour on a single day, then they spend (y - x + 1) hours at school that day.
 * The student is allowed to skip up to k lectures in total over all n days.
 *
 * Determine the minimum total time (in hours) the student needs to attend school over all
 * n days, given that they can skip lectures optimally.
 *
 * Example
 *
 * Consider n = 1, m = 5, schedule[][] = ["10001"] and k = 1.
 *
 * The student can skip the last lecture of the first day, that is, schedule[0][4].
 * Then, they only have to attend one lecture at the 0th hour, so the total time spent
 * at school = 1, which is the minimum possible. Thus, the answer is 1. in java
 *
 */
public class MicrosoftOAMinimumTimeStudentSpentInSchool {

    public static int minTotalTime(int n, int m, String[] schedule, int k) {
        // dp_day[i][s] stores the minimum time spent on day 'i' if 's' lectures are skipped on that day.
        // The maximum number of skips on a day is 'm' (if all lectures are skipped).
        List<List<Integer>> dpDay = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> lecturesOnDay = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (schedule[i].charAt(j) == '1') {
                    lecturesOnDay.add(j); // Store the hour of the lecture
                }
            }

            int numLectures = lecturesOnDay.size();
            // dpDay[i] will store min time for 0 to numLectures skips
            List<Integer> daySkipsTime = new ArrayList<>(numLectures + 1);
            for (int s = 0; s <= numLectures; s++) {
                if (s == numLectures) {
                    // Skip all lectures, time spent is 0
                    daySkipsTime.add(0);
                } else {
                    // Attend (numLectures - s) lectures
                    int lecturesToAttend = numLectures - s;
                    int minSpan = Integer.MAX_VALUE;

                    // Iterate through all possible starting points for the attended lectures
                    for (int startIdx = 0; startIdx <= numLectures - lecturesToAttend; startIdx++) {
                        int endIdx = startIdx + lecturesToAttend - 1;
                        int currentSpan = lecturesOnDay.get(endIdx) - lecturesOnDay.get(startIdx) + 1;
                        minSpan = Math.min(minSpan, currentSpan);
                    }
                    daySkipsTime.add(minSpan);
                }
            }
            dpDay.add(daySkipsTime);
        }

        // dp_total[i][j] stores the minimum total time considering days from 0 to 'i-1' (inclusive),
        // using exactly 'j' skips in total.
        // Size: (n+1) x (k+1)
        long[][] dpTotal = new long[n + 1][k + 1];

        // Initialize with a large value
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dpTotal[i], Long.MAX_VALUE);
        }
        dpTotal[0][0] = 0; // 0 days, 0 skips, 0 time

        for (int i = 0; i < n; i++) { // Current day index
            int numLecturesOnCurrentDay = 0;
            for(char c : schedule[i].toCharArray()) {
                if(c == '1') {
                    numLecturesOnCurrentDay++;
                }
            }

            for (int j = 0; j <= k; j++) { // Skips used before current day
                if (dpTotal[i][j] == Long.MAX_VALUE) {
                    continue; // This state is unreachable
                }

                // Iterate through possible skips 's' on the current day 'i'
                // 's' can range from 0 to numLecturesOnCurrentDay
                for (int s = 0; s <= numLecturesOnCurrentDay; s++) {
                    if (j + s <= k) { // If total skips do not exceed 'k'
                        long timeForCurrentDay = dpDay.get(i).get(s);
                        dpTotal[i + 1][j + s] = Math.min(dpTotal[i + 1][j + s], dpTotal[i][j] + timeForCurrentDay);
                    }
                }
            }
        }

        // The minimum total time is the minimum value in the last row of dpTotal
        // (after considering all 'n' days) across all possible skips up to 'k'.
        long minOverallTime = Long.MAX_VALUE;
        for (int j = 0; j <= k; j++) {
            minOverallTime = Math.min(minOverallTime, dpTotal[n][j]);
        }

        return (int) minOverallTime;
    }

    public static void main(String[] args) {
        // Example 1: n = 1, m = 5, schedule[][] = {"10001"}, k = 1
        int n1 = 1, m1 = 5, k1 = 1;
        String[] schedule1 = {"10001"};
        System.out.println("Example 1: " + minTotalTime(n1, m1, schedule1, k1)); // Expected: 1

        // Example 2: n = 2, m = 3, schedule[][] = {"111", "010"}, k = 1
        // Day 0: [0, 1, 2] (3 lectures)
        //   s=0: time = 2-0+1 = 3
        //   s=1: time = 1-0+1=2 (skip 2) OR 2-1+1=2 (skip 0) => min=2
        //   s=2: time = 0 (skip 0,1)
        //   s=3: time = 0 (skip all)
        // dpDay[0] = [3, 2, 0, 0] (for 0, 1, 2, 3 skips) -> only need up to 2 skips for day 0

        // Day 1: [1] (1 lecture)
        //   s=0: time = 1-1+1 = 1
        //   s=1: time = 0 (skip 1)
        // dpDay[1] = [1, 0] (for 0, 1 skips)

        // dpTotal (n=2, k=1) size 3x2
        // dpTotal[0][0] = 0

        // i=0 (Day 0)
        //   j=0 (0 skips used so far)
        //     s=0 (skip 0 on Day 0): dpTotal[1][0] = min(inf, 0 + dpDay[0][0]) = 3
        //     s=1 (skip 1 on Day 0): dpTotal[1][1] = min(inf, 0 + dpDay[0][1]) = 2

        // i=1 (Day 1)
        //   j=0 (0 skips used so far, from dpTotal[1][0]=3)
        //     s=0 (skip 0 on Day 1): dpTotal[2][0] = min(inf, dpTotal[1][0] + dpDay[1][0]) = 3 + 1 = 4
        //     s=1 (skip 1 on Day 1): j+s = 0+1 = 1 <= k. dpTotal[2][1] = min(inf, dpTotal[1][0] + dpDay[1][1]) = 3 + 0 = 3

        //   j=1 (1 skip used so far, from dpTotal[1][1]=2)
        //     s=0 (skip 0 on Day 1): j+s = 1+0 = 1 <= k. dpTotal[2][1] = min(3, dpTotal[1][1] + dpDay[1][0]) = min(3, 2 + 1) = 3
        //     s=1 (skip 1 on Day 1): j+s = 1+1 = 2 > k. Skip.

        // Final dpTotal[2]: [4, 3] (index 0, 1 for total skips)
        // Min of dpTotal[2][0], dpTotal[2][1] = 3
        int n2 = 2, m2 = 3, k2 = 1;
        String[] schedule2 = {"111", "010"};
        System.out.println("Example 2: " + minTotalTime(n2, m2, schedule2, k2)); // Expected: 3

        // Example 3: No lectures
        int n3 = 1, m3 = 5, k3 = 1;
        String[] schedule3 = {"00000"};
        System.out.println("Example 3: " + minTotalTime(n3, m3, schedule3, k3)); // Expected: 0

        // Example 4: Cannot skip
        int n4 = 1, m4 = 5, k4 = 0;
        String[] schedule4 = {"10001"};
        System.out.println("Example 4: " + minTotalTime(n4, m4, schedule4, k4)); // Expected: 5

        // Example 5: Multiple days, more skips
        int n5 = 2, m5 = 5, k5 = 2;
        String[] schedule5 = {"10101", "01110"};
        // Day 0: [0, 2, 4] (3 lectures)
        // s=0: 4-0+1=5
        // s=1: (0,2)=2-0+1=3, (2,4)=4-2+1=3. Min=3
        // s=2: 0
        // dpDay[0] = [5, 3, 0]

        // Day 1: [1, 2, 3] (3 lectures)
        // s=0: 3-1+1=3
        // s=1: (1,2)=2-1+1=2, (2,3)=3-2+1=2. Min=2
        // s=2: 0
        // dpDay[1] = [3, 2, 0]

        // dpTotal (n=2, k=2) size 3x3
        // dpTotal[0][0] = 0

        // i=0 (Day 0)
        //   j=0
        //     s=0: dpTotal[1][0] = 0+5 = 5
        //     s=1: dpTotal[1][1] = 0+3 = 3
        //     s=2: dpTotal[1][2] = 0+0 = 0

        // i=1 (Day 1)
        //   j=0 (from dpTotal[1][0]=5)
        //     s=0: dpTotal[2][0] = min(inf, 5+3) = 8
        //     s=1: dpTotal[2][1] = min(inf, 5+2) = 7
        //     s=2: dpTotal[2][2] = min(inf, 5+0) = 5

        //   j=1 (from dpTotal[1][1]=3)
        //     s=0: dpTotal[2][1] = min(7, 3+3) = 6
        //     s=1: dpTotal[2][2] = min(5, 3+2) = 5
        //     s=2: j+s=3 > k. Skip.

        //   j=2 (from dpTotal[1][2]=0)
        //     s=0: dpTotal[2][2] = min(5, 0+3) = 3
        //     s=1: j+s=3 > k. Skip.
        //     s=2: j+s=4 > k. Skip.

        // Final dpTotal[2]: [8, 6, 3]
        // Min is 3
        System.out.println("Example 5: " + minTotalTime(n5, m5, schedule5, k5)); // Expected: 3
    }
}
