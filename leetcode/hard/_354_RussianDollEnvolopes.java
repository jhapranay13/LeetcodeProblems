package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given a 2D array of integers envelopes where envelopes[i] =
 *         [wi, hi] represents the width and the height of an envelope.
 * 
 *         One envelope can fit into another if and only if both the width and
 *         height of one envelope are greater than the other envelope's width
 *         and height.
 * 
 *         Return the maximum number of envelopes you can Russian doll (i.e.,
 *         put one inside the other).
 * 
 *         Note: You cannot rotate an envelope.
 * 
 *         Example 1:
 * 
 *         Input: envelopes = [[5,4],[6,4],[6,7],[2,3]] Output: 3 Explanation:
 *         The maximum number of envelopes you can Russian doll is 3 ([2,3] =>
 *         [5,4] => [6,7]).
 * 
 *         Example 2:
 * 
 *         Input: envelopes = [[1,1],[1,1],[1,1]] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= envelopes.length <= 5000 envelopes[i].length == 2 1 <= wi, hi <=
 *         104
 *
 */

public class _354_RussianDollEnvolopes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// ============================================================================
	// Top Down approach
	public int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, new Comparator<>() {
			public int compare(int[] x, int[] y) {
				if (x[0] == y[0]) {
					return x[1] - y[1];
				}
				return x[0] - y[0];
			}
		});
		int ans = 0;
		int[] memo = new int[envelopes.length];

		for (int index = 0; index < envelopes.length; index++) {
			ans = Math.max(ans, recur(envelopes, index, memo));
		}
		return ans;
	}

	private int recur(int[][] envelopes, int index, int[] memo) {
		if (index == envelopes.length) {
			return 0;
		}

		if (memo[index] > 0) {
			return memo[index];
		}
		int val = 1;

		for (int i = index + 1; i < envelopes.length; i++) {
			if (envelopes[index][0] < envelopes[i][0] && envelopes[index][1] < envelopes[i][1]) {
				val = Math.max(val, 1 + recur(envelopes, i, memo));
			}
		}
		return memo[index] = val;
	}

	// ============================================================================
	// Bottom up approach
	public int maxEnvelopes1(int[][] envelopes) {
		Arrays.sort(envelopes, new Comparator<>() {
			public int compare(int[] x, int[] y) {
				if (x[0] == y[0]) {
					return x[1] - y[1];
				}
				return x[0] - y[0];
			}
		});
		int ans = 0;
		int[] memo = new int[envelopes.length];
		Arrays.fill(memo, 1);

		for (int index = envelopes.length - 1; index >= 0; index--) {

			int val = 1;

			for (int i = index + 1; i < envelopes.length; i++) {
				if (envelopes[index][0] < envelopes[i][0] && envelopes[index][1] < envelopes[i][1]) {
					val = Math.max(val, 1 + memo[i]);
				}
			}
			memo[index] = val;
			ans = Math.max(ans, memo[index]);
		}
		return ans;
	}
	//=============================================================================================
	// Binary Search approach
	public int maxEnvelopes12(int[][] envelopes) {
		Arrays.sort(envelopes, (a, b) -> {
			if (a[0] == b[0]) {
				return b[1] - a[1];
			}
			return a[0] - b[0];
		});
		List<Integer> holder = new ArrayList<>();

		for (int i = 0; i < envelopes.length; i++) {
			int curr = envelopes[i][1];

			if (i == 0 || holder.get(holder.size() - 1) < curr) {
				holder.add(curr);
			} else {
				int index = binarySearchJustGreaterThan(holder, curr);
				holder.set(index, curr);
			}
		}
		return holder.size();
	}

	private int binarySearchJustGreaterThan(List<Integer> holder, int target) {
		int lo = 0;
		int hi = holder.size() - 1;

		while (lo < hi) {
			int pivot = lo + (hi - lo) / 2;

			if (holder.get(pivot) < target) {
				lo = pivot + 1;
			} else {
				hi = pivot;
			}
		}
		return hi;
	}
}
