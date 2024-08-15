package leetcode.Sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.
 *
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 *
 *
 *
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 * Example 3:
 *
 *
 *
 * Input: startTime = [1,1,1], endTime = [2,3,4], profit = [5,6,4]
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 1 <= startTime[i] < endTime[i] <= 10^9
 * 1 <= profit[i] <= 10^4
 *
 */

public class _1235_Maximum_Profit_in_Job_Scheduling {
    //PriorityQueue Solution
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobHolder = new int[startTime.length][3];

        for (int i = 0; i < startTime.length; i++) {
            jobHolder[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        //Sorting end time
        Arrays.sort(jobHolder, (a, b) -> a[0] - b[0]);
        //Sorting end time
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int ans = 0;

        for (int jobData[] : jobHolder) {
            int start = jobData[0];
            int end = jobData[1];
            int currProfit = jobData[2];

            while (!pq.isEmpty() && start >= pq.peek()[0]) {
                ans = Math.max(ans, pq.poll()[1]);
            }
            pq.offer(new int[] {end, currProfit + ans});
        }

        while (!pq.isEmpty()) {
            ans = Math.max(ans, pq.poll()[1]);
        }
        return ans;
    }
    //=============================================================================================
    //Top Down Approach
    public int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobHolder = new int[startTime.length][3];

        for (int i = 0; i < startTime.length; i++) {
            jobHolder[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobHolder, (a, b) -> a[0] - b[0]);
        int[] memo = new int[jobHolder.length];
        return recur(jobHolder, 0, memo);
    }

    private int recur(int[][]jobHolder, int index, int[] memo) {

        if (index >= jobHolder.length) {
            return 0;
        }

        if (memo[index] > 0) {
            return memo[index];
        }
        int nextGreater = greaterThanEqualTo(jobHolder, index);
        int inc = jobHolder[index][2] + recur(jobHolder, nextGreater, memo);
        int exc = recur(jobHolder, index + 1, memo);
        return memo[index] = Math.max(inc, exc);
    }

    private int greaterThanEqualTo(int[][] jobHolder, int index) {
        int ans = jobHolder.length;
        int lo = index;
        int hi = ans - 1;
        int target = jobHolder[index][1];

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (jobHolder[pivot][0] < target) {
                lo = pivot + 1;
            } else {
                ans = pivot;
                hi = pivot - 1;
            }
        }
        return ans;
    }
    //=============================================================================================
    //Bottom up Approach
    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {
        int[][] jobHolder = new int[startTime.length][3];

        for (int i = 0; i < startTime.length; i++) {
            jobHolder[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }

        Arrays.sort(jobHolder, (a, b) -> a[0] - b[0]);
        int[] memo = new int[jobHolder.length + 1];

        for (int index = jobHolder.length - 1; index >= 0; index--) {
            int nextGreater = greaterThanEqualTo(jobHolder, index);
            int inc = jobHolder[index][2] + recur(jobHolder, nextGreater, memo);
            int exc = recur(jobHolder, index + 1, memo);
            memo[index] = Math.max(inc, exc);
        }
        return memo[0];
    }
}
