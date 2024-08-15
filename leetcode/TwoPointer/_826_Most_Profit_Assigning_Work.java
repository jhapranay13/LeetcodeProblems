package leetcode.TwoPointer;

import java.util.Arrays;

/**
 *
 * You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
 *
 * difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
 * worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 *
 * For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
 * Return the maximum profit we can achieve after assigning the workers to the jobs.
 *
 *
 *
 * Example 1:
 *
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
 * Example 2:
 *
 * Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == difficulty.length
 * n == profit.length
 * m == worker.length
 * 1 <= n, m <= 10^4
 * 1 <= difficulty[i], profit[i], worker[i] <= 10^5
 *
 */

public class _826_Most_Profit_Assigning_Work {
    // Binary Search Approach
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int[][] difficultyProfit = new int[difficulty.length][2];

        for (int i = 0; i < difficulty.length; i++) {
            difficultyProfit[i][0] = difficulty[i];
            difficultyProfit[i][1] = profit[i];
        }
        Arrays.sort(worker); //sorting worker according to the difficulty that they can handle
        Arrays.sort(difficultyProfit, (a, b) -> b[1] - a[1]); // Sort according to max profit
        int ans = 0;
        int index = worker.length - 1;

        for (int i = 0; i < difficulty.length; i++) {
            int workerIndex = getMinIndexForTask(worker, difficultyProfit[i][0]);

            if (index < 0) {
                //no more workers are available
                break;
            }
            if (workerIndex != -1 && workerIndex <= index) {
                //range of workers for which this task will get max profit
                ans += (index - workerIndex + 1) * difficultyProfit[i][1];
                index = workerIndex - 1;
            }
        }
        return ans;
    }

    private int getMinIndexForTask(int[] worker, int target) {
        int ans = -1;
        int lo = 0;
        int hi = worker.length - 1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (worker[pivot] >= target) {
                ans = pivot;
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }
    //=============================================================================================
    // Two pointer approach
    public int maxProfitAssignment1(int[] difficulty, int[] profit, int[] worker) {
        int[][] difficultyProfit = new int[difficulty.length][2];

        for (int i = 0; i < difficulty.length; i++) {
            difficultyProfit[i][0] = difficulty[i];
            difficultyProfit[i][1] = profit[i];
        }
        Arrays.sort(worker); //sorting worker according to the difficulty that they can handle
        Arrays.sort(difficultyProfit, (a, b) -> a[0] - b[0]); // according to the difficulty
        int maxProfit = 0;
        int ans = 0;
        int dpIndex = 0;

        for (int i = 0; i < worker.length; i++) {
            // Worker who can do the work and can get the profit
            while (dpIndex < difficulty.length && difficultyProfit[dpIndex][0] <= worker[i]) {
                maxProfit = Math.max(maxProfit, difficultyProfit[dpIndex++][1]);
            }
            ans += maxProfit;
        }
        return ans;
    }
}
