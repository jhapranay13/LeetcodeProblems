package leetcode.TreeSetTreeMap;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 *
 * You have n robots. You are given two 0-indexed integer arrays, chargeTimes and runningCosts, both of length n. The ith robot costs chargeTimes[i] units to charge and costs runningCosts[i] units to run. You are also given an integer budget.
 *
 * The total cost of running k chosen robots is equal to max(chargeTimes) + k * sum(runningCosts), where max(chargeTimes) is the largest charge cost among the k robots and sum(runningCosts) is the sum of running costs among the k robots.
 *
 * Return the maximum number of consecutive robots you can run such that the total cost does not exceed budget.
 *
 *
 *
 * Example 1:
 *
 * Input: chargeTimes = [3,6,1,3,4], runningCosts = [2,1,3,4,5], budget = 25
 * Output: 3
 * Explanation:
 * It is possible to run all individual and consecutive pairs of robots within budget.
 * To obtain answer 3, consider the first 3 robots. The total cost will be max(3,6,1) + 3 * sum(2,1,3) = 6 + 3 * 6 = 24 which is less than 25.
 * It can be shown that it is not possible to run more than 3 consecutive robots within budget, so we return 3.
 * Example 2:
 *
 * Input: chargeTimes = [11,12,19], runningCosts = [10,8,7], budget = 19
 * Output: 0
 * Explanation: No robot can be run that does not exceed the budget, so we return 0.
 *
 *
 * Constraints:
 *
 * chargeTimes.length == runningCosts.length == n
 * 1 <= n <= 5 * 10^4
 * 1 <= chargeTimes[i], runningCosts[i] <= 10^5
 * 1 <= budget <= 10^15
 *
 */

public class _2398_Maximum_Number_of_Robots_Within_Budget {
    //Monotonic Queue solution
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int ans = 0;
        int fast = 0;
        int slow = 0;
        Deque<Integer> mono = new LinkedList<>();
        long sum = 0;

        while (fast < chargeTimes.length) {
            sum += runningCosts[fast];

            while (!mono.isEmpty() && chargeTimes[mono.peekLast()] < chargeTimes[fast]) {
                mono.pollLast();
            }

            mono.offerLast(fast);
            int max = chargeTimes[mono.peekFirst()];
            int length = fast - slow + 1;
            long calculation = max + length * sum;

            while (calculation > budget && slow <= fast) {
                sum -= runningCosts[slow++];

                while (!mono.isEmpty() && mono.peekFirst() < slow) {
                    mono.pollFirst();
                }
                length = fast - slow + 1;
                max = mono.isEmpty() ? 0 : chargeTimes[mono.peekFirst()];
                calculation = max + length * sum;
            }
            ans = Math.max(ans, fast - slow + 1);
            fast++;
        }
        return ans;
    }
    //=============================================================================================
    //Binary Search with PriorityQueue
    public int maximumRobots2(int[] chargeTimes, int[] runningCosts, long budget) {
        long[] presum = new long[runningCosts.length];
        presum[0] = runningCosts[0];

        for (int i = 1; i < runningCosts.length; i++) {
            presum[i] = presum[i - 1] + runningCosts[i];
        }
        int lo = 0;
        int hi = chargeTimes.length - 1;
        int ans = 0 ;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (isPossible(chargeTimes, presum, budget, pivot)) {
                ans = Math.max(ans, pivot + 1);
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private boolean isPossible(int[] chargeTimes, long[] presum, long budget, int index) {
        PriorityQueue<int[]> holder = new PriorityQueue<>((a, b) ->
                a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]);
        int pivot = 0;

        for (int i = 0; i < chargeTimes.length; i++) {
            int[] temp = {chargeTimes[i], i};
            holder.offer(temp);

            if (i >= index) {
                int max = holder.peek()[0];
                long sum = pivot == 0 ? presum[i] : presum[i] - presum[pivot - 1];
                //index + 1 is the length
                long calculation = (long)max + ((long)(index + 1) * sum);

                if (calculation <= budget) {
                    return true;
                }
                pivot++;

                while (!holder.isEmpty() && holder.peek()[1] < pivot) {
                    holder.poll();
                }
            }
        }
        return false;
    }
    //=============================================================================================
    // TreeSet Experiment Very slow
    public int maximumRobots3(int[] chargeTimes, int[] runningCosts, long budget) {
        long[] presum = new long[runningCosts.length];
        presum[0] = runningCosts[0];

        for (int i = 1; i < runningCosts.length; i++) {
            presum[i] = presum[i - 1] + runningCosts[i];
        }
        int lo = 0;
        int hi = chargeTimes.length - 1;
        int ans = 0 ;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (isPossible1(chargeTimes, presum, budget, pivot)) {
                ans = Math.max(ans, pivot + 1);
                lo = pivot + 1;
            } else {
                hi = pivot - 1;
            }
        }
        return ans;
    }

    private boolean isPossible1(int[] chargeTimes, long[] presum, long budget, int index) {
        TreeSet<int[]> holder = new TreeSet<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]); //can use priority queue too
        int pivot = 0;

        for (int i = 0; i < chargeTimes.length; i++) {
            int[] temp = {chargeTimes[i], i};
            holder.add(temp);

            if (i >= index) {
                int max = holder.last()[0];
                long sum = pivot == 0 ? presum[i] : presum[i] - presum[pivot - 1];
                //index + 1 is the length
                long calculation = (long)max + ((long)(index + 1) * sum);

                if (calculation <= budget) {
                    return true;
                }
                pivot++;

                while (!holder.isEmpty() && holder.last()[1] < pivot) {
                    holder.remove(holder.last());
                }
            }
        }
        return false;
    }
}
