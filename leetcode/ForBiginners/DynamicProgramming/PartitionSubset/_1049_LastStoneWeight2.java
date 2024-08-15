package leetcode.ForBiginners.DynamicProgramming.PartitionSubset;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of integers stones where stones[i] is the
 *         weight of the ith stone.
 * 
 *         We are playing a game with the stones. On each turn, we choose any
 *         two stones and smash them together. Suppose the stones have weights x
 *         and y with x <= y. The result of this smash is:
 * 
 *         If x == y, both stones are destroyed, and If x != y, the stone of
 *         weight x is destroyed, and the stone of weight y has new weight y -
 *         x. At the end of the game, there is at most one stone left.
 * 
 *         Return the smallest possible weight of the left stone. If there are
 *         no stones left, return 0.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: stones = [2,7,4,1,8,1] Output: 1 Explanation: We can combine 2
 *         and 4 to get 2, so the array converts to [2,7,1,8,1] then, we can
 *         combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then, we
 *         can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
 *         we can combine 1 and 1 to get 0, so the array converts to [1], then
 *         that's the optimal value.
 * 
 *         Example 2:
 * 
 *         Input: stones = [31,26,33,21,40] Output: 5
 * 
 *         Example 3:
 * 
 *         Input: stones = [1,2] Output: 1
 * 
 * 
 *         Constraints:
 * 
 *         1 <= stones.length <= 30 1 <= stones[i] <= 100
 *
 */

public class _1049_LastStoneWeight2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Top Down Approach
	public int lastStoneWeightII(int[] stones) {
		int sum = 0;

		for (int stone : stones) {
			sum += stone;
		}
		int[][] memo = new int[stones.length][sum + 1];
		int ans = recur(stones, 0, 0, memo);

		return ans;
	}

	private int recur(int[] stones, int index, int sum, int[][] memo) {
		if (index == stones.length) {
			return sum;
		}

		if (memo[index][sum] > 0) {
			return memo[index][sum];
		}
		int accumulator = recur(stones, index + 1, Math.abs(sum + stones[index]), memo);
		int reducer = recur(stones, index + 1, Math.abs(sum - stones[index]), memo);
		return memo[index][sum] = Math.min(accumulator, reducer);
	}
	//===============================================================================
	//Another Approach
	public int lastStoneWeightII2(int[] stones) {
        int sumStWt = 0;
        for(int stone : stones){
            sumStWt += stone;
        }
        Integer[][] dp = new Integer[stones.length][sumStWt];
        return helper(stones, 0, 0, 0, dp);
    }
    
    private int helper(int[] stones, int index, int sumL, int sumR, Integer[][] dp) {
        if(index == stones.length){
            return Math.abs(sumL - sumR);
        }
        
        if(dp[index][sumL] != null) {
            return dp[index][sumL];
        }
        
        dp[index][sumL] = Math.min(helper(stones, index+1, sumL + stones[index], sumR, dp) , helper(stones, index+1, sumL, sumR + stones[index], dp));
        return dp[index][sumL];
    }
	//=============================================================================================
	//Bottom up approach
	public int lastStoneWeightII3(int[] stones) {
		int sum = 0;
		int max = 0;
		for (int stone : stones) {
			sum += stone;
			max = Math.max(max, stone);
		}
		int[][] memo = new int[stones.length + 1][sum + max + 1];
		int s = sum;

		for (int index = stones.length; index >= 0; index--) {
			for (; sum >= 0; sum--) {
				if (index == stones.length) {
					memo[index][sum] = sum;
					continue;
				}
				int accumulator = memo[index + 1][Math.abs(sum + stones[index])];
				int reducer = memo[index + 1][Math.abs(sum - stones[index])];
				memo[index][sum] = Math.min(accumulator, reducer);
			}
			sum = s;
		}
		return memo[0][0];
	}
}
