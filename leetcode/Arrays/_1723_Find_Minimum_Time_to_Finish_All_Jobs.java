package leetcode.Arrays;

import java.util.Arrays;

/**
 *
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
 *
 * There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker. The working time of a worker is the sum of the time it takes to complete all jobs assigned to them. Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
 *
 * Return the minimum possible maximum working time of any assignment.
 *
 *
 *
 * Example 1:
 *
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 * Example 2:
 *
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 *
 *
 * Constraints:
 *
 * 1 <= k <= jobs.length <= 12
 * 1 <= jobs[i] <= 10^7
 *
 */

public class _1723_Find_Minimum_Time_to_Finish_All_Jobs {
    public int minimumTimeRequired(int[] jobs, int k) {
        int[] workers = new int[k];
        recur(jobs, 0, workers);
        return ans;
    }
    private int ans = Integer.MAX_VALUE;
    private int max = Integer.MAX_VALUE;

    private void recur(int[] jobs, int index, int[] workers) {

        if (index == jobs.length) {
            int currMax = getMax(workers);

            if (currMax < max) {
                ans = Math.min(currMax, ans);
                max = currMax;
            }
            return;
        }

        for (int i = 0; i < workers.length; i++) {
            // When the time is same answer will aslo be the same so no need to do this.
            // Set can also be used for this.
            if (i > 0 && workers[i] == workers[i - 1]) {
                continue;
            }
            workers[i] += jobs[index];
            recur(jobs, index + 1, workers);
            workers[i] -= jobs[index];
        }
    }

    private int getMax(int[] arr) {
        int ans = 0;

        for (int num : arr) {
            ans = Math.max(ans, num);
        }
        return ans;
    }
    //=============================================================================================
    // Binary search
    public int minimumTimeRequired1(int[] jobs, int k) {
        Arrays.sort(jobs);
        int lo = jobs[jobs.length - 1];
        int hi = lo * jobs.length;

        while (lo < hi) {
            int pivot = lo + (hi - lo) / 2;

            if (recur(jobs, new int[k], pivot, 0)) {
                hi = pivot;
            } else {
                lo = pivot + 1;
            }
        }
        return hi;
    }

    private boolean recur(int[] jobs, int[] workers, int maxTime, int index) {

        if (index == jobs.length) {
            return true;
        }

        for (int i = 0; i < workers.length; i++) {

            if (i > 0 && workers[i] == workers[i - 1]) {
                continue;
            }
            workers[i] += jobs[index];

            if (workers[i] <= maxTime && recur(jobs, workers, maxTime, index + 1)) {
                return true;
            }
            workers[i] -= jobs[index];
        }

        return false;
    }
}
