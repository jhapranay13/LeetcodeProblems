package leetcode.BinarySearch;

/**
 *
 *
 * You are given a 0-indexed integer array stones sorted in strictly increasing order representing the positions of stones in a river.
 *
 * A frog, initially on the first stone, wants to travel to the last stone and then return to the first stone. However, it can jump to any stone at most once.
 *
 * The length of a jump is the absolute difference between the position of the stone the frog is currently on and the position of the stone to which the frog jumps.
 *
 * More formally, if the frog is at stones[i] and is jumping to stones[j], the length of the jump is |stones[i] - stones[j]|.
 * The cost of a path is the maximum length of a jump among all jumps in the path.
 *
 * Return the minimum cost of a path for the frog.
 *
 *
 *
 * Example 1:
 *
 *               ___________   __   __
 *              /           \ /  \ /  \
 *             0     2        5   6    7
 *              \___/ \_______________/
 *
 * Input: stones = [0,2,5,6,7]
 * Output: 5
 * Explanation: The above figure represents one of the optimal paths the frog can take.
 * The cost of this path is 5, which is the maximum length of a jump.
 * Since it is not possible to achieve a cost of less than 5, we return it.
 * Example 2:
 *
 *
 * Input: stones = [0,3,9]
 * Output: 9
 * Explanation:
 * The frog can jump directly to the last stone and come back to the first stone.
 * In this case, the length of each jump will be 9. The cost for the path will be max(9, 9) = 9.
 * It can be shown that this is the minimum achievable cost.
 *
 *
 * Constraints:
 *
 * 2 <= stones.length <= 10^5
 * 0 <= stones[i] <= 10^9
 * stones[0] == 0
 * stones is sorted in a strictly increasing order.
 *
 *
 */

public class _2498_Frog_Jump_II {
    public int maxJump(int[] stones) {
        int lo = 0;
        int hi = Integer.MAX_VALUE;
        int ans = -1;

        while (lo <= hi) {
            int pivot = lo + (hi - lo) / 2;

            if (check(stones, pivot)) {
                ans = pivot;
                hi = pivot - 1;
            } else {
                lo = pivot + 1;
            }
        }
        return ans;
    }

    private boolean check(int[] stones, int maxLength) {
        boolean[] v = new boolean[stones.length];
        int anchor = 0;
        // Checking for maximum that we can go and adjusting it accordingly
        for (int i = 1; i < stones.length; i++) {

            if (stones[i] - stones[anchor] > maxLength) {

                if (anchor == i - 1) {
                    return false;
                }
                v[i - 1] = true;
                anchor = i - 1;
            }
        }
        anchor = stones.length - 1;
        // Checking if the rest which will be used as return path should not be greater
        for (int i = anchor - 1; i >= 0; i--) {

            if (!v[i]) {

                if (stones[anchor] - stones[i] > maxLength) {
                    return false;
                }
                anchor = i;
            }
        }
        return true;
    }
}
