package leetcode.Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of n pairs pairs where pairs[i] = [lefti,
 *         righti] and lefti < righti.
 * 
 *         A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of
 *         pairs can be formed in this fashion.
 * 
 *         Return the length longest chain which can be formed.
 * 
 *         You do not need to use up all the given intervals. You can select
 *         pairs in any order.
 * 
 *         Example 1:
 * 
 *         Input: pairs = [[1,2],[2,3],[3,4]] Output: 2 Explanation: The longest
 *         chain is [1,2] -> [3,4]. 
 *         
 *         Example 2:
 * 
 *         Input: pairs = [[1,2],[7,8],[4,5]] Output: 3 Explanation: The longest
 *         chain is [1,2] -> [4,5] -> [7,8].
 * 
 * 
 *         Constraints:
 * 
 *         n == pairs.length 1 <= n <= 1000 -1000 <= lefti < righti < 1000
 *
 */

public class _646_MaximumLengthOfPairChain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Top Down Approach
	public int findLongestChain(int[][] pairs) {
		Arrays.sort(pairs, new Comparator<int[]>() {
			public int compare(int[] arr1, int[] arr2) {
				return arr1[0] - arr2[0];
			}
		});
		int[][] memo = new int[pairs.length + 1][pairs.length];
		return recur(pairs, -1, 0, memo);
	}

	private int recur(int[][] pairs, int prev, int curr, int[][] memo) {
		if (curr >= pairs.length) {
			return 0;
		}

		if (memo[prev + 1][curr] > 0) {
			return memo[prev + 1][curr];
		}
		int include = 0;

		if (prev < 0 || pairs[prev][1] < pairs[curr][0]) {
			include += 1 + recur(pairs, curr, curr + 1, memo);
		}
		int exclude = recur(pairs, prev, curr + 1, memo);
		return memo[prev + 1][curr] = Math.max(include, exclude);
	}
	//==============================================================================
	//Bottom up Approach
	public int findLongestChain1(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
		int N = pairs.length;
		int[] dp = new int[N];
		Arrays.fill(dp, 1);

		for (int j = 1; j < N; ++j) {
			for (int i = 0; i < j; ++i) {
				if (pairs[i][1] < pairs[j][0])
					dp[j] = Math.max(dp[j], dp[i] + 1);
			}
		}

		int ans = 0;
		for (int x: dp) if (x > ans) ans = x;
		return ans;
	}
	//===============================================================================
	//Greedy Approach
	public int findLongestChain2(int[][] pairs) {
		Arrays.sort(pairs, (a, b) -> a[1] - b[1]);
		int cur = Integer.MIN_VALUE, ans = 0;

		for (int[] pair: pairs) {
			if (cur < pair[0]) {
				cur = pair[1];
				ans++;
			}
		}
		return ans;
	}
}
