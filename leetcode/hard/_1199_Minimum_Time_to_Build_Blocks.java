package leetcode.hard;

import java.util.Arrays;

/**
 *
 * You are given a list of blocks, where blocks[i] = t means that the i-th block needs t units of time to be built. A block can only be built by exactly one worker.
 *
 * A worker can either split into two workers (number of workers increases by one) or build a block then go home. Both decisions cost some time.
 *
 * The time cost of spliting one worker into two workers is given as an integer split. Note that if two workers split at the same time, they split in parallel so the cost would be split.
 *
 * Output the minimum time needed to build all blocks.
 *
 * Initially, there is only one worker.
 *
 *
 *
 * Example 1:
 *
 * Input: blocks = [1], split = 1
 * Output: 1
 * Explanation: We use 1 worker to build 1 block in 1 time unit.
 * Example 2:
 *
 * Input: blocks = [1,2], split = 5
 * Output: 7
 * Explanation: We split the worker into 2 workers in 5 time units then assign each of them to a block so the cost is 5 + max(1, 2) = 7.
 * Example 3:
 *
 * Input: blocks = [1,2,3], split = 1
 * Output: 4
 * Explanation: Split 1 worker into 2, then assign the first worker to the last block and split the second worker into 2.
 * Then, use the two unassigned workers to build the first two blocks.
 * The cost is 1 + max(3, 1 + max(1, 2)) = 4.
 *
 *
 * Constraints:
 *
 * 1 <= blocks.length <= 1000
 * 1 <= blocks[i] <= 10^5
 * 1 <= split <= 100
 *
 *
 */

public class _1199_Minimum_Time_to_Build_Blocks {
    public int minBuildTime(int[] blocks, int split) {
        Arrays.sort(blocks);
        // Array In reverse Order
        for (int i = 0; i < blocks.length / 2; i++) {
            int temp = blocks[i];
            blocks[i] = blocks[blocks.length - 1 - i];
            blocks[blocks.length - 1 - i] = temp;
        }
        int[][] memo = new int[blocks.length][blocks.length];

        for (int[] mem : memo) {
            Arrays.fill(mem, -1);
        }
        return recur(blocks, split, 0, 1, memo);
    }

    private int recur(int[] blocks, int split, int index, int worker, int[][] memo) {

        if (index == blocks.length) {
            return 0;
        }

        if (worker == 0) {
            return Integer.MAX_VALUE;
        }
        // if count of worker covers all the blocks that are left
        if (worker >= blocks.length - index) {
            return blocks[index];
        }

        if (memo[index][worker] > -1) {
            return memo[index][worker];
        }
        int assignWorkerCost = recur(blocks, split, index + 1, worker - 1, memo);
        int splitCost = split + recur(blocks, split, index, worker * 2, memo);
        return memo[index][worker] = Math.max(Math.min(assignWorkerCost, splitCost), blocks[index]);
    }
}