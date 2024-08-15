package leetcode.Presum;

/**
 * 
 * @author Pranay Jha
 *
 *         There are n piles of stones arranged in a row. The ith pile has
 *         stones[i] stones.
 * 
 *         A move consists of merging exactly k consecutive piles into one pile,
 *         and the cost of this move is equal to the total number of stones in
 *         these k piles.
 * 
 *         Return the minimum cost to merge all piles of stones into one pile.
 *         If it is impossible, return -1.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: stones = [3,2,4,1], k = 2 Output: 20 Explanation: We start
 *         with [3, 2, 4, 1]. We merge [3, 2] for a cost of 5, and we are left
 *         with [5, 4, 1]. We merge [4, 1] for a cost of 5, and we are left with
 *         [5, 5]. We merge [5, 5] for a cost of 10, and we are left with [10].
 *         The total cost was 20, and this is the minimum possible.
 * 
 *         Example 2:
 * 
 *         Input: stones = [3,2,4,1], k = 3 Output: -1 Explanation: After any
 *         merge operation, there are 2 piles left, and we can't merge anymore.
 *         So the task is impossible.
 * 
 *         Example 3:
 * 
 *         Input: stones = [3,5,1,2,6], k = 3 Output: 25 Explanation: We start
 *         with [3, 5, 1, 2, 6]. We merge [5, 1, 2] for a cost of 8, and we are
 *         left with [3, 8, 6]. We merge [3, 8, 6] for a cost of 17, and we are
 *         left with [17]. The total cost was 25, and this is the minimum
 *         possible.
 * 
 * 
 *         Constraints:
 * 
 *         n == stones.length 1 <= n <= 30 1 <= stones[i] <= 100 2 <= k <= 30
 *
 */

public class _1000_MinimumCostToMergeStones {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int mergeStones(int[] stones, int K) {

		if ((stones.length - 1) % (K - 1) != 0) {
			return -1;
		}
		int pSum[] = new int[stones.length + 1];

		for (int i = 1; i < pSum.length; i++) {
			pSum[i] = pSum[i - 1] + stones[i - 1];
		}
		int[][] memo = new int[stones.length][stones.length];
		int ans = rec(pSum, 0, stones.length - 1, K, memo);
		return ans;
	}

	private int rec(int[] ps, int lo, int hi, int k, int[][] memo) {
		//can also be if (hi - lo < k - 1)  since merging is not possible in this case
		if (lo == hi) {
			return 0;
		}

		if (memo[lo][hi] > 0) {
			return memo[lo][hi];
		}
		int ans = Integer.MAX_VALUE;

		for (int i = lo; i < hi; i += k - 1) {
			ans = Math.min(ans, rec(ps, lo, i, k, memo) + rec(ps, i + 1, hi, k, memo));
		}

		if ((hi - lo) % (k - 1) == 0) {
			ans += ps[hi + 1] - ps[lo];
		}
		return memo[lo][hi] = ans;
	}
}
