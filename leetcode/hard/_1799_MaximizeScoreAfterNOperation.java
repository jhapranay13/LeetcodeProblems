package leetcode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given nums, an array of positive integers of size 2 * n. You
 *         must perform n operations on this array.
 * 
 *         In the ith operation (1-indexed), you will:
 * 
 *         Choose two elements, x and y. Receive a score of i * gcd(x, y).
 *         Remove x and y from nums. Return the maximum score you can receive
 *         after performing n operations.
 * 
 *         The function gcd(x, y) is the greatest common divisor of x and y.
 * 
 *         Example 1:
 * 
 *         Input: nums = [1,2] Output: 1 Explanation: The optimal choice of
 *         operations is: (1 * gcd(1, 2)) = 1
 *         
 *         Example 2:
 * 
 *         Input: nums = [3,4,6,8] Output: 11 Explanation: The optimal choice of
 *         operations is: (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11 
 *         
 *         Example 3:
 * 
 *         Input: nums = [1,2,3,4,5,6] Output: 14 Explanation: The optimal
 *         choice of operations is: (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 *
 *         gcd(3, 6)) = 1 + 4 + 9 = 14
 * 
 *         Constraints:
 * 
 *         1 <= n <= 7 nums.length == 2 * n 1 <= nums[i] <= 106
 *
 */
/*
Since any operation can begin from any position in the Array and it can go back and
forward so it's like combination of operation and set at that time that defines the state
This type of problem can be done by defining the state using bitmasking or String 
and keep it as a key in hashMap to be later returned if we encounter the state again.
*/

public class _1799_MaximizeScoreAfterNOperation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Using Bit mask as Array Position
	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	private int dfs(int[] n, int[][] dp, int i, int mask) {
		if (i > n.length / 2)
			return 0;
		if (dp[i][mask] == 0)

			for (int j = 0; j < n.length; ++j)

				for (int k = j + 1; k < n.length; ++k) {
					int new_mask = (1 << j) + (1 << k);

					if ((mask & new_mask) == 0) {
						dp[i][mask] = Math.max(dp[i][mask], i * gcd(n[j], n[k]) + dfs(n, dp, i + 1, mask + new_mask));

					}
				}
		return dp[i][mask];
	}

	public int maxScore(int[] n) {
		return dfs(n, new int[n.length / 2 + 1][1 << n.length], 1, 0);
	}

	// =============================================================================
	// Using Bit mask as Map Key
	public int maxScore1(int[] nums) {
		int state = 0;
		Map<Integer, Integer> memo = new HashMap<>();
		Arrays.sort(nums);
		return helper(state, nums, memo, 0);
	}

	int helper(int state, int[] nums, Map<Integer, Integer> memo, int times) {
		if (memo.containsKey(state)) {
			return memo.get(state);
		}
		int max = 0;
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (((state >> (n - i - 1)) & 1) == 0 && ((state >> (n - j - 1)) & 1) == 0) {
					int tempState = state;
					tempState = tempState | (1 << n - i - 1);
					tempState = tempState | (1 << n - j - 1);
					int rest = helper(tempState, nums, memo, times + 1);
					max = Math.max(max, (times + 1) * gcd(nums[j], nums[i]) + rest);
				}
			}
		}
		memo.put(state, max);
		return max;
	}

	// =============================================================================
	// Using String to represent State as Map Key
	public int maxScore2(int[] nums) {
		Set<Integer> usedNumbers = new HashSet<>();
		Map<String, Integer> memo = new HashMap<>();
		return recur(nums, 1, usedNumbers, memo);
	}

	private int recur(int[] nums, int operation, Set<Integer> usedNumbers, Map<String, Integer> memo) {

		if (operation > nums.length / 2) {
			return 0;
		}
		String key = usedNumbers.toString() + ", " + operation;

		if (memo.containsKey(key)) {
			return memo.get(key);
		}
		int max = 0;

		for (int i = 0; i < nums.length; i++) {

			if (!usedNumbers.contains(i)) {

				for (int j = i + 1; j < nums.length; j++) {
					int numOne = nums[i];
					int numTwo = nums[j];

					if (!usedNumbers.contains(j)) {
						usedNumbers.add(i);
						usedNumbers.add(j);
						int score = operation * gcd(numOne, numTwo);
						int nextScore = recur(nums, operation + 1, usedNumbers, memo);
						max = Math.max(max, score + nextScore);
						usedNumbers.remove(i);
						usedNumbers.remove(j);
					}
				}
			}
		}
		memo.put(key, max);
		return max;
	}
}
